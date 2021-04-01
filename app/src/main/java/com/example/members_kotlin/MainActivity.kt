package com.example.members_kotlin

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

var members: MemberList = MemberList()
var filteredMembers: MemberList = MemberList()
var defaultIcons = mapOf("Yasin Cetiner" to R.drawable.avatar1, "Tolga Can Unal" to R.drawable.avatar2, "Mithat Sinan Sari" to R.drawable.avatar3);
var randomIcons = arrayOf(R.drawable.avatar4, R.drawable.avatar5, R.drawable.avatar6, R.drawable.avatar7, R.drawable.avatar8, R.drawable.avatar9, R.drawable.avatar10
        , R.drawable.maskavatar1, R.drawable.maskavatar2, R.drawable.maskavatar3, R.drawable.maskavatar4, R.drawable.maskavatar5, R.drawable.maskavatar6, R.drawable.maskavatar7
        , R.drawable.maskavatar8, R.drawable.maskavatar9, R.drawable.maskavatar10);
var numberOfRandomIcons: Int = 17
class MainActivity : AppCompatActivity() {
    //This method gets the json file and instantiate members and filteredMembers
    private fun makeRequest(){
        val json : String?
        try {
            val inputStream: InputStream = assets.open("hipo.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonArray = JSONArray(json)
            members = MemberList(jsonArray)
            filteredMembers = MemberList(members)
        }
        catch(e : IOException){}
    }

    //This method adds a new member, also updates the filteredMembers and UI
    private fun add(member: Member){
        members.list.add(member)
        filteredMembers = MemberList(members)
        updateUI()
    }

    //This method filters members and save them to filteredMembers
    private fun filter(name: String){
        filteredMembers.list.clear()
        for(i in 0 until members.list.size)
        {
            if(members.list[i].name.toLowerCase().contains(name.toLowerCase()))
                filteredMembers.list.add(members.list[i])
        }
        updateUI()
    }

    //This method updates the UI
    private fun updateUI(){
        //Actually we could only use notifyDataSetChanged() method however it does not work with adapter.filter() method
        val lv_listView = findViewById<ListView>(R.id.lv_listView)
        lv_listView.adapter= MyAdapter(this, filteredMembers.list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeRequest()

        val lv_listView = findViewById<ListView>(R.id.lv_listView)
        lv_listView.adapter= MyAdapter(this, filteredMembers.list)
        lv_listView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("name", filteredMembers.list[position].name)
            intent.putExtra("age", filteredMembers.list[position].age)
            intent.putExtra("location", filteredMembers.list[position].location)
            intent.putExtra("github", filteredMembers.list[position].github)
            intent.putExtra("position", filteredMembers.list[position].hipo.position)
            intent.putExtra("years", filteredMembers.list[position].hipo.yearsInHipo.toString())
            intent.putExtra("imageIndex", filteredMembers.list[position].imageIndex)
            startActivity(intent)
        }
        val tv_emptyTextView = findViewById<TextView>(R.id.tv_emptyTextView)
        lv_listView.emptyView = tv_emptyTextView

        val addButton = findViewById<ExtendedFloatingActionButton>(R.id.extended_fab)
        addButton.setOnClickListener {
            var myHipo: Hipo = Hipo("Intern", 0)
            var me: Member = Member("Onuralp Avci", 20, "Bursa", "oavci1625", myHipo)
            me.setRandomImageIndex(numberOfRandomIcons)
            add(me)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)

        val search : MenuItem? = menu?.findItem(R.id.nav_search)
        val searchView : SearchView = search?.actionView as SearchView
        searchView.queryHint = "Search Something"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(search: String): Boolean {
                filter(search)
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}

//Class MyAdapter
class MyAdapter(private val context: Context, private val members: java.util.ArrayList<Member>) : BaseAdapter() {
    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var location: TextView
    private lateinit var image: ImageView
    override fun getCount(): Int {
        return members.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.member_row, parent, false)
        name = convertView.findViewById(R.id.tv_rowname)
        age = convertView.findViewById(R.id.tv_rowage)
        location = convertView.findViewById(R.id.tv_rowlocation)
        image = convertView.findViewById(R.id.iv_image)

        name.text = members[position].name
        age.text = "Age: " + members[position].age
        location.text = "Location: " + members[position].location
        //Users' images in json file are hardcoded and this if checks if they are in the defaultIconMap
        if(defaultIcons.containsKey(name.text.toString()))
            defaultIcons.get(name.text.toString())?.let { image.setImageResource(it) }
        //If they are not a default member a random image is assigned to user from randomIcons array
        else
            image.setImageResource(randomIcons[members[position].imageIndex])

        return convertView
    }
}
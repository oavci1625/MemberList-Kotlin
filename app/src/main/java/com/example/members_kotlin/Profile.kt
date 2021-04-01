package com.example.members_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionBar = supportActionBar
        actionBar!!.title = "Profile"

        val tv_name = findViewById<TextView>(R.id.tv_profile_name)
        val tv_age = findViewById<TextView>(R.id.tv_profile_age)
        val tv_location = findViewById<TextView>(R.id.tv_profile_location)
        val tv_github = findViewById<TextView>(R.id.tv_profile_github)
        val tv_position = findViewById<TextView>(R.id.tv_profile_position)
        val tv_years = findViewById<TextView>(R.id.tv_profile_years)
        val tv_image = findViewById<ImageView>(R.id.iv_profile_img)

        val name= intent.getStringExtra("name")
        val age= intent.getIntExtra("age", 0)
        val location= intent.getStringExtra("location")
        val github= intent.getStringExtra("github")
        val position= intent.getStringExtra("position")
        val years= intent.getStringExtra("years")
        val imageIndex= intent.getIntExtra("imageIndex", 0)

        //Users' images in json file are hardcoded and this if checks if they are in the defaultIconMap
        if(defaultIcons.containsKey(name))
            defaultIcons.get(name)?.let { tv_image.setImageResource(it) }
        //If they are not a default member a random image is assigned to user from randomIcons array
        else
            tv_image.setImageResource(randomIcons[imageIndex])

        tv_name.text = name
        tv_age.text = age.toString()
        tv_location.text = location
        tv_github.text = github
        tv_position.text = position
        tv_years.text = years

    }
}
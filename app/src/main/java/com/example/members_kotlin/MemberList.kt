package com.example.members_kotlin

import org.json.JSONArray
import org.json.JSONObject

class MemberList (){
    var list = arrayListOf<Member>()

    constructor(json: JSONArray) : this(){
        for (i in 0 until json.length()) {
            val item = json.getJSONObject(i)
            list.add(Member(item))
        }
    }

    constructor(memberList: MemberList): this(){
        for (i in 0 until memberList.list.size) {
           list.add(i, memberList.list[i].copy())
        }
    }

}
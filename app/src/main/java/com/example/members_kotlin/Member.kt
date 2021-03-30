package com.example.members_kotlin

import org.json.JSONObject

class Member (var name: String = "", var age: Int = 0, var location: String = "", var github: String = "", var hipo: Hipo = Hipo()){
    constructor(json: JSONObject) : this(){
        name = json.getString("name");
        age = json.getInt("age");
        location = json.getString("location");
        github = json.getString("github");
        hipo = Hipo( json.getJSONObject("hipo"));
    }

    fun copy(name: String = this.name, age: Int = this.age, location: String = this.location, github: String = this.github, hipo: Hipo = this.hipo.copy()) = Member(name, age, location, github,hipo)
}
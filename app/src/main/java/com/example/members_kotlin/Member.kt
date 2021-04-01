package com.example.members_kotlin

import org.json.JSONObject

class Member (var name: String = "", var age: Int = 0, var location: String = "", var github: String = "", var hipo: Hipo = Hipo(), var imageIndex: Int = 0){
    constructor(json: JSONObject) : this(){
        name = json.getString("name");
        age = json.getInt("age");
        location = json.getString("location");
        github = json.getString("github");
        hipo = Hipo( json.getJSONObject("hipo"));
    }

    fun copy(name: String = this.name, age: Int = this.age, location: String = this.location, github: String = this.github, hipo: Hipo = this.hipo.copy(), imageIndex: Int = this.imageIndex) = Member(name, age, location, github,hipo, imageIndex)

    //For newly added users this method assigns a random image index for the user. This index is used during UI update procedure
    fun setRandomImageIndex(imageNumber: Int){
        imageIndex = (0..(imageNumber - 1)).random()
    }
}
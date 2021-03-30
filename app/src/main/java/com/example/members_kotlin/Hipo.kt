package com.example.members_kotlin

import org.json.JSONObject

class Hipo (var position: String = "", var yearsInHipo: Int = 0){

    constructor(json: JSONObject) : this(){
        position = json.getString("position");
        yearsInHipo = json.getInt("years_in_hipo");
    }

    fun copy(position: String = this.position, yearsInHipo: Int = this.yearsInHipo) = Hipo(position, yearsInHipo)
}

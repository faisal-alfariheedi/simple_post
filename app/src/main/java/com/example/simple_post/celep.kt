package com.example.simple_post

import com.google.gson.annotations.SerializedName

class celep {

    var data:ArrayList<dat>?=null

    class dat {

        @SerializedName("name")
        var name: String? = null

        constructor(name: String?) {
            this.name = name

        }
    }

}
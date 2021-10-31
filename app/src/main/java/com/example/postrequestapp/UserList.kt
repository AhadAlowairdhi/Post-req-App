package com.example.postrequestapp


import com.google.gson.annotations.SerializedName

class UserList {
    @SerializedName("pk")
    var pk: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("location")
    var location: String? = null

    constructor (pk: Int?, name: String?, location: String?){
        this.pk = pk
        this.name = name
        this.location = location
    }
}
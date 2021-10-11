package com.example.postrequestapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("/test")
    fun getUsers(): Call<ArrayList<User>>

    @POST("/test/")
    fun addUser(@Body user: User) : Call<User>
}
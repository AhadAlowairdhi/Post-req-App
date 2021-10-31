package com.example.postrequestapp.model

import com.example.postrequestapp.UserList
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUsers(): Call<List<UserList?>>

    @POST("/test/")
    fun addUser(@Body user: User) : Call<User>

    @Headers("Content-Type: application/json")
    @PUT("/test/{pk}")
    fun updateInfo(@Path("pk") pk: Int, @Body updateUbring: UserList): Call<List<UserList>>

    @Headers("Content-Type: application/json")
    @DELETE ("/test/{pk}")
    fun deleteInfo(@Path("pk") pk: Int): Call<Void>
}
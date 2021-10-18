package com.example.simple_post

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("custom-people/")
    fun getdat(): Call<List<celep.dat>>


    @Headers("Content-Type: application/json")
    @POST("custom-people/")
    fun adddat(@Body data: celep.dat): Call<celep.dat>

    @Headers("Content-Type: application/json")
    @PUT("custom-people/{id}")
    fun updatedat(@Path("id")id:Int, @Body data: celep.dat): Call<celep.dat>

    @Headers("Content-Type: application/json")
    @DELETE("custom-people/{id}")
    fun deldat(@Path("id")id:Int): Call<celep.dat>
}
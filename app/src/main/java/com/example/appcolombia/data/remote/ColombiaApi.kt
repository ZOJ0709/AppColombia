package com.example.appcolombia.data.remote

import com.example.appcolombia.data.model.City
import com.example.appcolombia.data.model.Department
import com.example.appcolombia.data.model.Region
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ColombiaApi {

    @GET("v1/Department")
    suspend fun getDepartments(): List<Department>

    @GET("v1/Department/{id}")
    suspend fun getDepartmentById(
        @Path("id") id: Int
    ): Department

    @GET("v1/Region/{id}")
    suspend fun getRegionById(
        @Path("id") id: Int
    ): Region

    @GET("v1/City/{id}")
    suspend fun getCityById(
        @Path("id") id: Int
    ): City

    @GET("v1/City")
    suspend fun getCities(): List<City>
}

object RetrofitClient {

    private const val BASE_URL = "https://api-colombia.com/api/"

    val api: ColombiaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ColombiaApi::class.java)
    }
}
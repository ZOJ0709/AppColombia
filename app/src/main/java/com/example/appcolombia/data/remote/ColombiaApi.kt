package com.example.appcolombia.data.remote

import com.example.appcolombia.data.model.Department
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
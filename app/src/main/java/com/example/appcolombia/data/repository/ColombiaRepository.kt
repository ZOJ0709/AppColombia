package com.example.appcolombia.data.repository

import com.example.appcolombia.data.model.City
import com.example.appcolombia.data.model.Department
import com.example.appcolombia.data.model.Region
import com.example.appcolombia.data.remote.RetrofitClient

class ColombiaRepository {

    private val api = RetrofitClient.api

    suspend fun getDepartments(): Result<List<Department>> {
        return try {
            val departments = api.getDepartments()
            Result.success(departments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDepartmentById(id: Int): Result<Department> {
        return try {
            val department = api.getDepartmentById(id)
            Result.success(department)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRegionById(id: Int): Result<Region> {
        return try {
            val region = api.getRegionById(id)
            Result.success(region)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCityById(id: Int): Result<City> {
        return try {
            val city = api.getCityById(id)
            Result.success(city)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getCities(): Result<List<City>> {
        return try {
            val cities = api.getCities()
            Result.success(cities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
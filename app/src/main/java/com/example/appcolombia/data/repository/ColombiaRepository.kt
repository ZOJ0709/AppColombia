package com.example.appcolombia.data.repository

import com.example.appcolombia.data.model.Department
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
}
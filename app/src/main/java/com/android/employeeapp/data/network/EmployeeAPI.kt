package com.android.employeeapp.data.network

import com.android.employeeapp.data.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("employees.json")
    suspend fun getEmployees() : EmployeeResponse
}
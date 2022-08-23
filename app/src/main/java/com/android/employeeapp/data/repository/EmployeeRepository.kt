package com.android.employeeapp.data.repository

import com.android.employeeapp.data.model.EmployeeResponse
import com.android.employeeapp.data.network.EmployeeAPI
import javax.inject.Inject

class EmployeeRepository
@Inject
constructor(
    private val apiClient: EmployeeAPI
    ) {

    suspend fun getEmployees() : EmployeeResponse {
        return apiClient.getEmployees()
    }
}
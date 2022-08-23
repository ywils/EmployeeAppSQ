package com.android.employeeapp.data.model

import com.squareup.moshi.Json

data class Employee(
    val uuid : String,
    @Json(name = "full_name")
    val name : String,
    @Json(name = "phone_number")
    val phone: String?,
    @Json(name = "email_address")
    val email: String,
    @Json(name = "biography")
    val bio: String?,
    @Json(name = "photo_url_small")
    val picS: String?,
    @Json(name = "photo_url_large")
    val picL: String?,
    val team: String,
    @Json(name = "employee_type")
    val type: EmployeeTypeEnum
)

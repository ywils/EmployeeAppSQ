package com.android.employeeapp.data.model

enum class EmployeeTypeEnum {
    FULL_TIME, PART_TIME, CONTRACTOR;

    override fun toString(): String {
        return when (this) {
            FULL_TIME -> "Full Time"
            PART_TIME -> "Part Time"
            CONTRACTOR -> "Contractor"
        }
    }
}


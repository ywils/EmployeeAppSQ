package com.android.employeeapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.employeeapp.data.model.Employee
import com.android.employeeapp.data.network.Resource
import com.android.employeeapp.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val _spinner = MutableLiveData(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _empList = MutableLiveData<Resource<List<Employee>>>()
    val employees: LiveData<Resource<List<Employee>>>
        get() = _empList

    init {
        loadEmployees()
    }

    private fun loadEmployees(showSpinner: Boolean = true) {
        viewModelScope.launch {
            try {
                _spinner.postValue(showSpinner)
                _empList.postValue(Resource.Loading)
                val res = repository.getEmployees()
                _empList.postValue(Resource.Success(res.employees))
            } catch (error: Exception) {
                _empList.postValue(Resource.Error(error))
            } finally {
                _spinner.postValue(false)
            }
        }
    }

    fun refresh() {
        loadEmployees(showSpinner = false)
    }

}
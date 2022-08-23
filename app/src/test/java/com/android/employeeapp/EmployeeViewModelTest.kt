package com.android.employeeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.employeeapp.data.model.Employee
import com.android.employeeapp.data.model.EmployeeResponse
import com.android.employeeapp.data.model.EmployeeTypeEnum
import com.android.employeeapp.data.repository.EmployeeRepository
import com.android.employeeapp.ui.viewmodel.EmployeeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class EmployeeViewModelTest {

    private lateinit var viewModel: EmployeeViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository: EmployeeRepository = mockk()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = EmployeeViewModel(repository = repository)
   }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `load screen success`()  {
        testCoroutineRule.runBlockingTest {
            val mockResult = mockResponse()

            coEvery { repository.getEmployees() } returns mockResult

            //test
            viewModel.refresh()

            //verify
            coVerify { repository.getEmployees() }

        }

    }

    companion object {
        private fun mockEmployee() = Employee(
            uuid = "1234",
            name = "First Name",
            phone = "954 555 1234",
            email = "first.last@squareup.com",
            bio = "lorem ipsum",
            picS = null,
            picL = null,
            team = "Core",
            type = EmployeeTypeEnum.PART_TIME
        )

        fun mockResponse() = EmployeeResponse(
            listOf(mockEmployee())
        )
    }
}
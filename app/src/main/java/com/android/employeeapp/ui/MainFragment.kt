package com.android.employeeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.employeeapp.R
import com.android.employeeapp.data.network.Resource
import com.android.employeeapp.databinding.MainFragmentBinding
import com.android.employeeapp.ui.adapter.EmployeeAdapter
import com.android.employeeapp.ui.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : Fragment(R.layout.main_fragment), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: EmployeeViewModel by activityViewModels()
    private lateinit var refreshLayout: SwipeRefreshLayout

    private val adapter by lazy {
        EmployeeAdapter()
    }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshLayout = binding.swipeLayout
        refreshLayout.setOnRefreshListener(this)

        binding.recyclerView.adapter = adapter

        startObservers()
    }

    private fun startObservers() {
        viewModel.employees.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data.let {
                        refreshLayout.isRefreshing = false
                        if (it.isEmpty()) showEmpty() else adapter.setEmployees(it)
                    }
                }
                is Resource.Loading -> {}

                is Resource.Error -> showError()
            }

        }
        viewModel.spinner.observe(viewLifecycleOwner) { res ->
            binding.prog.visibility = if (res) View.VISIBLE else View.GONE
        }
    }

    private fun showEmpty() {
        binding.emptyText.visibility = View.VISIBLE
    }

    private fun showError() {
        binding.error.visibility = View.VISIBLE
        binding.errorText.visibility = View.VISIBLE
    }

}
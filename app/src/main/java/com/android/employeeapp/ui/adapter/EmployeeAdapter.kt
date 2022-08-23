package com.android.employeeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.employeeapp.R
import com.android.employeeapp.data.model.Employee
import com.android.employeeapp.databinding.EmployeeListBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private val employeeList = mutableListOf<Employee>()

    fun setEmployees(employees: List<Employee>) {
        employeeList.clear()
        employeeList.addAll(employees)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemBinding =
            EmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    class EmployeeViewHolder(private val itemBinding: EmployeeListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val root = itemBinding.root
        fun bind(employee: Employee) {
            itemBinding.name.text = employee.name
            itemBinding.team.text = root.context.getString(R.string.team, employee.team)
            itemBinding.type.text = root.context.getString(R.string.type, employee.type.toString())
            Glide.with(itemBinding.root.context)
                .load(employee.picL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(
                    RequestOptions()
                        .error(R.drawable.ic_baseline_error_24)
                        .placeholder(R.drawable.ic_baseline_image_24)
                        .fitCenter()
                )
                .into(itemBinding.pic)
            itemBinding.bio.text = root.context.resources.getString(R.string.bio, employee.bio)

        }
    }
}
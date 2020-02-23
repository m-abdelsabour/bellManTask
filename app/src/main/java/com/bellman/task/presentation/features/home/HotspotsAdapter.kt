package com.bellman.task.presentation.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bellman.task.BR
import com.bellman.task.R
import com.bellman.task.entity.HotSpot

class HotspotsAdapter() :
    RecyclerView.Adapter<HotspotsAdapter.ClassViewHolder>() {
    private val data = ArrayList<HotSpot>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        return ClassViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.custom_hotspot_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.binding.setVariable(BR.data, data[position])
        holder.binding.executePendingBindings()
    }

    fun addData(list: MutableList<HotSpot>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


    class ClassViewHolder(internal val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}
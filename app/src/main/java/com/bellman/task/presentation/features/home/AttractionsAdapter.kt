package com.bellman.task.presentation.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bellman.task.BR
import com.bellman.task.R
import com.bellman.task.entity.Attraction
import com.bellman.task.entity.HotSpot

class AttractionsAdapter() :
    RecyclerView.Adapter<AttractionsAdapter.ClassViewHolder>() {
    private val data = ArrayList<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        return ClassViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.custom_attraction_item,
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

    fun addData(list: MutableList<Attraction>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


    class ClassViewHolder(internal val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}
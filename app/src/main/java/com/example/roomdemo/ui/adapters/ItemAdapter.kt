package com.example.roomdemo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.databinding.ItemLayoutBinding
import com.example.roomdemo.ui.adapters.ItemAdapter.ViewHolder
import com.example.roomdemo.ui.models.User

class ItemAdapter(var items: List<User>): RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bindView(item: User){
            binding.user = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemLayoutBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_layout,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return if(items.size == null) 0
        else items.size
    }
}
package com.genarosoft.dms.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.genarosoft.dms.databinding.ItemHomeBinding

class HomeAdapter(private val options: List<HomeOption>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(options[position])
    }

    override fun getItemCount(): Int {
        return options.size
    }

    class ViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(option: HomeOption) {
            binding.title.text = option.title
            binding.description.text = option.description
            binding.root.setOnClickListener {
                if (option.action != null) {
                    it.findNavController().navigate(option.action)
                }
            }
        }
    }
}
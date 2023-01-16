package com.genarosoft.dms.ui.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genarosoft.dms.databinding.ItemProductBinding

class ProductAdapter(private val values: List<String>, private val listener: ProductListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
    }

    class ViewHolder(
        private val binding: ItemProductBinding,
        private val listener: ProductListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            binding.productName.text = value
            binding.root.setOnClickListener {
                listener.onOpenDialog(value)
            }
        }
    }
}
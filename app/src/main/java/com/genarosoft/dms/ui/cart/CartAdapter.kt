package com.genarosoft.dms.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genarosoft.dms.databinding.ItemCartBinding
import com.genarosoft.dms.ui.productlist.ProductListener

class CartAdapter(
    private val values: List<String>,
    private val cartListener: CartListener,
    private val productListener: ProductListener
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, cartListener, productListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
    }

    class ViewHolder(
        private val binding: ItemCartBinding,
        private val cartListener: CartListener,
        private val productListener: ProductListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String) {
            binding.productName.text = value
            binding.root.setOnClickListener {
                productListener.onOpenDialog(value)
            }
            binding.delete.setOnClickListener {
                cartListener.onDelete(adapterPosition)
            }
        }
    }
}
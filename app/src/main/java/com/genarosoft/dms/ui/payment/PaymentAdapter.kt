package com.genarosoft.dms.ui.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.ItemPaymentBinding

class PaymentAdapter(private val values: List<String>, private val listener: PaymentListener) :
    RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemPaymentBinding.inflate(
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
        private val binding: ItemPaymentBinding,
        private val listener: PaymentListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String) {
            val context = binding.root.context
            binding.paymentTitle.text = value
            binding.root.setOnClickListener {
                listener.openPaymentMethodDialog(value)
            }
            val drawableResource = when (value) {
                "Cash" -> R.drawable.ic_cash
                "Credit/Debit card" -> R.drawable.ic_credit_card
                "QR code" -> R.drawable.ic_qr_code
                else -> R.drawable.ic_cash
            }
            binding.paymentIcon.setImageDrawable(context.getDrawable(drawableResource))
            if (adapterPosition > 0) {
                binding.delete.setOnClickListener {
                    listener.onDeletePaymentMethod(adapterPosition)
                }
            } else {
                binding.delete.visibility = View.GONE
            }
        }
    }
}
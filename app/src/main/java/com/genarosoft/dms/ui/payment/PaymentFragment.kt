package com.genarosoft.dms.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.genarosoft.dms.databinding.FragmentPaymentBinding
import com.genarosoft.dms.ui.paymentmethod.PaymentMethodDialogFragment
import com.genarosoft.dms.ui.paymentmethod.PaymentMethodDialogListener
import com.google.android.material.snackbar.Snackbar

class PaymentFragment : PaymentListener, PaymentMethodDialogListener, Fragment() {
    companion object {
        const val TAG = "PaymentFragment"
    }

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PaymentAdapter
    private lateinit var paymentMethods: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentMethods = mutableListOf(
            "Credit/Debit card",
        )

        adapter = PaymentAdapter(paymentMethods, this)
        binding.paymentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.paymentRecyclerView.adapter = adapter

        binding.paymentFab.setOnClickListener {
            openPaymentMethodDialog(null)
        }
    }

    override fun onDeletePaymentMethod(position: Int) {
        val deletedItem: String = paymentMethods[position]
        paymentMethods.removeAt(position)
        adapter.notifyItemRemoved(position)
        Snackbar.make(binding.paymentRecyclerView, deletedItem, Snackbar.LENGTH_LONG)
            .setAction(
                "Undo"
            ) {
                paymentMethods.add(position, deletedItem)
                adapter.notifyItemInserted(position)
            }
            .show()
    }

    override fun openPaymentMethodDialog(paymentMethod: String?) {
        PaymentMethodDialogFragment.newInstance(this, paymentMethod)
            .show(childFragmentManager, PaymentMethodDialogFragment.TAG)
    }

    override fun onAddedProduct(paymentMethod: String) {
        paymentMethods.add(paymentMethod)
        adapter.notifyItemInserted(paymentMethods.size - 1)
        Snackbar.make(
            binding.paymentRecyclerView,
            "Added a $paymentMethod payment method!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
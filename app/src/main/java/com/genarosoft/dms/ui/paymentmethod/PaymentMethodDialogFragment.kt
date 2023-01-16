package com.genarosoft.dms.ui.paymentmethod

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.FragmentPaymentMethodDialogBinding
import com.genarosoft.dms.ui.payment.PaymentListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PaymentMethodDialogFragment(private val paymentMethodDialogListener: PaymentMethodDialogListener) :
    DialogFragment() {
    companion object {
        const val TAG = "PaymentMethodDialogFragment"
        private const val PAYMENT_METHOD = "payment_method"

        fun newInstance(
            listener: PaymentMethodDialogListener,
            paymentMethod: String?
        ): PaymentMethodDialogFragment {
            val args = Bundle()
            args.putString(PAYMENT_METHOD, paymentMethod)
            val fragment = PaymentMethodDialogFragment(listener)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var paymentMethod: String

    private var _binding: FragmentPaymentMethodDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paymentMethod = it.getString(PAYMENT_METHOD, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paymentMethods = arrayOf(
            "Cash",
            "Credit/Debit card",
            "QR code"
        )
        Log.i(TAG, "onViewCreated: ${paymentMethods.size}")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, paymentMethods)
        binding.paymentMethodTypeInput.setAdapter(adapter)
        binding.paymentMethodTypeInput.addTextChangedListener {
            if (it.toString() == paymentMethods[1]) {
                binding.cardLayout.visibility = View.VISIBLE
            } else {
                binding.cardLayout.visibility = View.GONE
            }
        }

        if (paymentMethod.isNotEmpty()) {
            binding.paymentMethodTypeInput.setText(paymentMethod, false)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arguments?.let {
            paymentMethod = it.getString(PAYMENT_METHOD, "")
        }

        val builder = MaterialAlertDialogBuilder(requireActivity())
        val inflater = requireActivity().layoutInflater
        _binding = FragmentPaymentMethodDialogBinding.inflate(inflater)

        val actionText = if (paymentMethod.isEmpty()) "Add" else "Update"

        builder.setView(binding.root)
            .setTitle("$actionText payment method")
            .setPositiveButton(actionText) { _, _ ->
                paymentMethodDialogListener.onAddedProduct(binding.paymentMethodTypeInput.text.toString())
            }
            .setNegativeButton("Cancel") { _, _ -> }

        return builder.create()

    }

}
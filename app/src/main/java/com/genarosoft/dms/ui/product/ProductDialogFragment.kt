package com.genarosoft.dms.ui.product

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.genarosoft.dms.databinding.DialogProductBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductDialogFragment : DialogFragment() {
    // todo: change an receive whole product
    companion object {
        const val PRODUCT = "product"
        const val TAG = "ProductDialogFragment"

        fun newInstance(product: String): ProductDialogFragment {
            val args = Bundle()
            args.putString(PRODUCT, product)
            val fragment = ProductDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var product: String

    private var _binding: DialogProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getString(PRODUCT).toString()
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
        try {
            binding.productQuantity.requestFocus()
            binding.productName.text = product
            binding.productQuantityTextInput.setText("1")
            binding.productQuantityTextInput.addTextChangedListener {
                if (!TextUtils.isEmpty(it)) {
                    val value = "$it".toInt()
                    binding.productQuantityTextInputLayout.isStartIconVisible = value != 0
                    binding.productQuantity.text = "x $value"
                    binding.productSubtotal.text = "${value * 25}.00"
                }
            }
            binding.productQuantityTextInputLayout.setEndIconOnClickListener {
                try {
                    val newValue = getCurrentQuantityValue() + 1
                    binding.productQuantityTextInput.setText(newValue.toString())
                } catch (e: Exception) {
                    Log.e(TAG, "onViewCreated: ${e.message}", e)
                }
            }
            binding.productQuantityTextInputLayout.setStartIconOnClickListener {
                try {
                    val newValue = getCurrentQuantityValue() - 1
                    binding.productQuantityTextInput.setText(newValue.toString())
                } catch (e: Exception) {
                    Log.e(TAG, "onViewCreated: ${e.message}", e)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "onViewCreated: ${e.message}", e)
        }
    }

    private fun getCurrentQuantityValue(): Int {
        val value = binding.productQuantityTextInput.text
        if (TextUtils.isEmpty(value)) return 0
        return "$value".toInt()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arguments?.let {
            product = it.getString(PRODUCT).toString()
        }

        val builder = MaterialAlertDialogBuilder(requireActivity())
        val inflater = requireActivity().layoutInflater
        _binding = DialogProductBinding.inflate(inflater)
        builder.setView(binding.root)
            .setTitle("Add product - PPCE0012")
            .setPositiveButton(
                "Add"
            ) { dialog, which ->
                Toast.makeText(
                    requireContext(),
                    "Added '$product' to cart!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id ->
                    // Send the negative button event back to the host activity
//                    listener.onDialogNegativeClick(this)
                })
        return builder.create()
    }
}
package com.genarosoft.dms.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.FragmentFilterModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip

class FilterModalBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "FilterModalBottomSheet"
    }

    private var _binding: FragmentFilterModalBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterModalBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.amountFilterRangeSlider.addOnChangeListener { rangeSlider, value, fromUser ->
            binding.amountFilterStartValue.text = "Bs. ${rangeSlider.values.first()}.-"
            binding.amountFilterEndValue.text = "Bs. ${rangeSlider.values.last()}.-"
        }

        val states = arrayOf("Pending", "Finished", "Cancelled", "Exported")
        states.forEach { state ->
            binding.chipGroup.addView(createStateChip(state))
        }
    }

    private fun createStateChip(state: String): Chip {
        return Chip(context).apply {
            text = state
            isCheckable = true
            isChecked = true
            isCheckedIconVisible = true
            setOnCloseIconClickListener {
                isChecked = !isChecked
                isCheckedIconVisible = !isCheckedIconVisible
            }
        }
    }

}
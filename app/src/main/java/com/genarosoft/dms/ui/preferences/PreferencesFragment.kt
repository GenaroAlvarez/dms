package com.genarosoft.dms.ui.preferences

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.FragmentPreferencesBinding

class PreferencesFragment : Fragment() {
    companion object {
//        todo: params
//        const val FORM_SCHEMA = "formSchema"
    }

//    todo: params
//    private lateinit var param1: String

    private var _binding: FragmentPreferencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            todo: params
//            param1 = it.getString(FORM_SCHEMA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>
        val users = listOf(
            "Cobranza en entrega", "Cobranza en venta", "Facturación computarizada",
            "Facturación electrónica", "Tíldes en impresión", "Copia de impresión"
        )

        val adapter = PreferenceAdapter(users)
        binding.preferencesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.preferencesRecyclerView.adapter = adapter
    }
}
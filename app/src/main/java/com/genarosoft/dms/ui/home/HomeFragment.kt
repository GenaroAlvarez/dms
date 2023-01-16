package com.genarosoft.dms.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.genarosoft.dms.databinding.FragmentHomeBinding
import com.genarosoft.dms.ui.login.LoginFragmentDirections
import com.genarosoft.dms.ui.preferences.PreferenceAdapter

class HomeFragment : Fragment() {
    companion object {
//        todo: params
//        const val FORM_SCHEMA = "formSchema"
    }

//    todo: params
//    private lateinit var param1: String

    private var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // todo: to string resource
        val options = mutableListOf(
            HomeOption("Customers", "Customer list for employee")
        )

        // todo: implement preferences
//        if (companyPreferences.isEntregaEnabled) {
//            options.add("Entregas")
//        }
//        if (companyPreferences.isAutoVentaEnabled) {
        options.add(
            HomeOption(
                "Orders",
                "Orders assigned to employee",
                HomeFragmentDirections.actionHomeFragmentToOrderListFragment()
            )
        )
        options.add(
            HomeOption(
                "Deliveries",
                "Deliveries assigned to employee",
            )
        )

        options.add(
            HomeOption(
                "Prospect",
                "Create or edit customers assigned to employee",
            )
        )
//        }
//        if (companyPreferences.isReimpresionEnabled) {
//            options.add("Venta")
//        }

        options.add(
            HomeOption(
                "Reprint",
                "Reprint documents by a custom search",
            )
        )

        options.add(
            HomeOption(
                "Import",
                "Sync server information to app",
                HomeFragmentDirections.actionHomeFragmentToImportFragment()
            )
        )
        options.add(
            HomeOption(
                "Export",
                "Sync app information to server",
                HomeFragmentDirections.actionHomeFragmentToPreferencesFragment()
            )
        )

        val adapter = HomeAdapter(options)
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRecyclerView.adapter = adapter


//        binding.imp.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToImportFragment()
//            it.findNavController().navigate(action)
//        }
//        binding.export.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToExportFragment()
//            it.findNavController().navigate(action)
//        }
//        binding.order.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToOrderListFragment()
//            it.findNavController().navigate(action)
//        }
//        binding.config.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToPreferencesFragment()
//            it.findNavController().navigate(action)
//        }
    }
}
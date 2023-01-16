package com.genarosoft.dms.ui.order

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.FragmentOrderBinding
import java.text.SimpleDateFormat
import java.util.*

class OrderFragment : Fragment() {
    companion object {
//        todo: params
//        const val FORM_SCHEMA = "formSchema"
    }

//    todo: params
//    private lateinit var param1: String

    private var _binding: FragmentOrderBinding? = null
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
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo: delete
        binding.deliveryDateTextInput.setText(SimpleDateFormat("dd-MM-yyyy").format(Date()))
        binding.documentNumberTextInput.setText("98798798")
        binding.businessNameTextInput.setText("JOAQUIN CHUMACERO")
        binding.emailEditText.setText("joaquin.chumacero@gmail.com")
        val documentTypes = arrayOf(
            "CARNET DE IDENTIDAD",
            "NIT",
            "CARNET EXTRANJERO",
            "PASAPORTE",
            "OTRO"
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, documentTypes)
        binding.documentTypeInput.setAdapter(adapter)
        binding.documentTypeInput.setText("CARNET DE IDENTIDAD", false)

//        binding.next.setOnClickListener {
//            val action = OrderFragmentDirections.actionOrderFragmentToCheckoutFragment()
//            it.findNavController().navigate(action)
//        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.order_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_cart -> {
                        val action = OrderFragmentDirections.actionOrderFragmentToCheckoutFragment()
                        NavHostFragment.findNavController(requireParentFragment()).navigate(action)
                        true
                    }
                    R.id.action_add_cart -> {
                        val action =
                            OrderFragmentDirections.actionOrderFragmentToProductListFragment()
                        NavHostFragment.findNavController(requireParentFragment()).navigate(action)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
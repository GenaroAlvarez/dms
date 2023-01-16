package com.genarosoft.dms.ui.productlist

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.FragmentProductListBinding
import com.genarosoft.dms.ui.product.ProductDialogFragment

class ProductListFragment : ProductListener, Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val products = mutableListOf(
            "Product 201 6x12",
            "Product 202 6x12",
            "Product 203 6x12",
            "Product 204 6x12",
            "Product 205 6x12",
            "Product 206 6x12",
            "Product 207 6x12",
            "Product 208 6x12",
            "Product 209 6x12",
            "Product 300 6x12",
            "Product 301 6x12",
            "Product 302 6x12",
            "Product 303 6x12",
            "Product 304 6x12",
        )
        val adapter = ProductAdapter(products, this)
        binding.productListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.productListRecyclerView.adapter = adapter

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.product_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_cart -> {
                        val action =
                            ProductListFragmentDirections.actionProductListFragmentToCheckoutFragment()
                        NavHostFragment.findNavController(requireParentFragment()).navigate(action)
                        true
                    }
                    else -> false
                }
            }

        })
    }

    override fun onOpenDialog(product: String) {
        ProductDialogFragment.newInstance(product)
            .show(childFragmentManager, ProductDialogFragment.TAG)
    }
}

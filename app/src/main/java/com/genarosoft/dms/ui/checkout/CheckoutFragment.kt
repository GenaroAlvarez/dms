package com.genarosoft.dms.ui.checkout

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.genarosoft.dms.R
import com.genarosoft.dms.databinding.FragmentCheckoutBinding
import com.genarosoft.dms.ui.cart.CartFragment
import com.genarosoft.dms.ui.payment.PaymentFragment
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutFragment : Fragment() {
    companion object {
//        todo: params
//        const val FORM_SCHEMA = "formSchema"
    }

//    todo: params
//    private lateinit var param1: String

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var checkoutAdapter: CheckoutAdapter
    private lateinit var viewPager: ViewPager2

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
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabList = mutableListOf(
            TabItem("Product list", CartFragment())
        )

//        if (companyPreferences.isPaymentMethodEnabled) {
        tabList.add(TabItem("Payment method", PaymentFragment()))
//        }

        checkoutAdapter = CheckoutAdapter(this, tabList)
        viewPager = binding.pager
        viewPager.adapter = checkoutAdapter

        TabLayoutMediator(binding.checkoutTabLayout, viewPager) { tab, position ->
            tab.text = tabList[position].title
        }.attach()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.checkout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    // todo: change to R.string
    data class TabItem(val title: String, val type: Fragment)
}
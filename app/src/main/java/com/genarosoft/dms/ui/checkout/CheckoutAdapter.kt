package com.genarosoft.dms.ui.checkout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CheckoutAdapter(val fragment: Fragment, val tabList: List<CheckoutFragment.TabItem>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return tabList.size
    }

    override fun createFragment(position: Int): Fragment {
//        val fragment =
////        fragment.arguments = Bundle().apply {
////            putInt()
////        }
        // todo: change name to fragment
        return tabList[position].type
    }
}
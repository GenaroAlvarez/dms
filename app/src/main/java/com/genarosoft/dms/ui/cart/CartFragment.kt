package com.genarosoft.dms.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.genarosoft.dms.databinding.FragmentCartBinding
import com.genarosoft.dms.ui.product.ProductDialogFragment
import com.genarosoft.dms.ui.productlist.ProductListener
import com.google.android.material.snackbar.Snackbar

class CartFragment : ProductListener, CartListener, Fragment() {
    companion object {
//        todo: params
//        const val FORM_SCHEMA = "formSchema"
    }

//    todo: params
//    private lateinit var param1: String

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartAdapter: CartAdapter
    private lateinit var users: MutableList<String>

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
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        users = mutableListOf(
            "Naranjito 12x6",
            "Doradito 12x5",
            "Tampico 650ml",
            "Pilsener 315mlx6",
            "Naranjito 12x6",
            "Doradito 12x5",
            "Tampico 650ml",
            "Pilsener 315mlx6",
            "Naranjito 12x6",
            "Doradito 12x5",
            "Tampico 650ml",
            "Pilsener 315mlx6"
        )



        cartAdapter = CartAdapter(users, this, this)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = cartAdapter

//        if (companyPreferences.isDeletingPedidoEnabled) {
//
//        }

//        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.adapterPosition
//                val deletedItem: String = users[position]
//                users.removeAt(position)
//                adapter.notifyItemRemoved(position)
//                Snackbar.make(binding.cartRecyclerView, deletedItem, Snackbar.LENGTH_LONG)
//                    .setAction(
//                        "Undo"
//                    ) {
//                        users.add(position, deletedItem)
//                        adapter.notifyItemInserted(position)
//                    }
//                    .show()
//            }
//
//        }).attachToRecyclerView(binding.cartRecyclerView)
    }

    override fun onDelete(position: Int) {
        val deletedItem: String = users[position]
        users.removeAt(position)
        cartAdapter.notifyItemRemoved(position)
        Snackbar.make(binding.cartRecyclerView, deletedItem, Snackbar.LENGTH_LONG)
            .setAction(
                "Undo"
            ) {
                users.add(position, deletedItem)
                cartAdapter.notifyItemInserted(position)
            }
            .show()
    }

    override fun onOpenDialog(product: String) {
        ProductDialogFragment.newInstance(product)
            .show(childFragmentManager, ProductDialogFragment.TAG)
    }

}
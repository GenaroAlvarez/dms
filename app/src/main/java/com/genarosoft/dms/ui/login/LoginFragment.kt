package com.genarosoft.dms.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.genarosoft.dms.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    companion object {
//        todo: params
//        const val FORM_SCHEMA = "formSchema"
    }

//    todo: params
//    private lateinit var param1: String

    private var _binding: FragmentLoginBinding? = null
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            it.findNavController().navigate(action)
        }
    }
}
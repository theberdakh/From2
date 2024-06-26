package com.theberdakh.from2.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentLoginBinding
import com.theberdakh.from2.screen.register.RegisterFragment
import com.theberdakh.from2.util.replaceFragment
import timber.log.Timber

class LoginFragment: Fragment() {
    private var  _binding: FragmentLoginBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        Timber.i("onCreateView()")

        binding.iconCloseLogin.setOnClickListener {
            Timber.i("iconCloseLogin clicked")
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        Timber.i("OnDestroyView()")
    }
}
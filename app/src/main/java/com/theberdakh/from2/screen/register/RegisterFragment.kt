package com.theberdakh.from2.screen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentRegisterBinding
import com.theberdakh.from2.screen.login.LoginFragment
import com.theberdakh.from2.util.addFragment
import timber.log.Timber

class RegisterFragment: Fragment() {
    private var _binding: FragmentRegisterBinding? =null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        Timber.i("onCreateView()")

        requireActivity().window.apply {
            val background = ContextCompat.getDrawable(requireContext(), R.drawable.background)
            setBackgroundDrawable(background)
        }

        binding.iconClose.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnSignIn.setOnClickListener {
            addFragment(parentFragmentManager,
                R.id.fragment_register_container,
                LoginFragment(),
                "LoginFragment")
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart()")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Timber.i("onDestroyView()")
        requireActivity().window.setBackgroundDrawable(null)

    }
}
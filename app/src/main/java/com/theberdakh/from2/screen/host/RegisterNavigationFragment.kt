package com.theberdakh.from2.screen.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentNavigationRegisterBinding
import com.theberdakh.from2.screen.register.RegisterFragment
import com.theberdakh.from2.util.replaceFragment

class RegisterNavigationFragment : Fragment() {
    private var _binding: FragmentNavigationRegisterBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationRegisterBinding.inflate(inflater, container, false)

        replaceFragment(
            childFragmentManager,
            R.id.fragment_register_container,
            RegisterFragment()
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.theberdakh.from2.screen.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentLoginBinding
import com.theberdakh.from2.util.setFullscreen
import com.theberdakh.from2.util.setGradientBackground
import timber.log.Timber

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? =null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        Timber.i("onCreateView()")

        requireActivity().window.apply {
            val background = ContextCompat.getDrawable(requireContext(), R.drawable.background)
            setBackgroundDrawable(background)
        }

        binding.iconClose.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
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
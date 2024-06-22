package com.theberdakh.from2.screen.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.util.showMenu

class TranslateFragment: Fragment() {
    private var _binding: FragmentTranslateBinding?  =null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=  FragmentTranslateBinding.inflate(inflater, container, false)

        binding.buttonFrom.setOnClickListener {
            requireContext().showMenu(binding.buttonFrom, R.menu.menu_popup_languages)
        }

        binding.buttonTo.setOnClickListener {
            requireContext().showMenu(binding.buttonTo, R.menu.menu_popup_languages)
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package com.theberdakh.from2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.from2.databinding.FragmentFromToUzTranslateBinding

class FromToUzTranslateFragment: Fragment() {
    private var _binding: FragmentFromToUzTranslateBinding?  =null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=  FragmentFromToUzTranslateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
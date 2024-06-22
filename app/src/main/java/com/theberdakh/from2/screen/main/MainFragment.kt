package com.theberdakh.from2.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentMainBinding
import com.theberdakh.from2.screen.translate.TranslateFragment
import com.theberdakh.from2.util.replaceFragment
import com.theberdakh.from2.util.showMenu

class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = checkNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        replaceFragment(
            childFragmentManager,
            R.id.fragment_main_container,
            TranslateFragment()
        )






        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        return true
    }
}
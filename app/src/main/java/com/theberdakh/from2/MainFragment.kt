package com.theberdakh.from2

import android.annotation.SuppressLint
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.theberdakh.from2.databinding.FragmentMainBinding
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

        /* binding.toolbar.setOnMenuItemClickListener{ requireContext().showMenu(binding.toolbar, R.menu.menu_from_to_uz) }
 */
        binding.toolbar.setOnMenuItemClickListener{it ->
            if (it.itemId == R.id.action_export){
                requireContext().showMenu(binding.toolbar, R.menu.menu_from_to_uz)
            }
            true
        }

        /*  binding.toolbar.setOnMenuItemClickListener {
              requireContext().showMenu(binding.toolbar, R.menu.menu_from_to_uz)
          }*/

        replaceFragment(
            childFragmentManager,
            R.id.fragment_main_container,
            FromToUzTranslateFragment()
        )

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.fragmentMainNavigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            binding.drawerLayout.close()
            true
        }




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
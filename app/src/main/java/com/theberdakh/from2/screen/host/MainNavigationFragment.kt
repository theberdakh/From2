package com.theberdakh.from2.screen.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentNavigationMainBinding
import com.theberdakh.from2.screen.translate.TranslateFragment
import com.theberdakh.from2.util.addFragment
import com.theberdakh.from2.util.replaceFragment
import timber.log.Timber

class MainNavigationFragment : Fragment() {
    private var _binding: FragmentNavigationMainBinding? = null
    private val binding get() = checkNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      Timber.i("onCreateView()")
        _binding = FragmentNavigationMainBinding.inflate(inflater, container, false)

        replaceFragment(
            childFragmentManager,
            R.id.fragment_main_container,
            TranslateFragment()
        )

        binding.toolbar.setNavigationOnClickListener {
            addFragment(
                requireActivity().supportFragmentManager,
                R.id.activity_main_container,
                RegisterNavigationFragment(),
                "RegisterNavigationFragment"
            )

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
        _binding = null
        super.onDestroyView()
        Timber.i("onDestroyView()")
    }
}
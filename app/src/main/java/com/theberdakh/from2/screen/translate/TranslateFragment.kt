package com.theberdakh.from2.screen.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.presentation.TranslateViewModel
import com.theberdakh.from2.util.showPopUpMenuWithIcons
import com.theberdakh.from2.util.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val translateViewModel by viewModel<TranslateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)

        initObservers()
        initSelectLanguage()
        initTranslate()

        return binding.root
    }

    private fun initObservers() {
        translateViewModel.translateResponseSuccess.onEach {
            binding.editTextBottomInput.setText(it.result)
        }.launchIn(lifecycleScope)

        translateViewModel.translateResponseMessage.onEach {
            requireContext().showToast(it)
        }

        translateViewModel.translateResponseError.onEach {
            it.printStackTrace()
        }
    }

    private fun initTranslate() {
        binding.buttonTranslate.setOnClickListener {
            val text = binding.editTextTopInput.text.toString()
            lifecycleScope.launch {
                translateViewModel.translate(langFrom = "uz", langTo = "kaa", text)
            }
        }
    }

    private fun initSelectLanguage() {

        binding.buttonFrom.setOnClickListener { requireActivity().showPopUpMenuWithIcons(binding.buttonFrom, R.menu.menu_popup_languages) }
        binding.buttonTo.setOnClickListener { requireActivity().showPopUpMenuWithIcons(binding.buttonTo, R.menu.menu_popup_languages) }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
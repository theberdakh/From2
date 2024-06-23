package com.theberdakh.from2.screen.translate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.presentation.TranslateViewModel
import com.theberdakh.from2.remote.translate.TranslateLanguages
import com.theberdakh.from2.util.showPopUpMenuWithIcons
import com.theberdakh.from2.util.showToast
import com.theberdakh.fromtouz.ResultData
import com.theberdakh.fromtouz.translate
import com.theberdakh.fromtouz.translateState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val translateViewModel by viewModel<TranslateViewModel>()
    private lateinit var _fromLanguage: TranslateLanguages
    private lateinit var _toLanguage: TranslateLanguages

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)

        _fromLanguage = TranslateLanguages.UZBEK
        _toLanguage = TranslateLanguages.KARAKALPAK
        initObservers()
        initSelectLanguage()
        initTranslate()

        lifecycleScope.launch {
            translateState(TranslateLanguages.ENGLISH_LATIN.code,
                TranslateLanguages.KARAKALPAK.code,
                "What a wonderful morning!",
                onSuccess = { text ->
                    Log.i("OnSuccess", "text: $text")
                    binding.editTextBottomInput.setText(text)
                },
                onMessage = { message ->
                    Log.i("OnMessage", "text: $message")
                    binding.editTextBottomInput.setText(message)
                },
                onError = { error ->
                    error.printStackTrace()
                })
        }

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
                translateViewModel.translate(
                    langFrom = _fromLanguage.code,
                    langTo = _toLanguage.code,
                    text
                )
            }
        }
    }

    private fun initSelectLanguage() {

        binding.buttonFrom.setOnClickListener {
            requireActivity().showPopUpMenuWithIcons(
                binding.buttonFrom,
                R.menu.menu_popup_languages
            ) { menuItem ->
                _fromLanguage = when (menuItem.itemId) {
                    R.id.pop_up_action_karakalpak ->
                        TranslateLanguages.KARAKALPAK

                    R.id.pop_up_action_uzbek -> TranslateLanguages.UZBEK
                    else -> TranslateLanguages.UZBEK
                }
                true
            }
        }
        binding.buttonTo.setOnClickListener {
            requireActivity().showPopUpMenuWithIcons(
                binding.buttonTo,
                R.menu.menu_popup_languages
            ) { menuItem ->
                _toLanguage = when (menuItem.itemId) {
                    R.id.pop_up_action_karakalpak -> TranslateLanguages.KARAKALPAK
                    R.id.pop_up_action_uzbek -> TranslateLanguages.UZBEK
                    else -> TranslateLanguages.UZBEK
                }
                true
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
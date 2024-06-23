package com.theberdakh.from2.screen.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.theberdakh.from2.R
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.util.showPopUpMenuWithIcons
import com.theberdakh.fromtouz.translate
import com.theberdakh.fromtouz.translate.TranslateLanguage
import kotlinx.coroutines.launch

class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = checkNotNull(_binding)
    private lateinit var _fromLanguage: TranslateLanguage
    private lateinit var _toLanguage: TranslateLanguage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)

        _fromLanguage = TranslateLanguage.UZBEK
        _toLanguage = TranslateLanguage.KARAKALPAK
        initSelectLanguage()
        initTranslate()

        return binding.root
    }


    private fun initTranslate() {
        binding.buttonTranslate.setOnClickListener {

            val text = "What a wonderful morning!"

            lifecycleScope.launch {
                translate(TranslateLanguage.ENGLISH_LATIN,
                    TranslateLanguage.KARAKALPAK,
                    text = text,
                    onSuccess = { text ->
                        binding.editTextBottomInput.setText(text)
                    },
                    onMessage = { message ->
                        binding.editTextBottomInput.setText(message)
                    },
                    onError = { error ->
                        error.printStackTrace()
                    })
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
                        TranslateLanguage.KARAKALPAK

                    R.id.pop_up_action_uzbek -> TranslateLanguage.UZBEK
                    else -> TranslateLanguage.UZBEK
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
                    R.id.pop_up_action_karakalpak -> TranslateLanguage.KARAKALPAK
                    R.id.pop_up_action_uzbek -> TranslateLanguage.UZBEK
                    else -> TranslateLanguage.UZBEK
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
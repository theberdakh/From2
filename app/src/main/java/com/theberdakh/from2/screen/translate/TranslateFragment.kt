package com.theberdakh.from2.screen.translate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.theberdakh.from2.R
import com.theberdakh.from2.data.Language
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.util.showPopUpMenuWithIcons
import com.theberdakh.from2.util.showUpMenu
import com.theberdakh.fromtouz.getAllTranslateLanguages
import com.theberdakh.fromtouz.translate
import com.theberdakh.fromtouz.translate.TranslateLanguage
import kotlinx.coroutines.launch

class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = checkNotNull(_binding)
    private lateinit var _fromLanguage: TranslateLanguage
    private lateinit var _toLanguage: TranslateLanguage

    companion object {
        const val TAG = "TranslateFragment"
    }

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

        binding.viewDeleteContentTopInput.setOnClickListener {
            binding.editTextTopInput.setText("")
        }

        binding.buttonTranslate.setOnClickListener {

            val text = binding.editTextTopInput.text.toString()

            lifecycleScope.launch {
                translate(_fromLanguage, _toLanguage,
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

        val allLanguages = getAllTranslateLanguages().map {
            Language(it.name, it.ordinal)
        }

        binding.buttonFrom.setOnClickListener {
            requireContext().showUpMenu(binding.buttonFrom, allLanguages){ ordinal, title ->
                binding.buttonFrom.text = title
                _fromLanguage = TranslateLanguage.valueOf(title.toString())
                true
            }
        }
        binding.buttonTo.setOnClickListener {
            requireContext().showUpMenu(binding.buttonFrom, allLanguages){ ordinal, title ->
                binding.buttonFrom.text = title
                _toLanguage = TranslateLanguage.valueOf(title.toString())
                true
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
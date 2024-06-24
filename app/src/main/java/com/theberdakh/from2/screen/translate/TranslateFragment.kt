package com.theberdakh.from2.screen.translate

import android.R.attr.label
import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.theberdakh.from2.R
import com.theberdakh.from2.data.Language
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.util.showSnackbar
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

        binding.editTextTopInput.addTextChangedListener {
            translateText()
        }

        binding.buttonTranslate.setOnClickListener {
            translateText()
        }
    }

    private fun translateText() {
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

    private fun initSelectLanguage() {

        val allLanguages = getAllTranslateLanguages().map {
            Language(it.name, it.ordinal)
        }

        binding.buttonFrom.setOnClickListener {
            requireContext().showUpMenu(binding.buttonFrom, allLanguages) { ordinal, title ->
                binding.buttonFrom.text = title
                _fromLanguage = TranslateLanguage.valueOf(title.toString())
                translateText()
                true
            }
        }
        binding.buttonTo.setOnClickListener {
            requireContext().showUpMenu(binding.buttonFrom, allLanguages) { ordinal, title ->
                binding.buttonFrom.text = title
                _toLanguage = TranslateLanguage.valueOf(title.toString())
                translateText()
                true
            }
        }

        binding.viewCopy.setOnClickListener {
            val textToCopy = binding.editTextBottomInput.text.toString()
            val clipboard: ClipboardManager? = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("From2", textToCopy)
            clipboard?.setPrimaryClip(clip)
            binding.viewCopy.showSnackbar("Copied to your clipboard", R.drawable.round_content_copy_24)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
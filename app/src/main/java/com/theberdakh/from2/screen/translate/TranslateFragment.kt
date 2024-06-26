package com.theberdakh.from2.screen.translate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
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
import timber.log.Timber


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

        Timber.i("onCreateView()")

        _fromLanguage = TranslateLanguage.UZBEK
        _toLanguage = TranslateLanguage.KARAKALPAK


        initViews()

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


    private fun translateText(text: String) {

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

    private fun initViews() {

        val allLanguages = getAllTranslateLanguages().map {
            Language(it.name, it.ordinal)
        }

        binding.buttonFrom.setOnClickListener {


            requireContext().showUpMenu(binding.buttonFrom, allLanguages) { ordinal, title ->
                binding.buttonFrom.text = title
                _fromLanguage = TranslateLanguage.valueOf(title.toString())
                translateText(binding.editTextTopInput.text.toString())
                true
            }
        }
        binding.buttonTo.setOnClickListener {
            requireContext().showUpMenu(binding.buttonFrom, allLanguages) { ordinal, title ->
                binding.buttonFrom.text = title
                _toLanguage = TranslateLanguage.valueOf(title.toString())
                translateText(binding.editTextTopInput.text.toString())
                true
            }
        }

        binding.viewDeleteContentTopInput.setOnClickListener {
            binding.editTextTopInput.setText("")
        }

        binding.editTextTopInput.doAfterTextChanged { text: Editable? ->
            Log.d("TranslateFragment", "EditText: doAfter: $text")
            translateText(text.toString())
        }



        binding.buttonTranslate.setOnClickListener {
            translateText(binding.editTextTopInput.text.toString())
        }

        binding.viewCopy.setOnClickListener {
            val textToCopy = binding.editTextBottomInput.text.toString()
            val clipboard: ClipboardManager? = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("From2", textToCopy)
            clipboard?.setPrimaryClip(clip)
            binding.viewCopy.showSnackbar("Copied to your clipboard", R.drawable.round_content_copy_24)
        }

        binding.viewShare.setOnClickListener {
            val textToShare = binding.editTextBottomInput.text.toString()

            val sendIntent = Intent()
            sendIntent.setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
            sendIntent.setType("text/plain")
            startActivity(sendIntent)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        Timber.i("onDestroyView()")
    }
}
package com.theberdakh.from2.screen.translate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.theberdakh.from2.R
import com.theberdakh.from2.data.Language
import com.theberdakh.from2.databinding.FragmentTranslateBinding
import com.theberdakh.from2.presentation.TranslateFragmentViewModel
import com.theberdakh.from2.util.NetworkConnectionRepoFactory
import com.theberdakh.from2.util.NetworkConnectionRepository
import com.theberdakh.from2.util.showSnackbar
import com.theberdakh.from2.util.showUpMenu
import com.theberdakh.fromtouz.getAllTranslateLanguages
import com.theberdakh.fromtouz.translate
import com.theberdakh.fromtouz.translate.TranslateLanguage
import com.theberdakh.fromtouz.transliterate
import com.theberdakh.fromtouz.transliterate.TransliterateLanguage
import kotlinx.coroutines.launch


class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = checkNotNull(_binding)
    private lateinit var _fromLanguage: TranslateLanguage
    private lateinit var _toLanguage: TranslateLanguage
    private lateinit var connectivityManager: ConnectivityManager
    private val viewModel: TranslateFragmentViewModel by viewModels {NetworkConnectionRepoFactory(
        NetworkConnectionRepository(connectivityManager)
    ) }

    companion object {
        const val TAG = "TranslateFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)

        connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        _fromLanguage = TranslateLanguage.UZBEK
        _toLanguage = TranslateLanguage.KARAKALPAK


        initViews()

        return binding.root
    }


    private fun translateText(text: String) {


        viewModel.isOnline.observe(requireActivity()) { isOnline ->
            if (isOnline){
                lifecycleScope.launch {
                    translate(_fromLanguage, _toLanguage,
                        text = text,
                        onSuccess = { text ->
                            Log.i(TAG, "result: $text")
                            binding.editTextBottomInput.isVisible = true
                            binding.editTextBottomInput.setText(text)
                        },
                        onMessage = { message ->
                            Log.i(TAG, "result: $message")
                            binding.editTextBottomInput.setText(message)
                        },
                        onError = { error ->
                            error.printStackTrace()
                        })
                }
            } else {
                binding.buttonTranslate.showSnackbar("Check your Internet connection")
            }
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

        binding.viewDelete.setOnClickListener {
            binding.editTextTopInput.setText("")
        }


        binding.editTextTopInput.doAfterTextChanged { text: Editable? ->
            Log.d(TAG, "EditText: doAfter: $text")
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
    }
}
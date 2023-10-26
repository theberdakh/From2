package com.theberdakh.from2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.theberdakh.from2.network.From2Api
import com.theberdakh.from2.data.Languages
import com.theberdakh.from2.network.RetrofitClient
import com.theberdakh.from2.data.remote.Body
import com.theberdakh.from2.data.remote.TranslateRequest
import com.theberdakh.from2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val models = arrayOf("Uzbek", "Karakalpak")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initViews()
        initListeners()

    }

    private fun initListeners() {

        binding.toolbar.setNavigationOnClickListener {
            val reviewManager = ReviewManagerFactory.create(this)
            reviewManager.requestReviewFlow().addOnCompleteListener {
                if (it.isSuccessful){
                    reviewManager.launchReviewFlow(this, it.result)
                    Snackbar.make(this, binding.etFromText, "Join us on Telegram t.me/theberdakh", Snackbar.LENGTH_LONG).show()
                }
            }
        }


        val autos = listOf(binding.autoFirstSign, binding.autoSecondSign)

        binding.ivReverse.setOnClickListener {
            val languages = listOf(
                binding.autoFirstSign.text.toString(),
                binding.autoSecondSign.text.toString()
            )
            binding.autoFirstSign.setText(languages[1], false)
            binding.autoSecondSign.setText(languages[0], false)
            translate(
                binding.autoFirstSign.text.toString().convert(),
                binding.autoSecondSign.text.toString().convert(),
                binding.etFromText.text.toString()
            )

        }

        autos.onEach {
            it.setOnItemClickListener { parent, view, position, id ->
                translate(
                    binding.autoFirstSign.text.toString().convert(),
                    binding.autoSecondSign.text.toString().convert(),
                    binding.etFromText.text.toString()
                )
            }
        }

        binding.etFromText.doAfterTextChanged {
            binding.chipPaste.isVisible = false
            translate(
                binding.autoFirstSign.text.toString().convert(),
                binding.autoSecondSign.text.toString().convert(),
                binding.etFromText.text.toString()
            )
            binding.chipClear.isVisible = it.toString().length > 2
        }

        binding.chipClear.setOnClickListener {
            binding.etFromText.setText("")
            binding.etToText.setText("")
        }


        var isLatin = true
        binding.chipTransliterate.setOnClickListener {
            val text = binding.etToText.text.toString()
            binding.etToText.setText("...")
            if (isLatin) {
                binding.chipTransliterate.text = "Back to Latin"
                binding.chipTransliterate.chipIcon =
                    AppCompatResources.getDrawable(this, R.drawable.round_keyboard_return_24)
                transliterate(Languages.LATIN, Languages.CYRILLIC, text)
                isLatin = false
            } else {
                binding.chipTransliterate.text = "To Cyrillic"
                binding.chipTransliterate.chipIcon =
                    AppCompatResources.getDrawable(this, R.drawable.round_translate_24)
                transliterate(Languages.CYRILLIC, Languages.LATIN, text)
                isLatin = true
            }
        }


        binding.chipPaste.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            binding.etFromText.setText(clipboardManager.primaryClip?.getItemAt(0)?.text)
            translate(
                binding.autoFirstSign.text.toString().convert(),
                binding.autoSecondSign.text.toString().convert(),
                binding.etFromText.text.toString()
            )
        }

        binding.chipCopy.setOnClickListener {
            val textToCopy = binding.etToText.text.toString()
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
        }

        var isEditable = false
        binding.chipEdit.setOnClickListener {
            if (isEditable){
                binding.etToText.inputType = InputType.TYPE_NULL
                binding.etFromText.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
                binding.chipEdit.text = "Edit"
                binding.etFromText.requestFocus()
                binding.chipEdit.chipIcon = AppCompatResources.getDrawable(this, R.drawable.round_edit_24)
                isEditable = false
            } else {
                binding.etToText.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
                binding.etFromText.inputType = InputType.TYPE_NULL
                binding.etToText.requestFocus()
                binding.chipEdit.text = "Stop editing"
                binding.chipEdit.chipIcon = AppCompatResources.getDrawable(this, R.drawable.round_close_24)
                isEditable = true
            }
            binding.etFromText.clearFocus()
            binding.etToText.requestFocus()
        }

    }

    private fun initViews() {
        val n = listOf(1,2,3,4,5)
        n.lastIndex
        n.sortedDescending().sum()
        binding.chipEdit.isVisible = false
        binding.chipTransliterate.isVisible = false
        binding.chipCopy.isVisible = false

         val firstAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, models)
        val secondAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, models)

        binding.autoFirstSign.setAdapter(firstAdapter)
        binding.autoSecondSign.setAdapter(secondAdapter)

        binding.autoFirstSign.setText(firstAdapter.getItem(0).toString(), false)
        binding.autoSecondSign.setText(secondAdapter.getItem(1).toString(), false)

        binding.chipTransliterate.text = "To Cyrillic"
        binding.chipTransliterate.chipIcon =
            AppCompatResources.getDrawable(this, R.drawable.round_translate_24)

        binding.etToText.inputType = InputType.TYPE_NULL
    }

    private fun transliterate(from: Languages, to: Languages, text: String) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(From2Api::class.java)
        val choice = binding.autoSecondSign.text.toString()


        lifecycleScope.launch {
            try {
                val response = apiInterface.transliterate(
                    TranslateRequest(
                        Body(
                            from.convert(), to.convert(), text
                        )
                    )
                )

                if (response.isSuccessful) {
                    binding.etToText.setText(response.body()?.result)

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }

    private fun translate(from: Languages, to: Languages, text: String) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(From2Api::class.java)
        lifecycleScope.launch {
            try {
                val response = apiInterface.translate(
                    TranslateRequest(
                        Body(
                            from.convert(), to.convert(), text
                        )
                    )
                )
                if (response.isSuccessful) {
                    if (binding.etFromText.text.toString().isNotEmpty()) {
                        binding.etToText.setText(response.body()?.result)
                        binding.chipTransliterate.isVisible = true
                        binding.chipEdit.isVisible = true
                        binding.chipCopy.isVisible = true
                    } else {
                        binding.etToText.setText("")
                        binding.chipTransliterate.isVisible = false
                        binding.chipEdit.isVisible = false
                        binding.chipCopy.isVisible = false
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }


    }


}

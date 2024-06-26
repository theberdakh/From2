package com.theberdakh.from2

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.theberdakh.from2.databinding.ActivityMainBinding
import com.theberdakh.from2.util.setFullscreen
import com.theberdakh.from2.util.setOnlyLightMode
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate()")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnlyLightMode()
        setFullscreen()
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart()")
    }


    override fun onStop() {
        super.onStop()
        Timber.i("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
    }


}

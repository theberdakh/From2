package com.theberdakh.from2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.theberdakh.from2.databinding.ActivityMainBinding
import com.theberdakh.from2.util.setFullscreen
import com.theberdakh.from2.util.setOnlyLightMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnlyLightMode()
        setFullscreen()


    }



}

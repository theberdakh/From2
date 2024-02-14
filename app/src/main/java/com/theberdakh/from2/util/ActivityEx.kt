package com.theberdakh.from2.util

import android.app.Activity
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.PopupMenu
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat


fun setOnlyLightMode() {
    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}

fun Activity.setGradientBackground(@DrawableRes resId: Int) {
    val window: Window = this.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    val background = ContextCompat.getDrawable(this, resId)
    window.setBackgroundDrawable(background)
}

fun Activity.setFullscreen(){
    val window: Window = this.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
}



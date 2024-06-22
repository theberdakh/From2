package com.theberdakh.from2.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu

@SuppressLint("RestrictedApi")
fun Context.showPopUpMenuWithIcons(view: View, @MenuRes menuRes: Int) {
    val popUpMenu = PopupMenu(this, view, Gravity.END)
    popUpMenu.menuInflater.inflate(menuRes, popUpMenu.menu)

    if (popUpMenu.menu is MenuBuilder) {
        val menuBuilder = popUpMenu.menu as MenuBuilder
        menuBuilder.setOptionalIconsVisible(true)

        for (item in menuBuilder.visibleItems) {
            val iconMarginPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
            if (item.icon != null) { item.icon = InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx, 0) }
        }
    }

    popUpMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
        Log.d("popUpMenu", " ${menuItem.title} clicked")
        true
    }

    popUpMenu.show()

}
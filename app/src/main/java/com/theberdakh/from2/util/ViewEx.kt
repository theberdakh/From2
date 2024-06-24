package com.theberdakh.from2.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.compose.material.SnackbarDuration
import com.google.android.material.snackbar.Snackbar
import com.theberdakh.from2.R
import com.theberdakh.from2.data.Language


fun Context.showUpMenu(
    anchorView: View,
    listOfLanguage: List<Language>,
    onMenuItemClick: (Int, CharSequence?) -> Boolean
) {
    val popupMenu = android.widget.PopupMenu(this, anchorView)
    val menu = popupMenu.menu
    for (language in listOfLanguage) {
        menu.add(Menu.NONE, language.ordinal, Menu.NONE, language.name)
    }

    popupMenu.setOnMenuItemClickListener { item ->
        when (item.itemId) {

        }
        onMenuItemClick.invoke(item.itemId, item.title)
    }

    popupMenu.show()

}


@SuppressLint("RestrictedApi")
fun Context.showPopUpMenuWithIcons(
    view: View,
    @MenuRes menuRes: Int,
    onMenuItemClick: (MenuItem) -> Boolean
) {
    val popUpMenu = PopupMenu(this, view, Gravity.END)
    popUpMenu.menuInflater.inflate(menuRes, popUpMenu.menu)

    if (popUpMenu.menu is MenuBuilder) {

        val menuBuilder = popUpMenu.menu as MenuBuilder
        menuBuilder.setOptionalIconsVisible(true)

        for (item in menuBuilder.visibleItems) {
            val iconMarginPx =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics)
                    .toInt()
            if (item.icon != null) {
                item.icon = InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx, 0)
            }
        }
    }

    popUpMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
        onMenuItemClick.invoke(menuItem)
    }

    popUpMenu.show()

}

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun View.showSnackbar(text: String, @DrawableRes startIcon : Int = 0, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make(this, text, duration)
    val snackbarLayout = snackbar.view
    val textView =
        snackbarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setCompoundDrawablesWithIntrinsicBounds(startIcon, 0, 0, 0)
    textView.compoundDrawablePadding =
        resources.getDimensionPixelOffset(R.dimen.snackbar_icon_padding)

    snackbar.show()

}
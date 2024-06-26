package com.theberdakh.from2.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun replaceFragment(fragmentManager: FragmentManager, @IdRes fragmentContainer: Int, fragment: Fragment){
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(fragmentContainer, fragment)
    transaction.commit()
}

fun addFragment(fragmentManager: FragmentManager, @IdRes fragmentContainer: Int, fragment: Fragment, tag: String){
    val transaction = fragmentManager.beginTransaction()
    transaction.add(fragmentContainer, fragment, tag)
    transaction.addToBackStack(tag)
    transaction.commit()
}
package com.theberdakh.from2.di

import com.theberdakh.from2.presentation.TranslateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<TranslateViewModel>{
        TranslateViewModel(translateRepository = get())
    }
}
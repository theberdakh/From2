package com.theberdakh.from2.di

import com.theberdakh.from2.domain.TranslateRepository
import org.koin.dsl.module

val appModule = module {
    single<TranslateRepository>{
        TranslateRepository(from2Api = get())
    }


}
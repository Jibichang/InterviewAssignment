package com.wongnai.android.assignment.di

import org.koin.dsl.module

/**
 * @author phompang on 2019-05-26.
 */
val coreModule = module {
    single { MyGsonBuilder().build() }
    single { OkHttpBuilder().build() }
    single { RetrofitBuilder(get(), get()).build() }
    single { ApiBuilder(get()).coinApi() }
}

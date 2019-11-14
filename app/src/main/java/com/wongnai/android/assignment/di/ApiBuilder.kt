package com.wongnai.android.assignment.di

import com.wongnai.android.assignment.api.CoinApi
import retrofit2.Retrofit
import retrofit2.create

/**
 * @author phompang on 2019-05-26.
 */
class ApiBuilder(private val retrofit: Retrofit) {
	fun coinApi(): CoinApi = retrofit.create()
}

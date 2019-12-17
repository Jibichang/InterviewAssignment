package com.wongnai.android.assignment.di

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author phompang on 2019-05-26.
 */
class RetrofitBuilder(
	private val client: OkHttpClient,
	private val gson: Gson
) {
	fun build(): Retrofit {
		return Retrofit.Builder()
			.client(client)
			.baseUrl("https://api.coinranking.com/v1/public/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
	}
}

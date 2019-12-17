package com.wongnai.android.assignment.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author phompang on 2019-05-26.
 */
class OkHttpBuilder {
	fun build(): OkHttpClient {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY
		return OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.build()
	}
}

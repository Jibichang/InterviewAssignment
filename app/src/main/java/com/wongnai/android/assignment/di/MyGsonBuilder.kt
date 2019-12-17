package com.wongnai.android.assignment.di

import com.google.gson.*
import com.google.gson.internal.bind.util.ISO8601Utils
import java.lang.reflect.Type
import java.text.ParseException
import java.text.ParsePosition
import java.util.*

/**
 * @author phompang on 2019-05-26.
 */
class MyGsonBuilder {
	fun build(): Gson {
		val b = GsonBuilder()
		b.registerTypeAdapter(Date::class.java, DateDeserializer())
		return b.create()
	}
}


private class DateDeserializer : JsonDeserializer<Date> {
	@Throws(JsonParseException::class)
	override fun deserialize(
		json: JsonElement?,
		typeOfT: Type,
		context: JsonDeserializationContext
	): Date? {
		return if (json != null && !json.asString.isNullOrBlank()) {
			try {
				ISO8601Utils.parse(json.asString, ParsePosition(0))
			} catch (e: ParseException) {
				null
			}
		} else {
			null
		}
	}
}

package com.wongnai.android.assignment.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.DimenRes

/**
 * @author phompang on 25/2/2019 AD.
 */
fun Context.dpToPx(
	@DimenRes
	dimenRes: Int
) = try {
	this.resources.getDimensionPixelOffset(dimenRes)
} catch (ignore: Resources.NotFoundException) {
	0
}

fun Context.dpToPx(sizeInDp: Float): Int {
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDp, this.resources.displayMetrics)
		.toInt()
}

package com.wongnai.android.assignment.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

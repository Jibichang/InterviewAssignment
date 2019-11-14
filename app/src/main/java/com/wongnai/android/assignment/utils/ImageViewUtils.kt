package com.wongnai.android.assignment.utils

import com.facebook.drawee.view.SimpleDraweeView

/**
 * @author phompang on 12/9/2018 AD.
 */
fun SimpleDraweeView.load(url: String?) {
	if (url == null) {
		return
	}
	this.setImageURI(url)
}

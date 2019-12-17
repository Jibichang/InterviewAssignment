package com.wongnai.android.assignment.di

import org.koin.core.context.loadKoinModules

/**
 * @author phompang on 2019-05-26.
 */
object CoreComponent {
    fun init() = loadKoinModules(listOf(coreModule))
}

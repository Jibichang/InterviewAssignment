package com.wongnai.android.assignment

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.wongnai.android.assignment.di.CoreComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AssignmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AssignmentApplication)
        }
        CoreComponent.init()
        Fresco.initialize(this)
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return "${super.createStackElementTag(element)} : ${element.methodName} (${element.fileName}:${element.lineNumber})"
            }
        })
    }
}

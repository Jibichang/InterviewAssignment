package com.wongnai.android.assignment

import android.app.Application
import com.facebook.drawee.backends.pipeline.DraweeConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.decoder.ImageDecoderConfig
import com.wongnai.android.assignment.di.CoreComponent
import com.wongnai.android.assignment.utils.SvgDecoderExample
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
        val imageDecoderConfig = ImageDecoderConfig.newBuilder()
            .addDecodingCapability(
                SvgDecoderExample.SVG_FORMAT,
                SvgDecoderExample.SvgFormatChecker(),
                SvgDecoderExample.SvgDecoder()
            )
            .build()

        val config = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true)
            .setImageDecoderConfig(imageDecoderConfig)
            .build()

        val draweeConfig = DraweeConfig.newBuilder()
            .addCustomDrawableFactory(SvgDecoderExample.SvgDrawableFactory())
            .build()

        Fresco.initialize(this, config, draweeConfig)
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return "${super.createStackElementTag(element)} : ${element.methodName} (${element.fileName}:${element.lineNumber})"
            }
        })
    }
}

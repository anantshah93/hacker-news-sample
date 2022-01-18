package io.app.test.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.app.test.news.data.helper.attachToDebugger

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            attachToDebugger()
        }
    }
}

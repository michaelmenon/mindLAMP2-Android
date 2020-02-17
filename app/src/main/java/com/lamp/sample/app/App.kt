package com.lamp.sample.app

import android.app.Application
import com.lamp.sample.appstate.Pref

class App: Application() {

    companion object {
        lateinit var app: App
    }
    override fun onCreate() {
        super.onCreate()
        app = this

        // Initializing Shared pref
        Pref.init(this, AppKeys.APP_PREF_NAME)
    }
}
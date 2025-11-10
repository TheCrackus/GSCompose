package com.example.gscompose

import android.app.Application
import com.example.gscompose.data.di.networkModule
import com.example.gscompose.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GSApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Here we need to init koin and the
         * modules we created in UiModule and
         * NetworkModule classes.
         *
         * Do not forget to add the context androidContext
         * and the logger androidLogger.
         *
         * Also do not forget to add the android name
         * property on the manifest.
         * **/
        startKoin{
            androidContext(this@GSApplication)
            androidLogger()
            modules(
                uiModule,
                networkModule
            )
        }
    }
}

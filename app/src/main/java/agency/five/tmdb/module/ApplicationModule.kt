package agency.five.tmdb.module

import agency.five.tmdb.BuildConfig
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

//TODO: edit if needed

class ApplicationModule() : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ApplicationModule)
            modules(movieModule)
        }
    }
}


package agency.five.tmdb

import agency.five.tmdb.data.api.di.apiModule
import agency.five.tmdb.data.database.di.databaseModule
import agency.five.tmdb.data.repository.di.repositoryModule
import agency.five.tmdb.di.appModule
import agency.five.tmdb.di.useCaseModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class TmdbApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TmdbApplication)
            modules(appModule, databaseModule, apiModule, repositoryModule, useCaseModule)
        }
    }
}


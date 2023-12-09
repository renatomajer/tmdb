package agency.five.tmdb.data.database.di

import agency.five.tmdb.data.database.DB_NAME
import agency.five.tmdb.data.database.IFavoriteMoviesDao
import agency.five.tmdb.data.database.TmdbDatabase
import agency.five.tmdb.data.database.mapper.MovieMapper
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            TmdbDatabase::class.java,
            DB_NAME
        ).build()
    }

    single<IFavoriteMoviesDao> {
        val database: TmdbDatabase = get()
        database.favoriteMovieDao()
    }

    single {
        MovieMapper()
    }
}
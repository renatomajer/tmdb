package agency.five.tmdb.data.database


import agency.five.tmdb.domain.common.Actor
import agency.five.tmdb.domain.common.PersonFunction
import agency.five.tmdb.presentation.ui.components.MovieItemViewState
import android.content.Context
import androidx.room.*


@Database(
    entities = [MovieItemViewState::class, Actor::class, PersonFunction::class, MoviePersonsFunctions::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun actorsDao(): ActorsDao

    abstract fun moviePersonsFunctionsDao(): MoviePersonsFunctionsDao

    abstract fun personFunctionsDao(): PersonsFunctionsDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "favorites_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
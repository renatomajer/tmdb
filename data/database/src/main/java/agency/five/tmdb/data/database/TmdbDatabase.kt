package agency.five.tmdb.data.database


import androidx.room.*


@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TmdbDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMoviesDao
}

const val DB_NAME = "tmdb_database"
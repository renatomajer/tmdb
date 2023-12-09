package agency.five.tmdb.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteMoviesDao : IFavoriteMoviesDao {

    @Query("SELECT * FROM favorite_movies")
    abstract override fun getAll(): Flow<List<FavoriteMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract override fun insertMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    abstract override fun deleteMovie(favoriteMovieEntity: FavoriteMovieEntity)
}
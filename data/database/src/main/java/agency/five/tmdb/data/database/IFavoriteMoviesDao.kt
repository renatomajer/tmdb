package agency.five.tmdb.data.database

import kotlinx.coroutines.flow.Flow

interface IFavoriteMoviesDao {

    fun getAll(): Flow<List<FavoriteMovieEntity>>

    fun insertMovie(favoriteMovieEntity: FavoriteMovieEntity)

    fun deleteMovie(favoriteMovieEntity: FavoriteMovieEntity)
}
package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState

interface MovieApi {
    suspend fun getPopularMovies(): List<List<MovieItemViewState>>

    suspend fun getTrendingMovies(): List<List<MovieItemViewState>>

    suspend fun getFreeMovies(): List<List<MovieItemViewState>>

    suspend fun getMovie(movieId: Int): MovieItemViewState

    suspend fun getPersonFunctions(movieId: Int): List<PersonFunction>

    suspend fun getActors(movieId: Int): List<Actor>

    suspend fun updateMovie(movie: MovieItemViewState, isFavorite: Boolean)
}
package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState


interface MovieApi {
    suspend fun getPopularMovies(): List<List<MovieItemViewState>>

    suspend fun getPopularForRent(): List<MovieItemViewState> //Get a list of the current popular movies on TMDB

    suspend fun getPopularInTheaters(): List<MovieItemViewState> //Get a list of movies in theatres

    suspend fun getPopularStreaming(): List<MovieItemViewState>

    suspend fun getPopularOnTV(): List<MovieItemViewState>

    suspend fun getTrendingMovies(): List<List<MovieItemViewState>>

    suspend fun getTrendingToday(): List<MovieItemViewState> //Get the daily trending movies

    suspend fun getTrendingThisWeek(): List<MovieItemViewState> //Get the weekly trending movies

    suspend fun getFreeMovies(): List<List<MovieItemViewState>>

    suspend fun getTopRatedMovies(): List<MovieItemViewState> //Get the top rated movies on TMDB

    suspend fun getUpcomingMovies(): List<MovieItemViewState>

    suspend fun getMovie(movieId: Int): MovieItemViewState

    suspend fun getPersonFunctions(movieId: Int): List<PersonFunction>

    suspend fun getActors(movieId: Int): List<Actor>
}

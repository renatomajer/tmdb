package agency.five.tmdb.data.api


import agency.five.tmdb.data.api.dto.CreditsDto
import agency.five.tmdb.data.api.dto.MoviesResponse
import agency.five.tmdb.data.api.dto.movie.details.MovieDetailsDto


interface MovieApi {

    // Popular
    suspend fun getPopularForRent(): MoviesResponse //Get a list of the current popular movies on TMDB

    suspend fun getPopularInTheaters(): MoviesResponse //Get a list of movies in theatres

    suspend fun getPopularStreaming(): MoviesResponse

    suspend fun getPopularOnTV(): MoviesResponse

    // Free
    suspend fun getTopRatedMovies(): MoviesResponse //Get the top rated movies on TMDB

    suspend fun getUpcomingMovies(): MoviesResponse

    // Trending
    suspend fun getTodayTrendingMovies(): MoviesResponse //Get the daily trending movies

    suspend fun getThisWeekTrendingMovies(): MoviesResponse //Get the weekly trending movies

    // Other...
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto

    suspend fun getCredits(movieId: Int): CreditsDto
}

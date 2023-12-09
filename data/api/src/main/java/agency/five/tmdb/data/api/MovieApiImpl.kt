package agency.five.tmdb.data.api

import agency.five.tmdb.data.api.dto.CreditsDto
import agency.five.tmdb.data.api.dto.MoviesResponse
import agency.five.tmdb.data.api.dto.movie.details.MovieDetailsDto
import agency.five.tmdb.domain.common.Constants.API_BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class MovieApiImpl(
    private val httpClient: HttpClient
) : MovieApi {

    // Popular
    override suspend fun getPopularForRent(): MoviesResponse {
        return httpClient.get("${API_BASE_URL}movie/popular?api_key=$API_KEY").body()
    }

    override suspend fun getPopularInTheaters(): MoviesResponse {
        return httpClient.get("${API_BASE_URL}movie/now_playing?api_key=$API_KEY").body()
    }

    override suspend fun getPopularStreaming(): MoviesResponse {
        // now playing movies on page=2
        return httpClient.get("${API_BASE_URL}movie/now_playing?api_key=$API_KEY&page=2").body()
    }

    override suspend fun getPopularOnTV(): MoviesResponse {
        // popular movies on page=2
        return httpClient.get("${API_BASE_URL}movie/popular?api_key=$API_KEY&page=2").body()
    }

    // Free
    override suspend fun getTopRatedMovies(): MoviesResponse {
        return httpClient.get("${API_BASE_URL}movie/top_rated?api_key=$API_KEY").body()
    }

    override suspend fun getUpcomingMovies(): MoviesResponse {
        return httpClient.get("${API_BASE_URL}movie/upcoming?api_key=$API_KEY").body()
    }

    // Trending
    override suspend fun getTodayTrendingMovies(): MoviesResponse {
        return httpClient.get("${API_BASE_URL}trending/movie/day?api_key=$API_KEY").body()
    }

    override suspend fun getThisWeekTrendingMovies(): MoviesResponse {
        return httpClient.get("${API_BASE_URL}trending/movie/week?api_key=$API_KEY").body()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return httpClient.get("${API_BASE_URL}movie/$movieId?api_key=$API_KEY").body()
    }

    override suspend fun getCredits(movieId: Int): CreditsDto {
        return httpClient.get("${API_BASE_URL}movie/$movieId/credits?api_key=$API_KEY").body()
    }

    // TODO: find a better way to safely store the api key
    companion object {
        private const val API_KEY = "0d756c167ac7d8a9afae24e0d3af8a9e"
    }
}
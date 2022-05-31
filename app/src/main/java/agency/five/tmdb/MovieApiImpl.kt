package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import io.ktor.client.*
import io.ktor.client.request.*


class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {

    private val apiKey: String = "0d756c167ac7d8a9afae24e0d3af8a9e"

    override suspend fun getPopularMovies(): List<List<MovieItemViewState>> {   // size: 4 (tabs)

        val forRent = getPopularForRent().subList(0, 10)

        val inTheaters = getPopularInTheaters().subList(0, 10)

        val onTV = getPopularOnTV().subList(0, 10)

        val streaming = getPopularStreaming().subList(0, 10)

        return listOf(streaming, onTV, forRent, inTheaters)
    }

    override suspend fun getPopularForRent(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/popular?api_key=$apiKey").movies
    }

    override suspend fun getPopularInTheaters(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/now_playing?api_key=$apiKey").movies
    }

    override suspend fun getPopularStreaming(): List<MovieItemViewState> {
        // now playing movies on page=2
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/now_playing?api_key=$apiKey&page=2").movies
    }

    override suspend fun getPopularOnTV(): List<MovieItemViewState> {
        // popular movies on page=2
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/popular?api_key=$apiKey&page=2").movies
    }


    override suspend fun getTrendingMovies(): List<List<MovieItemViewState>> {  // size: 2 (tabs)
        return listOf(getTrendingToday().subList(0, 10), getTrendingThisWeek().subList(0, 10))
    }

    override suspend fun getTrendingToday(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/trending/movie/day?api_key=$apiKey").movies
    }

    override suspend fun getTrendingThisWeek(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/trending/movie/week?api_key=$apiKey").movies
    }

    override suspend fun getFreeMovies(): List<List<MovieItemViewState>> {  // size: 2 (tabs)
        return listOf(getUpcomingMovies().subList(0, 10), getTopRatedMovies().subList(0, 10))
    }

    override suspend fun getTopRatedMovies(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey").movies
    }

    override suspend fun getUpcomingMovies(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/upcoming?api_key=$apiKey").movies
    }

    override suspend fun getMovie(movieId: Int): MovieItemViewState {
        return client.get<MovieItemViewState>("https://api.themoviedb.org/3/movie/$movieId?api_key=$apiKey")
    }

    override suspend fun getPersonFunctions(movieId: Int): List<PersonFunction> {
        val result =
            client.get<PersonsFunctionsResponse>("https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$apiKey").personsFunctions

        return if (result.size > 6) result.subList(0, 6)
        else result
    }

    override suspend fun getActors(movieId: Int): List<Actor> {
        val result =
            client.get<ActorResponse>("https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$apiKey").actors

        return if (result.size > 6) result.subList(0, 6)
        else result
    }

}
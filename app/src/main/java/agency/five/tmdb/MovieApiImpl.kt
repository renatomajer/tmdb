package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import android.util.Log
import io.ktor.client.*
import io.ktor.client.request.*


class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {

    private val API_KEY = "0d756c167ac7d8a9afae24e0d3af8a9e"

    /*
    private val m0 = MovieItemViewState(
        id = 0,
        title = "Iron Man 1",
        overview = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
        imageResId = R.drawable.iron_man_1,
        release_date = "05/02/2008",
        vote_average = 7.6,
        original_language = "US",
        duration = "2h 6m",
        genres = mutableListOf("Action", "Science Fiction", "Adventure")
    )

    private val m1 = MovieItemViewState(
        id = 1,
        title = "Gattaca",
        imageResId = R.drawable.gattaca
    )

    private val m2 = MovieItemViewState(
        id = 2,
        title = "Lion King",
        imageResId = R.drawable.lion_king
    )

    private val m3 = MovieItemViewState(
        id = 3,
        title = "Puppy Love",
        imageResId = R.drawable.puppy_love
    )

    private val m4 = MovieItemViewState(
        id = 4,
        title = "Godzila",
        imageResId = R.drawable.godzila
    )

    private val m5 = MovieItemViewState(
        id = 5,
        title = "Jungle Beat",
        imageResId = R.drawable.jungle_beat
    )

    private val l1 = listOf(m0, m1, m2, m3)

    private val l2 = listOf(m0, m1)

    private val l3 = listOf(m4, m5)

     */

    /*
    // Iron Man 1 persons functions
    private val p1 = PersonFunction(
        name = "Don",
        surname = "Heck",
        movieFunction = "Characters"
    )

    private val p2 = PersonFunction(
        name = "Jack",
        surname = "Kirby",
        movieFunction = "Characters"
    )

    private val p3 = PersonFunction(
        name = "Jon",
        surname = "Favreau",
        movieFunction = "Director"
    )

    private val p4 = PersonFunction(
        name = "Don",
        surname = "Heck",
        movieFunction = "Screenplay"
    )

    private val p5 = PersonFunction(
        name = "Jack",
        surname = "Marcum",
        movieFunction = "Screenplay"
    )

    private val p6 = PersonFunction(
        name = "Matt",
        surname = "Holloway",
        movieFunction = "Screenplay"
    )

    // Iron Man 1 actors
    private val a1 = Actor(
        movie = "Iron Man 1",
        name = "Robert",
        surname = "Downey Jr.",
        role = "Tony Stark/Iron Man",
        imageResId = R.drawable.robert_downey
    )

    private val a2 = Actor(
        movie = "Iron Man 1",
        name = "Terrence",
        surname = "Howard",
        role = "James Rhodes",
        imageResId = R.drawable.terrence_howard
    )

    private val a3 = Actor(
        movie = "Iron Man 1",
        name = "Jeff",
        surname = "Bridges",
        role = "Obadiah Stane / Iron Monger",
        imageResId = R.drawable.jeff_bridges
    )

     */


    override suspend fun getPopularMovies(): List<List<MovieItemViewState>> {   // size: 4 (tabs)

        val forRent = getPopularForRent().subList(0, 10)

        val inTheaters = getPopularInTheaters().subList(0, 10)

        val onTV = getPopularOnTV().subList(0, 10)

        val streaming = getPopularStreaming().subList(0, 10)


        //return listOf(l1, l2, l3, l1)
        return listOf(streaming, onTV, forRent, inTheaters)
    }

    override suspend fun getPopularForRent(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY").movies
    }

    override suspend fun getPopularInTheaters(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY").movies
    }

    override suspend fun getPopularStreaming(): List<MovieItemViewState> {
        // now playing movies on page=2
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY&page=2").movies
    }

    override suspend fun getPopularOnTV(): List<MovieItemViewState> {
        // popular movies on page=2
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY&page=2").movies
    }


    override suspend fun getTrendingMovies(): List<List<MovieItemViewState>> {  // size: 2 (tabs)
        return listOf(getTrendingToday().subList(0, 10), getTrendingThisWeek().subList(0, 10))
    }

    override suspend fun getTrendingToday(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/trending/movie/day?api_key=$API_KEY").movies
    }

    override suspend fun getTrendingThisWeek(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/trending/movie/week?api_key=$API_KEY").movies
    }

    override suspend fun getFreeMovies(): List<List<MovieItemViewState>> {  // size: 2 (tabs)
        return listOf(getUpcomingMovies().subList(0, 10), getTopRatedMovies().subList(0, 10))
    }

    override suspend fun getTopRatedMovies(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/top_rated?api_key=$API_KEY").movies
    }

    override suspend fun getUpcomingMovies(): List<MovieItemViewState> {
        return client.get<MoviesResponse>("https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY").movies
    }

    override suspend fun getMovie(movieId: Int): MovieItemViewState {
        return client.get<MovieItemViewState>("https://api.themoviedb.org/3/movie/$movieId?api_key=$API_KEY")
    }

    override suspend fun getPersonFunctions(movieId: Int): List<PersonFunction> {
        val result = client.get<PersonsFunctionsResponse>("https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$API_KEY").personsFunctions

        return if(result.size > 6) result.subList(0, 6)
        else result
    }

    override suspend fun getActors(movieId: Int): List<Actor> {
        val result = client.get<ActorResponse>("https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$API_KEY").actors

        return if(result.size > 6) result.subList(0, 6)
        else result

    }

}
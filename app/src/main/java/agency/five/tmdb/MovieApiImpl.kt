package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState


class MovieApiImpl() : MovieApi {

    private val m0 = MovieItemViewState(
        id = 0,
        title = "Iron Man 1",
        overview = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
        imageResId = R.drawable.iron_man_1,
        date = "05/02/2008",
        userScore = 76,
        country = "US",
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

    override suspend fun getPopularMovies(): List<List<MovieItemViewState>> {   // size: 4 (tabs)
        return listOf(l1, l2, l3, l1)
    }

    override suspend fun getTrendingMovies(): List<List<MovieItemViewState>> {  // size: 2 (tabs)
        return listOf(l2, l3)
    }

    override suspend fun getFreeMovies(): List<List<MovieItemViewState>> {  // size: 2 (tabs)
        return listOf(l1, l3)
    }

    override suspend fun getMovie(movieId: Int): MovieItemViewState {
        return listOf(m0, m1, m2, m3, m4, m5)[movieId]
    }

    override suspend fun getPersonFunctions(movieId: Int): List<PersonFunction> {
        return if (movieId == 0) {
            listOf(p1, p2, p3, p4, p5, p6)
        } else {
            emptyList()
        }
    }

    override suspend fun getActors(movieId: Int): List<Actor> {
        return if (movieId == 0) {
            listOf(a1, a2, a3)
        } else {
            emptyList()
        }
    }

    override suspend fun updateMovie(movie: MovieItemViewState, isFavorite: Boolean) {
        for(m in l1) {
            if (m.id == movie.id) {
                m.favorite = isFavorite
            }
        }

        for(m in l2) {
            if (m.id == movie.id) {
                m.favorite = isFavorite
            }
        }

        for(m in l3) {
            if (m.id == movie.id) {
                m.favorite = isFavorite
            }
        }
    }
}
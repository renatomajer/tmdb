package agency.five.tmdb.domain.common

/**
 * Represents a movie on the Home screen
 */
data class Movie(
    val id: Int,
    val isFavorite: Boolean,
    val posterPath: String?
)


/**
 * Returns a new list of movies with their favorite status set according IDs of the favorite movies.
 */
fun setMoviesFavoriteStatus(
    movies: List<Movie>,
    favoriteMoviesIds: List<Int>
): List<Movie> {
    return movies.map { movie ->
        if (favoriteMoviesIds.contains(movie.id)) {
            movie.copy(isFavorite = true)
        } else {
            movie
        }
    }
}
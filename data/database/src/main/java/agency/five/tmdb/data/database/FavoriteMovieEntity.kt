package agency.five.tmdb.data.database

import androidx.room.Entity

@Entity(tableName = "favorite_movies", primaryKeys = ["movieId"])
data class FavoriteMovieEntity(
    val movieId: Int,
    val posterPath: String?
)
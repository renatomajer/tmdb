package agency.five.tmdb.data.database.mapper

import agency.five.tmdb.data.database.FavoriteMovieEntity
import agency.five.tmdb.domain.common.Movie

class MovieMapper : EntityMapper<Movie, FavoriteMovieEntity> {

    override fun toEntity(data: Movie): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            movieId = data.id,
            posterPath = data.posterPath
        )
    }

    override fun fromEntity(entity: FavoriteMovieEntity): Movie {
        return Movie(
            id = entity.movieId,
            isFavorite = true,
            posterPath = entity.posterPath
        )
    }
}
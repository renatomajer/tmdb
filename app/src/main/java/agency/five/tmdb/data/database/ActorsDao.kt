package agency.five.tmdb.data.database

import agency.five.tmdb.domain.common.Actor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ActorsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertActors(actors: List<Actor>)

    @Query("DELETE FROM actor WHERE movie_id = :movieId")
    fun deleteActors(movieId: Int)
}
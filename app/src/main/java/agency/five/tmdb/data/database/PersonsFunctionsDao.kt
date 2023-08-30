package agency.five.tmdb.data.database

import agency.five.tmdb.domain.common.PersonFunction
import androidx.room.*


@Dao
interface PersonsFunctionsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPersonsFunctions(personsFunctions: List<PersonFunction>)

    @Query("DELETE FROM personfunction")
    fun deleteAll()
}
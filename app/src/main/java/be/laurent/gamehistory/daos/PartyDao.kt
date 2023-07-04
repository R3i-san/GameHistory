package be.laurent.gamehistory.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.laurent.gamehistory.models.PartyModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PartyDao {

    @Query("SELECT * FROM PartyModel")
    fun getAll(): Flow<List<PartyModel>>

    //Cette méthode pose problème
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(party: PartyModel) // : Long

    @Delete
    fun delete(party: PartyModel)
}
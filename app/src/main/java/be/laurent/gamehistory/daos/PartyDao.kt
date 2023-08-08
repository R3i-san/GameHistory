package be.laurent.gamehistory.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.PartyScoresModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PartyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(party: PartyModel)

    @Transaction
    @Query("SELECT * FROM PartyModel")
    fun getAll(): Flow<List<PartyScoresModel>>


    @Transaction
    @Query("SELECT * FROM PartyModel pm " +
            "JOIN ScoreModel sm ON sm.fk_pid = pm.pid " +
            "WHERE lower(pm.gameID) LIKE (:game)" +
            "GROUP BY pm.pid")
    fun getByGame(game : String): Flow<List<PartyScoresModel>>

    @Transaction
    @Query("SELECT * FROM PartyModel pm " +
            "JOIN ScoreModel sm ON sm.fk_pid = pm.pid " +
            "WHERE lower(sm.name) LIKE (:player)" +
            "GROUP BY pm.pid")
    fun getByPlayer(player : String): Flow<List<PartyScoresModel>>


    @Transaction
    @Query("SELECT * FROM PartyModel pm " +
            "JOIN ScoreModel sm ON sm.fk_pid = pm.pid " +
            "WHERE lower(pm.gameID) LIKE (:game) AND lower(sm.name) LIKE (:player)")
    fun getByGameAndPlayer(game : String, player : String): Flow<List<PartyScoresModel>>

    @Delete
    fun delete(party: PartyModel)
}
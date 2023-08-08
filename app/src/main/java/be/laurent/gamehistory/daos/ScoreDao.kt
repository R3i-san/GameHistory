package be.laurent.gamehistory.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import be.laurent.gamehistory.models.ScoreModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Insert
    suspend fun insert(score : List<ScoreModel>)


    @Query("SELECT * FROM ScoreModel WHERE fk_pid = :pid")
    fun getScoresByPID(pid : String): List<ScoreModel>

}
package be.laurent.gamehistory.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.laurent.gamehistory.models.GameModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM GameModel")
    fun getAll(): Flow<List<GameModel>>

    @Query("SELECT name FROM GameModel")
    fun getAllByName(): Flow<List<String>>

    @Query("SELECT * FROM GameModel WHERE gid IN (:gids)")
    fun loadAllByIds(gids: IntArray): List<GameModel>

    @Query("SELECT * FROM GameModel WHERE name LIKE :name")
    fun findByName(name: String): GameModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameModel)

    @Delete
    fun delete(game: GameModel)
}

package be.laurent.gamehistory.repository

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.laurent.gamehistory.daos.GameDao
import be.laurent.gamehistory.daos.PartyDao
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import kotlinx.coroutines.flow.Flow

@Database(entities = [GameModel::class, PartyModel::class], version = 2)
abstract class RoomDB : RoomDatabase() {

    abstract fun partyDao(): PartyDao
    abstract fun gameDao(): GameDao

    companion object{
        private var INSTANCE: RoomDB? = null
        private const val DATABASE_NAME = "historyStorage.db"

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = get(context)
            }
        }

        private fun get(context: Context) : RoomDB{
            INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    DATABASE_NAME
                ).createFromAsset("databases/$DATABASE_NAME").build()

            return INSTANCE ?: throw IllegalStateException("A database error has occured")
        }

        fun getParties(): Flow<List<PartyModel>>? = INSTANCE?.partyDao()?.getAll()

        suspend fun addParty(parties : PartyModel){

            //CoroutinesRoom
            INSTANCE?.partyDao()?.insert(parties)

        }

        fun getGames(gid: Int): Flow<List<GameModel>>? = INSTANCE?.gameDao()?.getAll()


    }
}

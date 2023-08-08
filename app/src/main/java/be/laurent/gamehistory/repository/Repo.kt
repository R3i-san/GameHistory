package be.laurent.gamehistory.repository

import android.content.Context
import androidx.room.Room
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.PartyScoresModel
import be.laurent.gamehistory.models.ScoreModel
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "historyStorage"
class Repo private constructor(context: Context) {

    private val database : RoomDB = Room.databaseBuilder(
        context.applicationContext,
        RoomDB::class.java,
        DATABASE_NAME
    ).build()

    private val partyDao = database.partyDao()
    private val scoreDao = database.scoreDao()
    private val gameDao = database.gameDao()

    fun getParties(): Flow<List<PartyScoresModel>> = partyDao.getAll()
    fun getPartiesBy(game : String, player : String) : Flow<List<PartyScoresModel>>{

        if(game.isNotBlank() && player.isNotBlank()) return partyDao.getByGameAndPlayer(game, player)
        if(game.isNotBlank() && player.isBlank()) return partyDao.getByGame(game)
        if(game.isBlank() && player.isNotBlank()) return partyDao.getByPlayer(player)

        return partyDao.getAll()

    }


    fun getScores(pid : String) : List<ScoreModel> = scoreDao.getScoresByPID(pid)
    fun getGames(): Flow<List<GameModel>> = gameDao.getAll()
    fun getGamesByName(): Flow<List<String>> = gameDao.getAllByName()

    suspend fun addParty(party : PartyModel, scores : List<ScoreModel>){

            partyDao.insert(party)
            scoreDao.insert(scores)
    }

    suspend fun addGame(game : GameModel){gameDao.insert(game)}

    companion object {
        private var INSTANCE: Repo? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Repo(context)
            }
        }

        fun get(): Repo {
            return INSTANCE ?:
            throw IllegalStateException("Repository must be initialized")
        }
    }
}
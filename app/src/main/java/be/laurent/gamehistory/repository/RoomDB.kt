package be.laurent.gamehistory.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import be.laurent.gamehistory.daos.GameDao
import be.laurent.gamehistory.daos.PartyDao
import be.laurent.gamehistory.daos.ScoreDao
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.ScoreModel

@Database(entities = [GameModel::class, PartyModel::class, ScoreModel::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun partyDao(): PartyDao
    abstract fun scoreDao(): ScoreDao
    abstract fun gameDao(): GameDao

}

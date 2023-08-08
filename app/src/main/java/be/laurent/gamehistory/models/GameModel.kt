package be.laurent.gamehistory.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
class GameModel(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "players") val players : Int,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "timer") val timer : Int) {

    @PrimaryKey(autoGenerate = true) var gid: Int = 0

    override fun toString(): String {
        return name
    }
}

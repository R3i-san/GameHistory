package be.laurent.gamehistory.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class PartyModel(
    @PrimaryKey(autoGenerate = true) var pid : Int,
    @ColumnInfo(name = "gameID") val gameID : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "timer") val timer : Int,
    @ColumnInfo(name = "location") val location : String,
    @ColumnInfo(name = "thumbnail") val thumbnail : String){


}
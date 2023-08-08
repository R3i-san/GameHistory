package be.laurent.gamehistory.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class PartyModel(

    @ColumnInfo(name = "gameID") val gameID : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "timer") val timer : Int,
    @ColumnInfo(name = "location") val location : String,
    @ColumnInfo(name = "thumbnail") val thumbnail : ByteArray){

    @PrimaryKey var pid : String = UUID.randomUUID().toString()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PartyModel

        if (gameID != other.gameID) return false
        if (description != other.description) return false
        if (timer != other.timer) return false
        if (location != other.location) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false
        if (pid != other.pid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gameID.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + timer
        result = 31 * result + location.hashCode()
        result = 31 * result + thumbnail.contentHashCode()
        result = 31 * result + pid.hashCode()
        return result
    }
}
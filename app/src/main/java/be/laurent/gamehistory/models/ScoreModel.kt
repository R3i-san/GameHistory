package be.laurent.gamehistory.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

@Entity
data class ScoreModel(
    @ColumnInfo("name") val name : String,
    @ColumnInfo("value") val value : Int,
    @ColumnInfo("fk_pid") var fk_pid : String)
    {
    @PrimaryKey var sid : String = UUID.randomUUID().toString()
}
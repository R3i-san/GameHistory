package be.laurent.gamehistory.models

import androidx.room.Embedded
import androidx.room.Relation


data class PartyScoresModel(
    @Embedded val party: PartyModel,
    @Relation(
        parentColumn = "pid",
        entityColumn = "fk_pid"
    )
    val scores: List<ScoreModel>
)


package fr.campus.academy.kotlin.tp_media_player.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
class MusicDTO (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val size: String,
    val duration: String
)
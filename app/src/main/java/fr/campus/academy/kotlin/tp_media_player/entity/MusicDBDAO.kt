package fr.campus.academy.kotlin.tp_media_player.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class MusicDBDAO {

    @Query("SELECT * FROM music")
    abstract fun findAll(): MutableList<MusicDTO>

    @Query("SELECT * FROM music WHERE title = :title")
    abstract fun findOneByTitle(title: String): MusicDTO

    @Insert
    abstract fun insert(vararg music: MusicDTO)

    @Delete
    abstract fun delete(music: MusicDTO)
}
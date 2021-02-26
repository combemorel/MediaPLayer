package fr.campus.academy.kotlin.tp_media_player.bdd

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.campus.academy.kotlin.tp_media_player.entity.MusicDBDAO
import fr.campus.academy.kotlin.tp_media_player.entity.MusicDTO

@Database(entities = [MusicDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun MusicDBDAO(): MusicDBDAO
}
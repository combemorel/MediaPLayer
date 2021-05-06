package fr.campus.academy.kotlin.tp_media_player.dao

import android.content.Context
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media.getContentUri
import fr.campus.academy.kotlin.tp_media_player.entity.Music

class MusicDAO
{
    fun getListMusics(context: Context): MutableList<Music>
    {
        val projection = arrayOf(
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DURATION)

        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null)

        val listMusics: MutableList<Music> = ArrayList()

        if (cursor != null)
        {
            try
            {
                while (cursor.moveToNext())
                {

                    listMusics.add(
                        Music(
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)),
                        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    )
                    )
                }
            }catch (e: Exception) { e.printStackTrace() }
            finally { cursor.close() }
        }
        return listMusics
    }
}
package fr.campus.academy.kotlin.tp_media_player.service

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import fr.campus.academy.kotlin.tp_media_player.R
import fr.campus.academy.kotlin.tp_media_player.activity.MainActivity
import fr.campus.academy.kotlin.tp_media_player.entity.Music
import java.net.URI
import java.net.URI.create
import kotlin.math.log


class MediaplayerService : Service()  {

    // Bloc static :
    companion object
    {
        // Constantes :
        const val EXTRA_COMMANDE = "EXTRA_COMMANDE"
        const val COMMANDE_PLAY = "COMMANDE_PLAY"
        const val COMMANDE_PAUSE = "COMMANDE_PAUSE"
    }

    // MediaPlayer :
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate()
    {
        // init :
        super.onCreate()

        //media player :


//        mediaPlayer = MediaPlayer.create(this, R.raw.titre)
//        mediaPlayer.setOnCompletionListener {
//
//            // on signale à l'activité qu'on a atteint la fin du titre :
//            val intent = Intent()
//            intent.action = MainActivity.PlayerBroadcastReceiver.INTENT_FILTER
//            sendBroadcast(intent)
//
//            // on remet à zéro :
//            mediaPlayer.seekTo(0)
//        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        if (intent?.hasExtra(EXTRA_COMMANDE) == true)
        {
            when (intent.getStringExtra(EXTRA_COMMANDE))
            {
                COMMANDE_PLAY -> {
                    //lecture :
                    val uri = intent.getStringExtra("uri")
                    Log.d("uri", "uri :"+uri)

                    mediaPlayer.setDataSource(uri)
                    mediaPlayer.start()
                }
                COMMANDE_PAUSE -> {
                    // pause :
                    mediaPlayer.pause()
                }
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy()
    {
        // init :
        super.onDestroy()

        // on libère le media player :
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }
}

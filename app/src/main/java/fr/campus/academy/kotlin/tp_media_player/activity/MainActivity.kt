package fr.campus.academy.kotlin.tp_media_player.activity

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fr.campus.academy.kotlin.tp_media_player.R
import fr.campus.academy.kotlin.tp_media_player.adapter.MusicAdapter
import fr.campus.academy.kotlin.tp_media_player.dao.MusicDAO
import fr.campus.academy.kotlin.tp_media_player.entity.Music
import fr.campus.academy.kotlin.tp_media_player.service.MediaplayerService
//import fr.campus.academy.kotlin.tp_media_player.service.MediaplayerService.MusicBinder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{

    private var musicSrv: MediaplayerService? = null
    private val playIntent: Intent? = null
    private var musicBound = false
    private var listMusics: MutableList<Music>? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission == PackageManager.PERMISSION_GRANTED )
        {
            list_musics.setHasFixedSize(true)

            val layoutManager = LinearLayoutManager(this)
            list_musics.layoutManager = layoutManager

            val listMusics: MutableList<Music> = MusicDAO().getListMusics(this)

            list_musics.adapter = MusicAdapter(listMusics,this)

        }else
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        if(requestCode == 123)
        {
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                list_musics.setHasFixedSize(true)

                val layoutManager = LinearLayoutManager(this)
                list_musics.layoutManager = layoutManager

                listMusics = MusicDAO().getListMusics(this)

                list_musics.adapter = MusicAdapter(listMusics!!,this)
            }else
            {
                Toast.makeText(this, "Vous n'avez pas les autorisations", Toast.LENGTH_LONG).show()
            }
        }
    }


    // TODO: lecteur audio
    //connect to the service
//    private var musicConnection: ServiceConnection? = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName, service: IBinder) {
//            val binder = service as MusicBinder
//            //get service
//            musicSrv = binder.service
//            //pass list
//            musicSrv!!.setList(listMusics as ArrayList<Music>)
//            musicBound = true
//        }
//
//        override fun onServiceDisconnected(name: ComponentName) {
//            musicBound = false
//        }
//    }
    // TODO: boutton ajoute favoris
    // TODO: button action bar favoris
    // TODO: notification lors du lancement de la lecture
    // TODO: notification lecture/pause (+ titre + dureé)
    // Impossible pour android de ferme la lecture
    // titre suivant auto (+ dernier = reload)

}
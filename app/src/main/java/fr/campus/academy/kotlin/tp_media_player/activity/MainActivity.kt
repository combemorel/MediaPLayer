package fr.campus.academy.kotlin.tp_media_player.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fr.campus.academy.kotlin.tp_media_player.R
import fr.campus.academy.kotlin.tp_media_player.adapter.MusicAdapter
import fr.campus.academy.kotlin.tp_media_player.entity.Music
import fr.campus.academy.kotlin.tp_media_player.dao.MusicDAO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
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

                val listMusics: MutableList<Music> = MusicDAO().getListMusics(this)

                list_musics.adapter = MusicAdapter(listMusics,this)
            }else
            {
                Toast.makeText(this, "Vous n'avez pas les autorisations", Toast.LENGTH_LONG).show()
            }
        }
    }


    // TODO: lecteur audio
    // TODO: boutton ajoute favoris
    // TODO: button action bar favoris
    // TODO: notification lors du lancement de la lecture
    // TODO: notification lecture/pause (+ titre + dureé)
    // Impossible pour android de ferme la lecture
    // titre suivant auto (+ dernier = reload)

}
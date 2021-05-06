package fr.campus.academy.kotlin.tp_media_player.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.campus.academy.kotlin.tp_media_player.R
import fr.campus.academy.kotlin.tp_media_player.bdd.AppDatabaseHelper
import fr.campus.academy.kotlin.tp_media_player.entity.Music
import fr.campus.academy.kotlin.tp_media_player.entity.MusicDTO
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_music.view.*
import java.lang.Integer.parseInt
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class MusicAdapter (
    private var listMusics: MutableList<Music>,
    private var activity: AppCompatActivity
) : RecyclerView.Adapter<MusicAdapter.MusicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicsViewHolder {
        val viewMusic: View?;

        viewMusic = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_music, parent, false)

        return MusicsViewHolder(viewMusic)
    }

    override fun onBindViewHolder(holder: MusicsViewHolder, position: Int) {

        val min: Int = listMusics[position].duration / 1000 / 60
        val sec: Int = listMusics[position].duration / 1000 % 60
        val size: Int = listMusics[position].size / 1000000
        var duration: String = if(min < 10)  "0"+min.toString()+":" else min.toString()+":"
        duration = if(sec < 10)  duration+"0"+sec.toString() else duration+sec.toString()

        holder.textViewTitle.text = listMusics[position].title
        holder.textViewSize.text = "Taille : "+size.toString()+"Mo"
        holder.textViewDuration.text = "Durée : "+duration
    }

    override fun getItemCount(): Int = listMusics.size

    fun updateList(musics: MutableList<Music>) {
        this.listMusics = musics
        notifyDataSetChanged()
    }

//    fun castList(list: MutableList<MusicDTO>): MutableList<Music> {
//        val newList: MutableList<Music> = ArrayList()
//        for (music in list) {
//            newList.add(
//                Music(
//                    music.title,
//                    music.size,
//                    music.duration
//                )
//            )
//        }
//        return newList
//    }

    inner class MusicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.title)
        val textViewSize: TextView = itemView.findViewById(R.id.size)
        val textViewDuration: TextView = itemView.findViewById(R.id.duration)

        init
        {
            itemView.findViewById<ImageButton>(R.id.add_favoris).setOnClickListener {

                val music = AppDatabaseHelper.getDatabase(activity)
                    .MusicDBDAO()
                    .findOneByTitle(textViewTitle.text.toString())

                if(music == null)
                {
                    AppDatabaseHelper.getDatabase(activity)
                        .MusicDBDAO()
                        .insert(MusicDTO(0,textViewTitle.text.toString(),textViewSize.text.toString(),textViewDuration.text.toString()))
                } else
                {
                    Toast.makeText(activity,"Ce Titre est déja présent dans vos Favoris", Toast.LENGTH_LONG).show()
                }

            }
            itemView.findViewById<ConstraintLayout>(R.id.fragment).setOnClickListener {

                Toast.makeText(activity,"Lecture lancée", Toast.LENGTH_LONG).show()

            }
        }
    }
}
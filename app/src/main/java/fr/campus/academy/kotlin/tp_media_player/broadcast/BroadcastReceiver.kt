package fr.campus.academy.kotlin.tp_media_player.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.campus.academy.kotlin.tp_media_player.activity.MainActivity

class PlayerBroadcastReceiver (mainActivity: MainActivity) : BroadcastReceiver(){
    companion object { const val INTENT_FILTER = "com.example.lecteur" }

    override fun onReceive(context : Context?, intent: Intent?) {

    }
}
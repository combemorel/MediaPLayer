package fr.campus.academy.kotlin.tp_media_player.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.campus.academy.kotlin.tp_media_player.R
import kotlinx.android.synthetic.main.fragment_music.*

/**
 * A simple [Fragment] subclass.
 * Use the [MusicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MusicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val arguments = requireArguments()

        title.text = arguments.getString("title")
        size.text = arguments.getString("size")
        duration.text = arguments.getString("duration")

    }

}
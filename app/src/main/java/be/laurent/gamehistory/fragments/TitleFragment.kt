package be.laurent.gamehistory.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import be.laurent.gamehistory.AddGameActivity
import be.laurent.gamehistory.MainActivity
import be.laurent.gamehistory.R
import be.laurent.gamehistory.viewmodels.HomeViewModel


class TitleFragment() : Fragment() {

    lateinit var title: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_title, container, false)

        val titleComponent = view.findViewById<TextView>(R.id.Title);
        titleComponent.text = title


        return view
    }

}
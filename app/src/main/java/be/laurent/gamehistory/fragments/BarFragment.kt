package be.laurent.gamehistory.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import be.laurent.gamehistory.AddGameActivity
import be.laurent.gamehistory.AddPartyActivity
import be.laurent.gamehistory.R
import be.laurent.gamehistory.ViewStatsActivity
import be.laurent.gamehistory.interfaces.ISwitchActivity

class BarFragment : Fragment(), ISwitchActivity{

    private lateinit var bar : CardView
    private lateinit var addPartyButton : ImageButton
    private lateinit var addGameButton : ImageButton
    private lateinit var StatsButton : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_bar, container, false)

        addPartyButton = view.findViewById(R.id.addPartyButton);
        addGameButton = view.findViewById(R.id.addGameButton);
        StatsButton = view.findViewById(R.id.StatsButton);

        addPartyButton.setOnClickListener{goTo(AddPartyActivity::class.java)};
        addGameButton.setOnClickListener{goTo(AddGameActivity::class.java)};
        StatsButton.setOnClickListener{goTo(ViewStatsActivity::class.java)};
        return view
    }


    override fun goTo(activityClass: Class<*>) {
        val intent = Intent(context, activityClass)
        startActivity(intent)
    }
}
package be.laurent.gamehistory.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.AddGameActivity
import be.laurent.gamehistory.AddPartyActivity
import be.laurent.gamehistory.R
import be.laurent.gamehistory.ViewStatsActivity
import be.laurent.gamehistory.databinding.FragmentBarBinding
import be.laurent.gamehistory.databinding.FragmentHomeBinding
import be.laurent.gamehistory.interfaces.ISwitchActivity
import be.laurent.gamehistory.viewmodels.HomeViewModel

class BarFragment : Fragment(), ISwitchActivity{

    private lateinit var bar : CardView
    private lateinit var addPartyButton : ImageButton
    private lateinit var addGameButton : ImageButton
    private lateinit var statsButton : ImageButton

    private var _binding: FragmentBarBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding bacause it is null. Is the view is Visible ?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBarBinding.inflate(inflater, container, false)

        binding.addPartyButton.setOnClickListener{goTo(AddPartyActivity::class.java)};
        binding.addGameButton.setOnClickListener{goTo(AddGameActivity::class.java)};
        binding.statsButton.setOnClickListener{goTo(ViewStatsActivity::class.java)};

        return binding.root
    }

    override fun goTo(activityClass: Class<*>) {
        val intent = Intent(context, activityClass)
        startActivity(intent)
    }
}
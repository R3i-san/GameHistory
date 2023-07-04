package be.laurent.gamehistory

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import be.laurent.gamehistory.databinding.ActivityMainBinding
import be.laurent.gamehistory.fragments.BarFragment
import be.laurent.gamehistory.fragments.HomeFragment
import be.laurent.gamehistory.fragments.TitleFragment
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.repository.RoomDB
import be.laurent.gamehistory.viewmodels.AddPartyViewModel
import be.laurent.gamehistory.viewmodels.HomeViewModel
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentTitle : FrameLayout
    private lateinit var fragmentHome : FrameLayout
    private lateinit var fragmentBar : FrameLayout

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        homeViewModel.retrieveParties()

        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        fragmentTitle = binding.containerFragmentTitle
        fragmentHome = binding.containerFragmentHome
        fragmentBar = binding.containerFragmentBar

        initFrags()

        tranferParty()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun initFrags(){



        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(fragmentTitle.id, tFrag)
        transaction.replace(fragmentHome.id, HomeFragment())
        //transaction.replace(fragmentHome.id, HomeFragment(this, homeViewModel))
        transaction.replace(fragmentBar.id, BarFragment())

        transaction.commit()
    }

    private fun tranferParty(){

    /*    val addPartyViewModel = extractParty() ?: return

        homeViewModel.addParty(
            PartyModel(
                GameModel(addPartyViewModel.description, 2, "Moving pieces", 30),
                addPartyViewModel.description,
                30,
                "here",
                "pciture"))*/
    }


    private fun extractParty() : AddPartyViewModel?{
        return intent.extras?.get("partyViewModel") as AddPartyViewModel?
    }

}
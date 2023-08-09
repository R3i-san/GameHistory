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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.Room
import be.laurent.gamehistory.adapter.PartyAdapter
import be.laurent.gamehistory.databinding.ActivityMainBinding
import be.laurent.gamehistory.fragments.BarFragment
import be.laurent.gamehistory.fragments.HomeFragment
import be.laurent.gamehistory.fragments.TitleFragment
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.repository.RoomDB
import be.laurent.gamehistory.viewmodels.AddPartyViewModel
import be.laurent.gamehistory.viewmodels.HomeViewModel
import kotlinx.coroutines.launch
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel.load()

        binding = ActivityMainBinding.inflate(layoutInflater)
        displaySearchCount()
        setContentView(binding.root)

        binding.submitFilter.setOnClickListener {setFilterAction()}



    }


    private fun setFilterAction() {
        viewModel.filter(
            binding.gameFilter.text.toString(),
            binding.playerFilter.text.toString())
        displaySearchCount()
    }

    private fun displaySearchCount(){
        binding.resultCount.text = String.format("%s résulat(s)", viewModel.getNbrParties().toString())
    }

}
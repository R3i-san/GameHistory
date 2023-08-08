package be.laurent.gamehistory

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import be.laurent.gamehistory.adapter.PartyAdapter
import be.laurent.gamehistory.daos.GameDao
import be.laurent.gamehistory.databinding.ActivityAddPartyBinding
import be.laurent.gamehistory.databinding.ActivityMainBinding
import be.laurent.gamehistory.fragments.BarFragment
import be.laurent.gamehistory.fragments.TitleFragment
import be.laurent.gamehistory.interfaces.ISwitchActivity
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.viewmodels.AddPartyViewModel
import kotlinx.coroutines.launch

class AddPartyActivity : AppCompatActivity(){

    private lateinit var binding: ActivityAddPartyBinding
    private val viewModel : AddPartyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel.loadGames()

        super.onCreate(savedInstanceState)

        binding = ActivityAddPartyBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

}
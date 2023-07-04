package be.laurent.gamehistory

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import be.laurent.gamehistory.daos.GameDao
import be.laurent.gamehistory.databinding.ActivityAddPartyBinding
import be.laurent.gamehistory.fragments.BarFragment
import be.laurent.gamehistory.fragments.TitleFragment
import be.laurent.gamehistory.interfaces.ISwitchActivity
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.viewmodels.AddPartyViewModel

class AddPartyActivity : AppCompatActivity(), ISwitchActivity{

    private lateinit var binding: ActivityAddPartyBinding
    private val viewModel : AddPartyViewModel by viewModels()

    private lateinit var fragmentTitle : FrameLayout
    private lateinit var fragmentBar : FrameLayout
    private lateinit var nameField : Spinner
    private lateinit var descriptionField : EditText
    private lateinit var scoreField : EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_party)

        binding = ActivityAddPartyBinding.inflate(layoutInflater)

        fragmentTitle = binding.containerFragmentTitle
        fragmentBar = binding.containerFragmentBar
        nameField = binding.gameSelectSpinner
        descriptionField = binding.partyDescription

        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(fragmentTitle.id, TitleFragment.initFragment("Ajouter une partie"))
        //transaction.replace(fragmentForm.id, AddPartyFragment())
        transaction.replace(fragmentBar.id, BarFragment())
        transaction.commit()


        val confirmButton = this.findViewById<Button>(R.id.confirm_button)

        confirmButton.setOnClickListener{addParty()};

    }

    private fun extractData(){
        viewModel.description = this.findViewById<EditText>(descriptionField.id)?.text.toString()
    }

    private fun addParty(){
        extractData()
        viewModel.createParty()
        onBackPressed()
    }


    override fun goTo(activityClass: Class<*>) {
        TODO("Not yet implemented")
    }


}
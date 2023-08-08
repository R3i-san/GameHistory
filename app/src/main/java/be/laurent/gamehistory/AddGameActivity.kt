package be.laurent.gamehistory

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import be.laurent.gamehistory.databinding.ActivityAddGameBinding
import be.laurent.gamehistory.databinding.ActivityMainBinding
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.viewmodels.AddGameViewModel
import java.util.UUID

class AddGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGameBinding

    /*private lateinit var nameField : EditText
    private lateinit var descriptionField : EditText
    private lateinit var playersField : EditText
    private lateinit var timerField : EditText*/


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityAddGameBinding.inflate(layoutInflater)
        /*nameField = this.findViewById(R.id.game_name)
        descriptionField = this.findViewById(R.id.game_description)
        playersField = this.findViewById(R.id.number_players)
        timerField = this.findViewById(R.id.game_timer)
        confirmButton = this.findViewById(R.id.confirm_button)*/

        //binding.confirmButton.setOnClickListener{submit()}

        setContentView(binding.root)
    }

    private fun submit(){
        onBackPressed()
    }
}
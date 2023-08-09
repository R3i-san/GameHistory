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



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityAddGameBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }

}
package be.laurent.gamehistory.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.laurent.gamehistory.AddPartyActivity
import be.laurent.gamehistory.MainActivity
import be.laurent.gamehistory.R
import be.laurent.gamehistory.databinding.ActivityAddPartyBinding
import be.laurent.gamehistory.databinding.FragmentAddPartyBinding
import be.laurent.gamehistory.interfaces.ISwitchActivity
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.viewmodels.AddPartyViewModel
import be.laurent.gamehistory.viewmodels.HomeViewModel

/*class AddPartyFragment : Fragment(), ISwitchActivity {

    private lateinit var binding: FragmentAddPartyBinding

    private var image:ImageView? = null
    private lateinit var confirmButton: Button

    private val viewModel : AddPartyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_add_party, container, false)

        binding = FragmentAddPartyBinding.inflate(layoutInflater)

        confirmButton = view.findViewById(R.id.confirm_button)
        image = binding.selectedImage

        confirmButton.setOnClickListener{addParty()};

        val button = view.findViewById<Button>(R.id.load_image_button)
        button.setOnClickListener{getImage()}

        return view
    }

    private fun addParty(){
        viewModel.description = view?.findViewById<EditText>(R.id.party_description)?.text.toString()
        goTo(MainActivity::class.java)
    }

    private fun getImage(){
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Séléctionner une image"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null) return

            val picture = data.data

            image?.setImageURI(picture)

        }
    }

    override fun goTo(activityClass: Class<*>) {
        val intent = Intent(context, activityClass)
        intent.putExtra("partyViewModel", viewModel)
        startActivity(intent)
    }
}*/
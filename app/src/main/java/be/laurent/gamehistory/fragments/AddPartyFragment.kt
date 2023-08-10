package be.laurent.gamehistory.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.R
import be.laurent.gamehistory.adapter.ScoreFieldsAdapter
import be.laurent.gamehistory.databinding.FragmentAddPartyBinding
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.ScoreModel
import be.laurent.gamehistory.viewmodels.AddPartyViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.lang.NullPointerException
import java.lang.NumberFormatException
import kotlin.Exception

//TODO : View stats

//TODO : Add game from addPartyActivity
//TODO : (Rework style)

class AddPartyFragment : Fragment(){

    private lateinit var binding: FragmentAddPartyBinding

    private val viewModel : AddPartyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        viewModel.loadGames()

        binding = FragmentAddPartyBinding.inflate(layoutInflater)

        binding.confirmButton.setOnClickListener{addParty()};
        binding.loadImageButton.setOnClickListener{chooseThumbnail()}

        initSpinnerListener()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.games.collect{ list -> initSpinner(list) }
            }
        }
    }

    private fun initSpinner(names : List<GameModel>){
        binding.gameSelectSpinner.adapter = ArrayAdapter(
            requireContext(),
            androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
            names)
    }

    private fun initSpinnerListener(){
        binding.gameSelectSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedGame = parent.getItemAtPosition(position)
                viewModel.selectedGame = selectedGame as GameModel
                setFields()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setFields() {
        loadThumbnail()
        binding.partyTimer.setText(viewModel.selectedGame.timer.toString())
        binding.partyDescription.setText(viewModel.selectedGame.description)

        binding.scoresRecyclerView.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        binding.scoresRecyclerView.adapter =
            ScoreFieldsAdapter(R.layout.item_score, viewModel.getNbrScores())

    }

    private fun addParty(){

        try{

            val party = extractParty()

            val scores = extractScores(party.pid)

            viewModel.createParty(party, scores)
            activity?.onBackPressed()

        } catch (e : Exception){

            when(e){
                is NullPointerException -> {
                    Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                }

                is NumberFormatException -> {
                        Toast.makeText(context, "Veuillez remplir les champs score", Toast.LENGTH_SHORT).show()
                }

                else -> throw e
            }
        }
    }

    private fun extractParty() : PartyModel{

        return PartyModel(
            binding.gameSelectSpinner.selectedItem.toString(),
            binding.partyDescription.text.toString(),
            binding.partyTimer.text.toString().toInt(),
            binding.partyLocation.text.toString(),
            viewModel.thumbnail ?: viewModel.selectedGame.thumbnail
        )
    }

    private fun extractScores(pid : String) : List<ScoreModel>{

        val scores = ArrayList<ScoreModel>()

        val recyclerView = binding.scoresRecyclerView

        for(i in 0 until recyclerView.adapter!!.itemCount){

            val holder = recyclerView.findViewHolderForAdapterPosition(i)
            val item = holder?.itemView

            val name = item?.findViewById<EditText>(R.id.scoreName)
            val value = item?.findViewById<EditText>(R.id.scoreValue)

            scores.add(ScoreModel(
                name?.text.toString(),
                value?.text.toString().toInt(),
                pid))
        }
        return scores
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){

            if((intent == null) || (intent.data == null)) return

            val imageView = binding.selectedImage
            imageView.setImageURI(intent.data)
            convertThumbnail(imageView)
        }
    }

    private fun loadThumbnail(){



        val imageView = binding.selectedImage
        val bytes = viewModel.thumbnail ?: viewModel.selectedGame.thumbnail
        if(bytes.isEmpty()) return

        val bmp : Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 256, 256, false))
    }

    private fun chooseThumbnail() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Séléctionner une image"), 1)
    }

    private fun convertThumbnail(imageView : ImageView){
        try{
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            viewModel.thumbnail = stream.toByteArray()
            stream.close()
        } catch (e : Exception){
           Toast.makeText(context, "Une erreur est survenue lors de la récupération de la vignette", Toast.LENGTH_SHORT).show()
        }
    }
}
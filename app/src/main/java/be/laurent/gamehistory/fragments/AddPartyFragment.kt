package be.laurent.gamehistory.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import be.laurent.gamehistory.R
import be.laurent.gamehistory.adapter.ScoreAdapter
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
//TODO : Display details

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

        binding.loadImageButton.setOnClickListener{getThumbnail()}

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

    private fun setFields(){
        binding.partyTimer.setText(viewModel.selectedGame.timer.toString())
        binding.partyDescription.setText(viewModel.selectedGame.description)
        binding.scoresRecyclerView.adapter = ScoreAdapter(R.layout.item_score, viewModel.getNbrScores())

    }

    private fun addParty(){

        try{

            val party = extractParty()

            val scores = extractScores(party.pid)

            viewModel.createParty(party, scores)
            activity?.onBackPressed()

        } catch (e : Exception){
            binding.dataIntegrityWarning.visibility = View.GONE
            binding.dataIntegrityWarning.visibility = View.VISIBLE
            when(e){
                is NullPointerException -> {
                    binding.dataIntegrityWarning.text = "Veuillez remplir tous les champs"

                }

                is NumberFormatException -> {
                    binding.dataIntegrityWarning.text = "Les champs de scores doivent être remplis"
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
            viewModel.thumbnail
        )
    }

    private fun extractScores(pid : String) : List<ScoreModel>{
        val scores = ArrayList<ScoreModel>()
        for(i in 0 until binding.scoresRecyclerView.adapter!!.itemCount){
            val holder = binding.scoresRecyclerView.findViewHolderForAdapterPosition(i)
            val name = holder?.itemView?.findViewById<EditText>(R.id.scoreName)!!.text.toString()
            val value = holder.itemView.findViewById<EditText>(R.id.scoreValue)!!.text.toString().toInt()
            scores.add(ScoreModel(name, value, pid))
        }
        return scores
    }

    private fun getThumbnail() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Séléctionner une image"), 1)

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

    private fun convertThumbnail(imageView : ImageView){
        try{
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            viewModel.thumbnail = stream.toByteArray()
            stream.close()
        } catch (e : Exception){
            binding.dataIntegrityWarning.text = "Une erreur est survenue lors de la récupération de la vignette"
        }
    }
}
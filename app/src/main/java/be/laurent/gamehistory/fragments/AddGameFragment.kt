package be.laurent.gamehistory.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.laurent.gamehistory.TakePhotoActivity
import be.laurent.gamehistory.databinding.FragmentAddGameBinding
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.viewmodels.AddGameViewModel
import java.io.ByteArrayOutputStream

class AddGameFragment : Fragment() {

    private var _binding: FragmentAddGameBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding bacause it is null. Is the view is Visible ?"
        }
    private val viewModel : AddGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddGameBinding.inflate(inflater, container, false)
        binding.takePhotoButton.setOnClickListener{takePhoto()}
        binding.confirmButton.setOnClickListener{submit()}
        return binding.root
    }

    private fun takePhoto(){
        val intent = Intent(context, TakePhotoActivity::class.java)
        intent.type = "/image"
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == Activity.RESULT_OK){

            val path = data?.extras?.get("path").toString()
            binding.photoTook.setImageURI(Uri.parse(path))
            convertThumbnail(binding.photoTook)
            Toast.makeText(context, path, Toast.LENGTH_SHORT).show()
        }
    }

    private fun submit(){

        val name = binding.gameName.text.toString()
        val players = binding.numberPlayers.text.toString().toInt()
        val description = binding.gameDescription.text.toString()
        val timer = binding.gameTimer.text.toString().toInt()

        if(players > 8){
            Toast.makeText(context, "8 joueurs max.", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.createGame(
            GameModel(
                name,
                players,
                description,
                timer,
                viewModel.thumbnail)
        )

        activity?.onBackPressed()

    }

    private fun convertThumbnail(imageView : ImageView){
        try{
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
            viewModel.thumbnail = stream.toByteArray()
            stream.close()
        } catch (e : Exception){
            Toast.makeText(context, "Une erreur est survenue lors de la récupération de la vignette", Toast.LENGTH_SHORT).show()
        }
    }



}
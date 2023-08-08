package be.laurent.gamehistory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.laurent.gamehistory.databinding.FragmentAddGameBinding
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.viewmodels.AddGameViewModel

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
        binding.confirmButton.setOnClickListener{submit()}
        return binding.root
    }

    private fun submit(){

        viewModel.createGame(
            GameModel(
                binding.gameName.text.toString(),
                binding.numberPlayers.text.toString().toInt(),
                binding.gameDescription.text.toString(),
                binding.gameTimer.text.toString().toInt()
            ))

        activity?.onBackPressed()

    }


}
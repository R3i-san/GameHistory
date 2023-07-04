package be.laurent.gamehistory.viewmodels

import androidx.lifecycle.ViewModel
import be.laurent.gamehistory.models.GameModel

class AddGameViewModel : ViewModel() {

    private lateinit var game : GameModel

    fun createGame(newGameModel: GameModel){
        game = newGameModel
    }

}
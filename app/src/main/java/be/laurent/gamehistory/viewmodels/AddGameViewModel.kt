package be.laurent.gamehistory.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.repository.Repo
import kotlinx.coroutines.launch

class AddGameViewModel : ViewModel() {

    private val repo = Repo.get()

    fun createGame(game: GameModel){
        viewModelScope.launch {
            repo.addGame(game)
        }
    }
}
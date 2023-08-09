package be.laurent.gamehistory.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.ScoreModel
import be.laurent.gamehistory.repository.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AddPartyViewModel : ViewModel(){

    private val repo = Repo.get()
    private val _games :  MutableStateFlow<List<GameModel>> = MutableStateFlow(emptyList())
    val games : StateFlow<List<GameModel>> = _games
    lateinit var selectedGame : GameModel
    var thumbnail : ByteArray? = null

    fun getNbrScores() = selectedGame.players

    fun loadGames() {
        viewModelScope.launch {
            repo.getGames().collect{
                _games.value = it
            }
        }

    }

    fun getGamesNames() : List<String>{
        val alist = ArrayList<String>()
        games.value.forEach { g -> alist.add(g.name)  }
        return alist
    }

    fun createParty(party: PartyModel, score : List<ScoreModel>){
        viewModelScope.launch {
            repo.addParty(party, score)
        }
    }
}

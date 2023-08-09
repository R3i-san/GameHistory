package be.laurent.gamehistory.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.PartyScoresModel
import be.laurent.gamehistory.models.ScoreModel
import be.laurent.gamehistory.repository.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class HomeViewModel : ViewModel() {

    private val repo = Repo.get()
    private val _parties : MutableStateFlow<List<PartyScoresModel>> = MutableStateFlow(emptyList())
    val parties: StateFlow<List<PartyScoresModel>> = _parties

    fun load(){
        loadParties()
    }

    private fun loadParties() {
        viewModelScope.launch {
            repo.getParties().collect {
                _parties.value = it
            }
        }
    }

    fun filter(game : String, player : String){

        viewModelScope.launch {
            repo.getPartiesBy(game, player).collect {
                _parties.value = it
            }
        }
    }

    fun getScoresOf(index: Int) : List<ScoreModel> = parties.value[index].scores

    fun getParties() : ArrayList<PartyScoresModel> {
        val alist = ArrayList<PartyScoresModel>()
        parties.value.forEach { p -> alist.add(p)  }
        return alist
    }

    fun getPartyAt(index : Int) : PartyModel{
        return parties.value[index].party
    }


    fun getMaxScore(){

    }

    /*fun getPartiesWithScore() : ArrayList<PartyScoreModel> {
        val alist = ArrayList<PartyScoreModel>()
        partiesScore.value.forEach { p -> alist.add(p)  }
        return alist
    }*/

    fun getNbrParties() : Int = parties.value.size

}
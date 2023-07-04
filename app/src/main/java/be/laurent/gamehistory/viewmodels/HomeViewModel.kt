package be.laurent.gamehistory.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.repository.RoomDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {

    //private var parties : LiveData<List<GameModel>> = LiveData()
    //var parties = MutableLiveData<ArrayList<PartyModel>>(ArrayList())
    private val _parties : MutableStateFlow<List<PartyModel>> = MutableStateFlow(emptyList())
    val parties : StateFlow<List<PartyModel>>
        get() = _parties.asStateFlow()


    private val repo = RoomDB

    fun retrieveParties(){
        if(_parties.value.isNotEmpty()) return

        viewModelScope.launch {
            repo.getParties()?.collect{ _parties.value = it }
        }
    }


    fun getParties() : List<PartyModel> {
        return parties.value!!
    }

    /*fun addParty(party : PartyModel){
        parties.value!!.add(party)
    }*/

    fun getNbrParties() : Int = parties.value!!.size

}
package be.laurent.gamehistory.viewmodels

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.laurent.gamehistory.daos.GameDao
import be.laurent.gamehistory.daos.PartyDao
import be.laurent.gamehistory.interfaces.IRepo
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.repository.RoomDB
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
class AddPartyViewModel(
    var description : String = "I won",
    var players : List<String> = ArrayList<String>(),
    var score : String = "1-0"
) : ViewModel(), Parcelable {

    var pid : String = UUID.randomUUID().toString()
    private val repo = RoomDB


  fun createParty(){

      viewModelScope.launch {
          repo.addParty(
              PartyModel(1,
                  "test",
              description,
              30,
              "location test",
              "put a picutre here"))
          }
      }
  }

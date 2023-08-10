package be.laurent.gamehistory.viewmodels

import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.models.PartyScoresModel
import be.laurent.gamehistory.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test


//Not working
internal class HomeViewModelTest {

   val dispatcher = TestCoroutineDispatcher()
   val repo = Repo.get()
   lateinit var parties : List<PartyScoresModel>
   var party : PartyModel? = null

   @Before
   suspend fun setup(){
      Dispatchers.setMain(dispatcher)

      repo.getParties().collect{
         parties = it
      }

      if(parties.isEmpty()){

         party = PartyModel(
            "Test",
            "Test",
            1,
            "Test",
            ByteArray(0)

         )

         repo.addParty(
            party!!,
            emptyList()
         )

      }

   }

   @After
   fun tearDown() {
      Dispatchers.resetMain()

      if(party != null){
         repo.deleteParty(party!!)
      }

   }


   @Test
   fun loadTest(){

      assertNotEquals(0, parties.size)

   }


}
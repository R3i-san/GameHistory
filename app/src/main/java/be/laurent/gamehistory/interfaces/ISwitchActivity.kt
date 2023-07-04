package be.laurent.gamehistory.interfaces

import androidx.appcompat.app.AppCompatActivity

interface ISwitchActivity {

    fun goTo(activityClass: Class<*>)

}
package be.laurent.gamehistory.repository

import android.app.Application

class IntentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RoomDB.initialize(this)
    }
}
package sarmisdead.a7minutesworkout

import android.app.Application

class YogaApp : Application() {

    val db by lazy{

        HistoryDatabase.getInstance(this)

    } //End of Lazy

} //End of YogaApp
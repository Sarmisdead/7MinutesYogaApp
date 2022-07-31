package sarmisdead.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import sarmisdead.a7minutesworkout.databinding.ActivityFinishBinding
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarFinishActivity)

        if(supportActionBar != null){

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "7 MINUTES YOGA"

        } //End of if

        binding?.toolbarFinishActivity?.setNavigationOnClickListener {

            onBackPressed()

        } //End of SetNavigationOnClickListener

        binding?.btnFinish?.setOnClickListener{

            finish()

        } //End of setOnClickListener

        val dao = (application as YogaApp).db.historyDao()
        addDateToDatabase(dao)

    } //End of onCreate

    private fun addDateToDatabase(historyDao: HistoryDao){

        val myCalendar = Calendar.getInstance()
        val dateTime = myCalendar.time
        Log.e("Date: ", "" +dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Formatted Date : ", "" + date)


        lifecycleScope.launch {

            historyDao.insert(HistoryEntity(date))
            Log.e( "Date : ", "Added...")

        } //End of LifecycleScope

    } //End of addDateToDatabase

} //End of FinishActivity
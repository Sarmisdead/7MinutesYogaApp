package sarmisdead.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sarmisdead.a7minutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private var binding : ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        if (supportActionBar != null) {

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"

        } //End of if

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {

            onBackPressed()

        } //End of setNavigationOnClickListener

        val dao = (application as YogaApp).db.historyDao()
        getAllCompletedDates(dao)

    } //End of onCreate

    private fun getAllCompletedDates(historyDao: HistoryDao){

        lifecycleScope.launch {

            historyDao.fetchAllDates().collect { allCompletedDatesList ->

                if(allCompletedDatesList.isNotEmpty()){

                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE

                    binding?.tvNoDataAvailable?.visibility = View.INVISIBLE

                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    val dates = ArrayList<String>()
                    for(date in allCompletedDatesList){

                        dates.add(date.date)

                    } //End of IF

                    val historyAdapter = HistoryAdapter(dates)

                    binding?.rvHistory?.adapter = historyAdapter

                } else {

                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE

                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE

                } //End of IF

            } //End of Collect

        } //End of LifecycleScope

    } //End of getAllCompletedDates

    override fun onDestroy() {

        super.onDestroy()
        binding = null

    } //End of onDestroy

} //End of HistoryActivity
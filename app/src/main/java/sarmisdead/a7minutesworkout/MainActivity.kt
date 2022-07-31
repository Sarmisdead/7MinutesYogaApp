package sarmisdead.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import sarmisdead.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {

            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)

        } // End of flStart setOnCLickListener

        binding?.flBMI?.setOnClickListener {

            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)

        } // End of flBMI setOnCLickListener

        binding?.flHistory?.setOnClickListener {

            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)

        } // End of flHistory setOnCLickListener

    } //End of onCreate

    override fun onDestroy() {

        super.onDestroy()
        binding = null

    } //End of onDestroy

} //End of MainActivity
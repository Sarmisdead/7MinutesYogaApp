 package sarmisdead.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import sarmisdead.a7minutesworkout.databinding.ActivityExerciseBinding
import sarmisdead.a7minutesworkout.databinding.ActivityMainBinding
import sarmisdead.a7minutesworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

 class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

     private var binding: ActivityExerciseBinding? = null

     private var restTimer: CountDownTimer? = null
     private var restProgress = 0
     private var restTimerDuration: Long = 10

     private var exerciseTimer: CountDownTimer? = null
     private var exerciseProgress = 0
     private var exerciseTimerDuration: Long = 30


     private var exerciseList: ArrayList<ExerciseModel>? = null
     private var currentExercisePosition = -1

     private var tts: TextToSpeech? = null
     private var player: MediaPlayer? = null

     private var exerciseAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar != null){

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "7 MINUTES YOGA"

        } //End of if

        binding?.toolbarExercise?.setNavigationOnClickListener {

            customDialogForBackButton()

        } //End of setNavigationOnClickListener

        exerciseList = Constants.defaultExerciseList()

        tts = TextToSpeech(this, this)

        setupRestView()
        setupExerciseStatusRecyclerView()

    } //End of onCreate

     override fun onBackPressed() {

         customDialogForBackButton()

     } //End of onBackPressed

     private fun customDialogForBackButton() {

         val customDialog = Dialog(this)
         val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
         customDialog.setContentView(dialogBinding.root)
         customDialog.setCanceledOnTouchOutside(false)

         dialogBinding.btnYes.setOnClickListener{

            this@ExerciseActivity.finish()
             customDialog.dismiss()

         } //End of setOnClickListener

         dialogBinding.btnNo.setOnClickListener{

             customDialog.dismiss()

         } //End of setOnClickListener

         customDialog.show()

     } //End of customDialogForBackButton

     private fun setupExerciseStatusRecyclerView(){

        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter

    } //End of setupExerciseStatusRecyclerView

    private fun setupRestView(){

        try {

            val soundURI = Uri.parse("android.resource://sarmisdead.a7minutesworkout/"
                + R.raw.start)

            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()

        } catch (e: Exception){

            e.printStackTrace()

        } //End of Catch

        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE

        if (restTimer != null){

            restTimer?.cancel()
            restProgress = 0

        } //End of IF

        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition +1].getName()

        setRestProgressBar()

    } //End of setupRestView

     private fun setupExerciseView(){

         binding?.flRestView?.visibility = View.INVISIBLE
         binding?.tvTitle?.visibility = View.INVISIBLE
         binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
         binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE

         binding?.tvExerciseName?.visibility = View.VISIBLE
         binding?.flExerciseView?.visibility = View.VISIBLE
         binding?.ivImage?.visibility = View.VISIBLE

         if(exerciseTimer != null) {

             exerciseTimer?.cancel()
             exerciseProgress = 0

         } //End of IF

         speakOut(exerciseList!![currentExercisePosition].getName())

         binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
         binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()

         setExerciseProgressBar()

     } //End of setupExerciseView

     private fun setExerciseProgressBar(){

         binding?.progressBarExercise?.progress = exerciseProgress

         exerciseTimer = object : CountDownTimer( exerciseTimerDuration * 1000, 1000){

             override fun onTick(p0: Long)  {

                 exerciseProgress++
                 binding?.progressBarExercise?.progress = 30 - exerciseProgress
                 binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()

             } //End of onTick

             override fun onFinish() {

                 if(currentExercisePosition < exerciseList?.size!! - 1) {

                     exerciseList!![currentExercisePosition].setIsSelected(false)
                     exerciseList!![currentExercisePosition].setIsCompleted(true)
                     exerciseAdapter!!.notifyDataSetChanged()
                     setupRestView()

                 } else {

                    finish()
                     val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                     startActivity(intent)
                 } //End of IF

             } //End of onFinish

         }.start() //End of CountDownTimer

     } //End of setExerciseProgressBar

    private fun setRestProgressBar(){

        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000){

            override fun onTick(p0: Long)  {

                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()

            } //End of onTick

            override fun onFinish() {

                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()

            } //End of onFinish

        }.start() //End of CountDownTimer

    } //End of setRestProgressBar

     override fun onDestroy() {

         super.onDestroy()

         //Shutting down the timer to Rest between exercises
         if (restTimer != null){

             restTimer?.cancel()
             restProgress = 0

         } //End of IF

         //Shutting down the timer to Exercise after finished
         if(exerciseTimer != null){

             exerciseTimer?.cancel()
             exerciseProgress = 0

         } //End of IF

         //Shutting down the Text to Speech feature
         if (tts != null) {

             tts!!.stop()
             tts!!.shutdown()

         } //End of IF

         //Shutting down the Media Player
         if(player != null){

             player!!.stop()

         } //End of IF

         binding = null

     } //End of onDestroy

     override fun onInit(status: Int) {

         if (status == TextToSpeech.SUCCESS) {

             //setting English Language
             val result = tts?.setLanguage(Locale.US)

             if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){

                 Log.e("TTS", "The Language specified is not supported!")

         } else {

             Log.e("TTS", "Initialization Failed!")

         } //End of IF

         } //End of IF

     } //End of onInit

     private fun speakOut(text : String){

         tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")

     } //End of SpeakOut

 } //End of Exercise Activity




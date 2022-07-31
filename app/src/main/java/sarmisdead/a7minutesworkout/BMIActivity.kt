package sarmisdead.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import sarmisdead.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode


class BMIActivity : AppCompatActivity() {

    companion object {

        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"

    } //End of Companion object

    private var currentVisibleView : String = METRIC_UNITS_VIEW

    private var binding : ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null) {

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE YOUR BMI"

        } //End of if

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {

            onBackPressed()

        } //End of setNavigationOnClickListener

        //Default
        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener{ _ , checkedId: Int ->

            if (checkedId == R.id.rbMetricUnits){

                makeVisibleMetricUnitsView()

            } else {

                makeVisibleUsUnitsView()

            } //End of IF

        } //End of setOnCheckedChangeListener

        binding?.btnCalculateUnits?.setOnClickListener {

            calculateUnits()

        } //End of setOnClickListener

    } //End of onCreate

    private fun calculateUnits(){

        if(currentVisibleView == METRIC_UNITS_VIEW){

            if (validateMetricUnits()) {

                val heightValue : Float = binding?.etHeight?.text.toString().toFloat() / 100
                val WeightValue : Float = binding?.etWeight?.text.toString().toFloat()

                val bmi = WeightValue / (heightValue * heightValue)

                displayBMIResult(bmi)

            } else {

                Toast.makeText(
                    this@BMIActivity, "Please enter valid values.",
                    Toast.LENGTH_LONG
                ).show()

            } //End of Else

        }  else {

            if(validateUsUnits()){

                val usHeightFeet : String = binding?.etHeightFeet?.text.toString()
                val usHeightInch : String = binding?.etHeightInch?.text.toString()
                val usWeight : Float = binding?.etUsWeight?.text.toString().toFloat()

                val heighValue = usHeightInch.toFloat() + usHeightFeet.toFloat() * 12
                val bmi = 703 * (usWeight / (heighValue * heighValue))

                displayBMIResult(bmi)

            } else {

                Toast.makeText(
                    this@BMIActivity, "Please enter valid values.",
                    Toast.LENGTH_LONG
                ).show()

            } //End of Else

        } //End of Else

    } //End of calculateUnits

    private fun makeVisibleMetricUnitsView(){

        //Show
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilWeight?.visibility = View.VISIBLE
        binding?.tilHeight?.visibility = View.VISIBLE

        //Hide
        binding?.tilUsWeight?.visibility = View.GONE
        binding?.tilHeightFeet?.visibility = View.GONE
        binding?.tilHeightInch?.visibility = View.GONE

        //Clear all the boxes
        binding?.etHeight?.text!!.clear()
        binding?.etWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    } //End of makeVisibleMetricUnitsView

    private fun makeVisibleUsUnitsView(){

        //Show
        binding?.tilUsWeight?.visibility = View.VISIBLE
        binding?.tilHeightFeet?.visibility = View.VISIBLE
        binding?.tilHeightInch?.visibility = View.VISIBLE

        //Hide
        currentVisibleView = US_UNITS_VIEW
        binding?.tilWeight?.visibility = View.INVISIBLE
        binding?.tilHeight?.visibility = View.INVISIBLE

        //Clear all the boxes
        binding?.etHeightFeet?.text!!.clear()
        binding?.etHeightInch?.text!!.clear()
        binding?.etUsWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    } //End of makeVisibleUsUnitsView


    private fun displayBMIResult(bmi: Float) {

        val bmiLabel : String
        val bmiDescription : String

        if(bmi.compareTo(15f) <= 0) {

            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! Your really need to take better care of yourself! Eat more!"

        } else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {

            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! Your really need to take better care of yourself! Eat more!"

        } else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {

            bmiLabel = "Underweight"
            bmiDescription = "Oops! Your really need to take better care of yourself! Eat more!"

        } else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {

            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"

        } else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {

            bmiLabel = "Overweight"
            bmiDescription = "Oops! Your really need to take better care of yourself! Do more yoga and workout more!"

        } else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {

            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! Your really need to take better care of yourself! Do more yoga and workout more!"

        } else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {

            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "Be carefully! You are in a very dangerous condition! Act Now!"

        } else {

            bmiLabel = "Obese Class ||| (Very severely obese)"
            bmiDescription = "Be carefully! You are in a very dangerous condition! Act Now!"

        } //End of IF

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription

    } //End of displayBMIResult

    private fun validateMetricUnits():Boolean{

        var isValid = true

        if(binding?.etWeight?.text.toString().isEmpty()){

            isValid = false

        } else if(binding?.etHeight?.text.toString().isEmpty()){

            isValid = false

        } //End of IF

        return isValid

    } //End of validateMetricUnits

    private fun validateUsUnits():Boolean{

        var isValid = true

        when {
            binding?.etUsWeight?.text.toString().isEmpty() -> {

                isValid = false

            } //End of etUsWeight

            binding?.etHeightFeet?.text.toString().isEmpty() -> {

                isValid = false

            } //End of etHeightFeet

            binding?.etHeightInch?.text.toString().isEmpty() -> {

                isValid = false

            } //End of etHeightInch

        } //End of When

        return isValid

    } //End of validateUsUnits

} //End of BMIActivity
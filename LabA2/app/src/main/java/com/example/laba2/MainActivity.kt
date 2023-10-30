package com.example.laba2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var serviceQualityOptions: RadioGroup
    private lateinit var costOfService: TextInputLayout
    private lateinit var calculateButton: Button
    private lateinit var tipResult: TextView
    private lateinit var foodRating: RatingBar
    private lateinit var roundUpSwitch: SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceQualityOptions = findViewById(R.id.service_quality_options)
        costOfService = findViewById(R.id.cost_of_service)
        calculateButton = findViewById(R.id.calculate_button)
        tipResult = findViewById(R.id.tip_result)
        foodRating = findViewById(R.id.food_rating)
        roundUpSwitch = findViewById(R.id.round_up_switch)

        calculateButton.setOnClickListener { calculateTip() }

        costOfService.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    private fun calculateTip() {
        val stringInTextField = costOfService.editText?.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        val serviceQuality = when (serviceQualityOptions.checkedRadioButtonId) {
            R.id.great -> 0.15
            R.id.ok -> 0.10
            R.id.satisfactory -> 0.05
            else -> 0.0
        }

        val foodRatingValue = foodRating.rating

        var tip = serviceQuality * cost + foodRatingValue

        val roundUp = roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
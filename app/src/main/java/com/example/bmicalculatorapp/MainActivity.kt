package com.example.bmicalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        image_boy.setOnClickListener{
            image_boy.setImageResource(R.drawable.boy)
            image_girl.setImageResource(R.drawable.girl_blur)
        }

        image_girl.setOnClickListener{
            image_boy.setImageResource(R.drawable.boy_blur)
            image_girl.setImageResource(R.drawable.girl)
        }


        btnCalculate.setOnClickListener {

            if (!edit_weight.text.toString().equals("") && !edit_heightFt.text.toString().equals("")
                && !edit_heightInch.text.toString().equals("")){

                val wt = (edit_weight.text.toString()).toDouble()
                val htfeet = (edit_heightFt.text.toString()).toDouble()
                val htinch = (edit_heightInch.text.toString()).toDouble()

                val totalInch = (htfeet*12) + htinch;
                val totalCM = totalInch * 2.54

                val totalM = totalCM/100

                val bmi = String.format("%.2f", wt/(totalM*totalM))

                textTitle.text = "Your BMI"
                textResult.text = "${bmi.toDouble()}"
                textTitle.setTextColor(resources.getColor(android.R.color.white));
                textMsg.setTextColor(resources.getColor(android.R.color.white));

                when {
                    bmi> 25.toString() -> {
                        Toast.makeText(this@MainActivity, R.string.strOverWt, Toast.LENGTH_SHORT).show()

                        textMsg.text = resources.getString(R.string.strOverWt)
                        llMain.setBackgroundColor(resources.getColor(R.color.colorRed))


                    }
                    bmi< 18.toString() -> {
                        Toast.makeText(this@MainActivity, R.string.strUnderWt, Toast.LENGTH_SHORT).show()

                        textMsg.text = resources.getString(R.string.strUnderWt)
                        llMain.setBackgroundColor(resources.getColor(R.color.colorYellow))

                    }
                    else -> {
                        Toast.makeText(this@MainActivity, R.string.strHealthy, Toast.LENGTH_SHORT).show()

                        textMsg.text = resources.getString(R.string.strHealthy)
                        llMain.setBackgroundColor(resources.getColor(R.color.colorGreen))

                    }
                }
                bmiView.visibility = VISIBLE
                btnCalculate.visibility = GONE
            }else {
                Toast.makeText(this@MainActivity, "Please fill all the required blanks!", Toast.LENGTH_SHORT).show()
            }
            calculate_again.setOnClickListener{
                bmiView.visibility = GONE
                btnCalculate.visibility = VISIBLE
                edit_weight.text.clear()
                edit_heightFt.text.clear()
                edit_heightInch.text.clear()
                edit_weight.requestFocus()
            }

        }




    }

}
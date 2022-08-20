package com.oguzel.rollthedice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var buttonDice: Button
    private lateinit var imageViewDice: ImageView
    private lateinit var diceImageList: ArrayList<Int>
    private var runnable = Runnable{}
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonDice = findViewById(R.id.buttonDice)
        imageViewDice = findViewById(R.id.imageViewDice)

        diceImageList = arrayListOf(
            R.drawable.ic_diceone,
            R.drawable.ic_dicetwo,
            R.drawable.ic_dicethree,
            R.drawable.ic_dicefour,
            R.drawable.ic_dicefive,
            R.drawable.ic_dicesix
        )

        buttonDice.setOnClickListener {
            if(buttonDice.text == "Roll The Dice"){
                rollDice()
            } else{
                stopDice()
            }
        }

    }

    private fun rollDice(){
        //This function starts animation,looping 6 svg, changes color and text of button
        runnable = Runnable {
            val random = Random()
            val randomIndex = random.nextInt(6)

            imageViewDice.setImageResource(diceImageList[randomIndex])

            handler.postDelayed(runnable,100)
        }
        handler.post(runnable)
        buttonDice.text = getString(R.string.stopDice)
        buttonDice.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
    }

    private fun stopDice(){
        //This function ends animation,changes color and text of button
        handler.removeCallbacks(runnable)
        buttonDice.text = getString(R.string.rollDice)
        buttonDice.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
    }
}
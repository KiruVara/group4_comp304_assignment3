package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ex1Clicked(view: View){
        val intent = Intent(this, ExerciseOneActivity::class.java)
        startActivity(intent)
    }
    fun ex2Clicked(view: View){
        val intent = Intent(this, ExerciseTwoActivity::class.java)
        startActivity(intent)
    }
    fun ex3Clicked(view: View){
        //add to open exercise 3
        val intent = Intent(this, ExerciseThreeActivity::class.java)
        startActivity(intent)
    }
}
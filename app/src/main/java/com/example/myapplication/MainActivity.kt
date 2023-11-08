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
    fun ex2Clicked(){
        //add to open exercise 2
    }
    fun ex3Clicked(){
        //add to open exercise 3

    }
}
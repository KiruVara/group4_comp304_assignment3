package com.example.myapplication
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
class ExerciseThreeActivity:AppCompatActivity() {
    private lateinit var sunView: ImageView
    private lateinit var earthView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solar_system)

        sunView = findViewById(R.id.sunImageView)
        earthView = findViewById(R.id.earthImageView)

        val rotateEarth = AnimatorInflater.loadAnimator(this, R.animator.rotate_earth)
        val revolveEarth = AnimatorInflater.loadAnimator(this, R.animator.revolve_earth)
        val scaleEarth = AnimatorInflater.loadAnimator(this, R.animator.scale_earth);

        rotateEarth.setTarget(earthView)
        revolveEarth.setTarget(earthView)
        scaleEarth.setTarget(earthView);
        val set = AnimatorSet()
        set.playTogether(rotateEarth, revolveEarth, scaleEarth)
        set.start()
    }
}
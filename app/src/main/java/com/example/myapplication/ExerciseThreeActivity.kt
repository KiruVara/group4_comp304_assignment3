package com.example.myapplication
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
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

        rotateEarth.setTarget(earthView)
        performAnimation(R.animator.revolve_earth)

        rotateEarth.start()
    }
    private fun performAnimation(animationResourceID: Int){
        val orbitRadius = 300
        val pivotX = earthView.x + orbitRadius
        val pivotY = earthView.y + orbitRadius

        earthView.pivotX = pivotX
        earthView.pivotY = pivotY

        val rotationAnimator = ObjectAnimator.ofFloat(
            earthView,
            "rotation",
            0f,
            360f
        ).apply{animationResourceID
        }

        earthView.setImageResource(R.drawable.earth)

        rotationAnimator.start()

    }
}
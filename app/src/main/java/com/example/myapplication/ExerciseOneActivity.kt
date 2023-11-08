package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class ExerciseOneActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var lineThicknessSpinner: Spinner
    private lateinit var colorRadioGroup: RadioGroup
    private lateinit var arrowUp: ImageButton
    private lateinit var arrowLeft: ImageButton
    private lateinit var arrowRight: ImageButton
    private lateinit var arrowDown: ImageButton
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise1_main)

        drawingView = findViewById(R.id.drawingSurface)
        lineThicknessSpinner = findViewById(R.id.lineThicknessSpinner)
        colorRadioGroup = findViewById(R.id.colorRadioGroup)
        arrowUp = findViewById(R.id.imageBtnUp)
        arrowLeft = findViewById(R.id.imageBtnLeft)
        arrowRight = findViewById(R.id.imageBtnRight)
        arrowDown = findViewById(R.id.imageBtnDown)
        clearButton = findViewById(R.id.clearButton)

        val lineThicknessAdapter = ArrayAdapter.createFromResource(
            this, R.array.line_thickness_values, android.R.layout.simple_spinner_item
        )
        lineThicknessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lineThicknessSpinner.adapter = lineThicknessAdapter

        // Initialize drawing settings
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        drawingView.setPaint(paint)

        // Set up listeners
        lineThicknessSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Update line thickness
                val selectedThickness = parent?.getItemAtPosition(position).toString().toFloat()
                drawingView.updateLineThickness(selectedThickness)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //will always be selected
            }
        }

        colorRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Update line color based on the selected radio button
            when (checkedId) {
                R.id.rdBtnRed -> drawingView.updateLineColour(Color.RED)
                R.id.rdBtnYellow -> drawingView.updateLineColour(Color.YELLOW)
                R.id.rdBtnCyan -> drawingView.updateLineColour(Color.CYAN)

            }
        }

        arrowUp.setOnClickListener { drawingView.drawLine(Direction.UP) }
        arrowLeft.setOnClickListener { drawingView.drawLine(Direction.LEFT) }
        arrowRight.setOnClickListener { drawingView.drawLine(Direction.RIGHT) }
        arrowDown.setOnClickListener { drawingView.drawLine(Direction.DOWN) }

        clearButton.setOnClickListener { drawingView.clearCanvas() }
    }
}

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val path = Path()
    private var currentX = 0f
    private var currentY = 0f
    private val lineLength = 20f

    fun setPaint(paint: Paint) {
        this.paint.set(paint)
    }

    fun updateLineColour(colour: Int) {
        paint.color = colour
    }

    fun updateLineThickness(thickness: Float) {
        paint.strokeWidth = thickness
    }

    fun drawLine(direction: Direction) {
        // Calculate the new position for the line based on the current position
        val startX = currentX
        val startY = currentY
        val endX: Float
        val endY: Float

        when (direction) {
            Direction.UP -> {
                endX = startX
                endY = startY - lineLength
            }
            Direction.LEFT -> {
                endX = startX - lineLength
                endY = startY
            }
            Direction.RIGHT -> {
                endX = startX + lineLength
                endY = startY
            }
            Direction.DOWN -> {
                endX = startX
                endY = startY + lineLength
            }
        }

        // Draw the line on the canvas
        path.moveTo(startX, startY)
        path.lineTo(endX, endY)

        // Update the current position
        currentX = endX
        currentY = endY

        // Invalidate the view to redraw it
        invalidate()
    }

    fun clearCanvas() {
        path.reset()
        currentX = 0f
        currentY = 0f

        invalidate()    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}

enum class Direction {
    UP, LEFT, RIGHT, DOWN
}
package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.*

class LongDateTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var mLongDate: Long = 0

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.LongDateTextView)
            mLongDate = a.getString(R.styleable.LongDateTextView_longDate)?.toDate()?.time ?: 0
            a.recycle()
        }
        setText(getFormattedDate(mLongDate))
    }

    var longDate: String
        get() = mLongDate.toString()
        set(date) {
            mLongDate = date.toDate().time
            setText(getFormattedDate(mLongDate))
        }

    private fun getFormattedDate(date: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val longDateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
        return longDateFormat.format(calendar.time)
    }

    private fun String.toDate(pattern: String = "dd/MM/yyyy"): Date {
        return SimpleDateFormat(pattern, Locale.getDefault()).parse(this) ?: Date(0)
    }
}

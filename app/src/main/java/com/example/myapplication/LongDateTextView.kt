
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
            mLongDate = a.getInt(R.styleable.LongDateTextView_longDate, 0).toLong()
            a.recycle()
        }
        setText(getFormattedDate(mLongDate))
    }

    var longDate: Long
        get() = mLongDate
        set(date) {
            mLongDate = date
            setText(getFormattedDate(date))
        }

    private fun getFormattedDate(date: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val longDateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
        return longDateFormat.format(calendar.time)
    }
}

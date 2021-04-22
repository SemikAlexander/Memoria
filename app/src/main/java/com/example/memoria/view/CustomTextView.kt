package com.example.memoria.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.example.memoria.R

class CustomTextView(context: Context, attrs: AttributeSet)
    : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    init {
        setTextColor(Color.WHITE)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
        typeface = ResourcesCompat.getFont(context, R.font.f20610)// Typeface.createFromAsset(context.assets, "fonts/montserrat_regular.otf")
    }
}
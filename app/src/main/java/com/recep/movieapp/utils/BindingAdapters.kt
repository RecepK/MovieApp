package com.recep.movieapp.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.recep.movieapp.R
import com.squareup.picasso.Picasso


@BindingAdapter("imageLoad")
fun ImageView.imageLoad(imageUrl: String?) {
    Picasso.get().load(imageUrl).into(this)
}

@BindingAdapter("formatDate")
fun TextView.formatDate(date: String?) {
    //val dateFormated = SimpleDateFormat("yyyy-MM-dd")
    text = "$date"
}

@BindingAdapter("spannable:text1", "spannable:text2")
fun TextView.spannable(text1: String, text2: String) {
    val builder = SpannableStringBuilder()
    builder.append(setSpan(this, text1, R.color.title_color))
    builder.append(setSpan(this, text2, R.color.gray_dark_color))
    setText(builder, TextView.BufferType.SPANNABLE)
}

private fun setSpan(view: TextView, text: String, color: Int): SpannableString {
    val spannable = SpannableString(text)
    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(view.context, color)),
        0,
        spannable.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}
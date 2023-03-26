package com.ruchanokal.europeancountries.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.ruchanokal.europeancountries.R
import com.ruchanokal.europeancountries.model.Languages
import java.text.DecimalFormat

// Android KTX kütüphanesinin kullanımı
fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {


    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_baseline_error_outline_24)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url:String?) {
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}

@BindingAdapter("android:showLanguages")
fun showLangs(view: TextView, languages: Languages) {

    val myLangs = getNonNullParams(languages)
    val commaSeparatedString = myLangs.joinToString(", ")
    view.text = commaSeparatedString
}

@BindingAdapter("android:showBorders")
fun showBorders(view: TextView, borders: List<String>?) {

    if (borders != null && borders.isNotEmpty()) {

        val commaSeparatedString = borders.joinToString(", ")
        view.text = commaSeparatedString

    } else {
        view.text = view.context.getString(R.string.not_has_borders)
    }
}


@BindingAdapter("android:beautifyNumberFormat")
fun changeFormat(view: TextView, number : Int) {

    val formatter = DecimalFormat("#,###.###")
    val formattedString = formatter.format(number)
    view.text = formattedString
}

fun getNonNullParams(languages: Languages): List<Any> {
    val nonNullParams = mutableListOf<Any>()
    for (property in languages.javaClass.declaredFields) {
        property.isAccessible = true
        val value = property.get(languages)
        if (value != null) {
            nonNullParams.add(value)
        }
    }
    return nonNullParams
}
package com.example.myapplication.ui

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setPrice")
fun TextView.setPrice(pr: Int) {
    text = "Price:$$pr"
}


@BindingAdapter("profileImage")
fun loadImage(view: ImageView, profileImage: String) {
    Glide.with(view.context)
        .load(profileImage)
        .into(view)
}


package com.example.videolibrary.screens.common.imageloader

import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.videolibrary.Constants
import com.example.videolibrary.R
import javax.inject.Inject

class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {

    private val requestOptions = RequestOptions.centerCropTransform()

    fun loadImage(imageUrl: String, target: ImageView) {
        Glide.with(activity).load(Constants.IMAGE_URL + imageUrl).apply(requestOptions).placeholder(R.drawable.placeholder_image).into(target)
    }
}
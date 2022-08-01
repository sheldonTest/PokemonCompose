package com.example.pokemoncompose.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun loadPokeImage(url: String,
                  @DrawableRes defaultImage: Int) : Bitmap? {
    var bitmapState : Bitmap? by remember { mutableStateOf(null)}

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })

    return bitmapState
}
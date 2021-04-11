package com.example.fedorinchik_hw7.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.example.fedorinchik_hw7.GiphConstants
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

class GiphyViewModel : ViewModel() {

    private var currentGiphy: String? = null

    fun startGiphy(intent: Intent, context: Context, giphy: ImageView) {
        val bundle = intent.extras
        if (bundle != null) {
            currentGiphy = bundle.getString(GiphConstants.GIPHY_URL)
        }
        currentGiphy?.let {
            Glide
                .with(context)
                .load(it)
                .into(giphy)
        }
    }

}
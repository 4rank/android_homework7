package com.example.fedorinchik_hw7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fedorinchik_hw7.GiphConstants.Companion.GIPHY_TITLE
import com.example.fedorinchik_hw7.GiphConstants.Companion.GIPHY_URL
import kotlinx.android.synthetic.main.giphy_activity.*

class GiphyActivity : AppCompatActivity() {

    private var currentGif: String? = null
    private var currentGifTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.giphy_activity)
        startGiphy()
    }

    private fun startGiphy() {
        val bundle = intent.extras
        if (bundle != null) {
            currentGif = bundle.getString(GIPHY_URL)
            currentGifTitle = bundle.getString(GIPHY_TITLE)
        }
        currentGif?.let {
            Glide
                .with(this)
                .load(it)
                .into(ga_giphy)
        }
    }
}
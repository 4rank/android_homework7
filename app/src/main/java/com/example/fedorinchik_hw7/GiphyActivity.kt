package com.example.fedorinchik_hw7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fedorinchik_hw7.viewmodel.GiphyViewModel
import kotlinx.android.synthetic.main.giphy_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GiphyActivity : AppCompatActivity() {

    private val viewModel: GiphyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.giphy_activity)
        viewModel.startGiphy(intent, this, image_view_giphy_activity)
    }
}
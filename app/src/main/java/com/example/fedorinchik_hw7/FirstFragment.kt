package com.example.fedorinchik_hw7

import kotlinx.android.synthetic.main.first_fragment.view.*
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fedorinchik_hw7.viewmodelfactory.ViewModel
import com.example.fedorinchik_hw7.viewmodelfactory.ViewModelFactory

class FirstFragment : Fragment() {

    private lateinit var viewModel: ViewModel

    private lateinit var recyclerView: RecyclerView

    private val adapter by lazy {
        AdapterGiphy {
            val intent = Intent(this.context, GiphyActivity::class.java)
            intent.putExtra(GiphConstants.GIPHY_URL, it.images.original.url)
            intent.putExtra(GiphConstants.GIPHY_TITLE, it.title)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = GiphyRepository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        val view = inflater.inflate(R.layout.first_fragment, container, false)
        startGiphy()
        startRecycler(view)
        return view
    }

    private fun startGiphy() {
        viewModel.trendGiphy()
        viewModel.giphTrend.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setChanged(it) }
            }
        })
    }

    private fun startRecycler(view: View) {
        recyclerView = view.recycler_view_first
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }
}
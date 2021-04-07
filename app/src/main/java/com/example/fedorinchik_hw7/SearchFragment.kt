package com.example.fedorinchik_hw7

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fedorinchik_hw7.viewmodelfactory.ViewModel
import com.example.fedorinchik_hw7.viewmodelfactory.ViewModelFactory
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*

class SearchFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        startRecycler(view)
        search(view)
        return view
    }

    private fun search(view: View) {
        view.button.setOnClickListener {
            val category = vVod_tag.text.toString()
            viewModel.startGiphy(category)
            viewModel.giphGet.observe(viewLifecycleOwner, { setResponse ->
                if (setResponse.isSuccessful) {
                    setResponse.body()?.data?.let { adapter.setChanged(it) }
                } else {
                    Toast.makeText(this.context, setResponse.code(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun startRecycler(view: View) {
        recyclerView = view.recycler_view_second
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
}
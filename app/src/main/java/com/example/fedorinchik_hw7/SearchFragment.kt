package com.example.fedorinchik_hw7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fedorinchik_hw7.viewmodel.ViewModel
import kotlinx.android.synthetic.main.search_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: ViewModel by viewModel()

    private val adapter by lazy {
        AdapterGiphy {
            val intent = viewModel.intent(requireContext(), it)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        viewModel.startRecycler(adapter, view.recycler_view_search_fragment)
        startSearch(view)
        return view
    }

    private fun startSearch(view: View) {
        view.button_search.setOnClickListener {
            viewModel.getGiphy(
                view.edit_text_search.text.toString(),
                adapter,
                view.edit_text_search
            )
        }
    }
}
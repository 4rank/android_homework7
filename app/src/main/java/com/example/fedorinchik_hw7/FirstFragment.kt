package com.example.fedorinchik_hw7

import kotlinx.android.synthetic.main.first_fragment.view.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fedorinchik_hw7.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.first_fragment, container, false)
        viewModel.startRecycler(adapter, view.recycler_view_first_fragment)
        viewModel.startGiphy(adapter)
        return view
    }
}
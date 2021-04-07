package com.example.fedorinchik_hw7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.giphy_layout.view.*

class AdapterGiphy(private val onClick: (Data) -> Unit) :
    RecyclerView.Adapter<AdapterGiphy.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var giphy = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.giphy_layout,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        giphyHolder(holder, position)
        val currentGif = giphy[position]
        holder.itemView.giphy.setOnClickListener {
            onClick(currentGif)
        }
    }

    override fun getItemCount(): Int {
        return giphy.size
    }

    private fun giphyHolder(holder: ViewHolder, position: Int) {
        val load = CircularProgressDrawable(holder.itemView.giphy.context)
        load.strokeWidth = 10F
        load.centerRadius = 50F
        load.start()
        Glide
            .with(holder.itemView.giphy.context)
            .load(giphy[position].images.original.url)
            .placeholder(load)
            .into(holder.itemView.giphy)

    }

    fun setChanged(giphy: List<Data>) {
        this.giphy = giphy
        notifyDataSetChanged()
    }
}
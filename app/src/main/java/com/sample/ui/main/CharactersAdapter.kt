package com.sample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.R

class CharactersAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<CharacterViewData, CharactersAdapter.ViewHolder>(ItemDiffUtilCallback()) {

    class ViewHolder(view: View, private val onClick: (String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val name: TextView

        init {
            name = view.findViewById(R.id.name)
        }

        fun bind(data: CharacterViewData) {
            name.text = data.name
            name.setOnClickListener { onClick(data.name) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemDiffUtilCallback : DiffUtil.ItemCallback<CharacterViewData>() {
    override fun areItemsTheSame(
        oldItem: CharacterViewData,
        newItem: CharacterViewData
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: CharacterViewData,
        newItem: CharacterViewData
    ): Boolean = oldItem.name == newItem.name
}

package com.sample.ui.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.R

class ListFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModels { ListViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO add loading

        val header = view.findViewById<TextView>(R.id.header)

        val adapter = CharactersAdapter()
        val list = view.findViewById<RecyclerView>(R.id.characterList)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel.characters.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.heading, Toast.LENGTH_LONG).show()
            adapter.submitList(it.relatedTopics.toList())
            header.text = it.heading
        }
    }
}

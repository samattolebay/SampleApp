package com.sample.ui.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.sample.R
import com.sample.ui.details.DetailsFragment
import com.sample.ui.util.Factory
import com.sample.ui.util.TwoPaneOnBackPressedCallback

class ListFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModels { Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slidingPaneLayout = view.findViewById<SlidingPaneLayout>(R.id.main)
        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED

        // Connect the SlidingPaneLayout to the system back button.
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            TwoPaneOnBackPressedCallback(slidingPaneLayout)
        )

        // TODO add loading

        val header = view.findViewById<TextView>(R.id.header)

        val adapter = CharactersAdapter { name ->
            childFragmentManager.beginTransaction()
                .replace(R.id.detail_container, DetailsFragment.newInstance(name))
                .commit()

            slidingPaneLayout.open()
        }
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

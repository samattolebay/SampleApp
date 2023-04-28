package com.sample.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.sample.BuildConfig
import com.sample.R
import com.sample.ui.details.DetailsFragment
import com.sample.ui.util.Factory

class ListFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModels { Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            viewModel.fetchCharacters(BuildConfig.CHARACTER)

        val slidingPaneLayout = view.findViewById<SlidingPaneLayout>(R.id.main)

        val search = view.findViewById<SearchView>(R.id.search)
        search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.search(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0?.isEmpty() == true) {
                    viewModel.reset()
                }
                return false
            }
        })

        val loadingBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val adapter = CharactersAdapter { name ->
            childFragmentManager.beginTransaction()
                .replace(R.id.detail_container, DetailsFragment.newInstance(name))
                .commit()

            slidingPaneLayout.open()
            search.clearFocus()
        }
        val list = view.findViewById<RecyclerView>(R.id.characterList)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.submitList(it)

            loadingBar.visibility = View.GONE
        }
    }
}

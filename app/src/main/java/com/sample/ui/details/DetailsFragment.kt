package com.sample.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.sample.R
import com.sample.ui.util.Factory

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        private const val ARG_CHARACTER_NAME = "name"

        fun newInstance(name: String) = DetailsFragment().apply {
            arguments = bundleOf(
                ARG_CHARACTER_NAME to name
            )
        }
    }

    private val characterName: String by lazy {
        requireArguments().getString(ARG_CHARACTER_NAME)
            ?: throw IllegalArgumentException("Argument $ARG_CHARACTER_NAME required!")
    }

    private val viewModel: DetailsViewModel by viewModels { Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<TextView>(R.id.name)
        name.text = characterName
        val description = view.findViewById<TextView>(R.id.description)
        val image = view.findViewById<ImageView>(R.id.image)

        if (savedInstanceState == null)
            viewModel.getCharacter(characterName)

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel.character.observe(viewLifecycleOwner) {
            description.text = it.text
            Glide.with(this)
                .load(it.firstUrl + it.icon.url)
                .placeholder(R.drawable.ic_downloading)
                .error(R.drawable.ic_no_data)
                .into(image)
        }
    }
}

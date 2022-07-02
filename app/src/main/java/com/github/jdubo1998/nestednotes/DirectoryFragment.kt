package com.github.jdubo1998.nestednotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_MAIN_NEST_STRING = "mainNestString"
private const val TAG = "DirectoryFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [DirectoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DirectoryFragment : Fragment() {
    // TODO: Replace mainNestString with using JSON argument instead of a string.
    private var mainNestString: String = """{"title": "", "items": []}"""
    private lateinit var mainNest: Directory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainNestString = it.getString(ARG_MAIN_NEST_STRING) ?: """{"title": "", "items": []}"""
        }

        mainNest = Json.decodeFromString(mainNestString)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dirsListView = view.findViewById<ListView>(R.id.list_dirs)!!
        val adapter = DirectoriesListAdapter(requireContext(), mainNest)
        dirsListView.adapter = adapter

        dirsListView.setOnItemClickListener { adapterView, view, i, l ->
            if (adapter.getItem(i) is Directory) {
                val subDirectoryFragment = newInstance(Json.encodeToString(adapter.getItem(i) as Directory))

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_navigator, subDirectoryFragment)
                    addToBackStack(mainNest.title)
                    commit()
                }
            } else if (adapter.getItem(i) is Note) {
                val noteFragment = NoteFragment.newInstance(Json.encodeToString(adapter.getItem(i) as Note))

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_navigator, noteFragment)
                    addToBackStack(mainNest.title)
                    commit()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param mainNestString String encoding of the JSON for the nested notes.
         * @return A new instance of fragment NavigatorFragment.
         */
        @JvmStatic
        fun newInstance(mainNestString: String) = DirectoryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MAIN_NEST_STRING, mainNestString)
            }
        }
    }
}
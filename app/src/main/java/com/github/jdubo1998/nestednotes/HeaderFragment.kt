package com.github.jdubo1998.nestednotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TITLE_STRING = "titleString"
private const val TAG = "HeaderFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [DirectoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeaderFragment : Fragment() {
    // TODO: Replace mainNestString with using JSON argument instead of a string.
    private var titleString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titleString = it.getString(ARG_TITLE_STRING) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteEditText = view.findViewById<EditText>(R.id.edittext_note)
        Log.d(TAG, "onViewCreated: $titleString")
        noteEditText.setText(titleString)
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
        fun newInstance(titleString: String) = DirectoryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE_STRING, titleString)
            }
        }
    }
}
package com.github.jdubo1998.nestednotes

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val ARG_NOTE_STRING = "noteString"

class NoteFragment : Fragment() {
    private var noteString: String = """{"title": "", "note": ""}"""
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            noteString = it.getString(ARG_NOTE_STRING) ?: """{"title": "", "note": ""}"""
        }

        note = Json.decodeFromString(noteString)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val titleEditText = view.findViewById<EditText>(R.id.edittext_title)
//        titleEditText.setText(note.title)

        val noteEditText = view.findViewById<EditText>(R.id.edittext_note)
        noteEditText.setText(note.note)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param noteString String encoding of the JSON for the nested notes.
         * @return A new instance of fragment NavigatorFragment.
         */
        @JvmStatic
        fun newInstance(noteString: String) = NoteFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_NOTE_STRING, noteString)
            }
        }
    }
}
package com.github.jdubo1998.nestednotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogTestString = """{
            "title": "Dogs",
            "items": [
                {
                    "type": "com.github.jdubo1998.nestednotes.Directory",
                    "title": "Small Dogs",
                    "items": [
                        {
                            "type": "com.github.jdubo1998.nestednotes.Note",
                            "title": "Mimi",
                            "note": "Mimi is a Chihuahua."
                        },
                        {
                            "type": "com.github.jdubo1998.nestednotes.Note",
                            "title": "Stinko",
                            "note": "Stinko is a Chihuahua."
                        }
                    ]
                },
                {
                    "type": "com.github.jdubo1998.nestednotes.Directory",
                    "title": "Big Dogs",
                    "items": [
                        {
                            "type": "com.github.jdubo1998.nestednotes.Note",
                            "title": "Pickles",
                            "note": "Pickles is a mix."
                        }
                    ]
                },
                {
                    "type": "com.github.jdubo1998.nestednotes.Note",
                    "title": "Deceased",
                    "note": "Gordas, Chelsea, Cutie Pie, Stony, Lamar, Sparky."
                }
            ]
        }"""

        val directoryFragment = DirectoryFragment.newInstance(dogTestString)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_navigator, directoryFragment)
//            addToBackStack("Home")
            commit()
        }
    }
}
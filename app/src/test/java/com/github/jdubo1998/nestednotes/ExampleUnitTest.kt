package com.github.jdubo1998.nestednotes

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun nestedItemTest() {
        val bigDogs = Directory("Big Dogs", arrayOf(Note("Pickles", "Pickles is a mix.")))
        val smallDogs = Directory("Small Dogs", arrayOf(Note("Mimi", "Mimi is a Chihuahua.")))
        val mainDir = Directory("Dogs", arrayOf(smallDogs, bigDogs))

        val format = Json { prettyPrint = true }
        println(format.encodeToString(mainDir))

        assertEquals("Mimi is a Chihuahua.", ((mainDir.items[0] as Directory).items[0] as Note).note)
        assertEquals("Pickles is a mix.", ((mainDir.items[1] as Directory).items[0] as Note).note)
    }

    @Test
    fun decodeFromStringTest() {
        val nestedNotesString = """{
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

        val mainDir = Json.decodeFromString<Directory>(nestedNotesString)

        assertEquals("Small Dogs", (mainDir.items[0] as Directory).title)
        assertEquals("Mimi is a Chihuahua.", ((mainDir.items[0] as Directory).items[0] as Note).note)
        assertEquals("Big Dogs", (mainDir.items[1] as Directory).title)
        assertEquals("Pickles is a mix.", ((mainDir.items[1] as Directory).items[0] as Note).note)
        assertEquals("Deceased", (mainDir.items[2] as Note).title)
        assertEquals("Gordas, Chelsea, Cutie Pie, Stony, Lamar, Sparky.", (mainDir.items[2] as Note).note)
    }

    @Test
    fun getTypeTest() {
        val note = Note("Note", "This is a note.")
        val subdir = Directory("Sub Directory", arrayOf())
        val dir = Directory("Directory", arrayOf(note, subdir))

        assertEquals(true, dir.items[0] is Note) // note is a Note
        assertEquals(false, dir.items[0] is Directory) // note is not a Directory
        assertEquals(false, dir.items[1] is Note) // subdir is not a Note
        assertEquals(true, dir.items[1] is Directory) // subdir is a Directory
    }
}
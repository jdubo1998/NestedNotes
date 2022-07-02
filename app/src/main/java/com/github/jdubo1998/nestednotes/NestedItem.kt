package com.github.jdubo1998.nestednotes

import kotlinx.serialization.Serializable

@Serializable
sealed class NestedItem {
    abstract val title: String
}

@Serializable
data class Directory(override val title: String, val items: Array<NestedItem>) : NestedItem() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Directory

        if (title != other.title) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + items.contentHashCode()
        return result
    }
}

@Serializable
data class Note(override val title: String, val note: String) : NestedItem()
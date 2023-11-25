package com.miguejimfer.notesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguejimfer.notesapp.ui.theme.LightBlue
import com.miguejimfer.notesapp.ui.theme.LightGray
import com.miguejimfer.notesapp.ui.theme.LightGreen
import com.miguejimfer.notesapp.ui.theme.LightRed
import com.miguejimfer.notesapp.ui.theme.LightYellow

@Entity
data class NoteBO(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        val noteColors = listOf(LightBlue, LightGray, LightGreen, LightRed, LightYellow)
    }
}

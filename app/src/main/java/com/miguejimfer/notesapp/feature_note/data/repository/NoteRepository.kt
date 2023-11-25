package com.miguejimfer.notesapp.feature_note.data.repository

import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    //Una funcion por cada una en la DB

    fun getNotes(): Flow<List<NoteBO>>

    suspend fun getNoteById(id: Int): NoteBO?

    suspend fun insertNote(note: NoteBO)

    suspend fun deleteNote(note: NoteBO)
}
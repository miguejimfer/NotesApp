package com.miguejimfer.notesapp.feature_note.data.repository

import com.miguejimfer.notesapp.feature_note.data.data_source.NoteDAO
import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl (
    private val dao: NoteDAO
) : NoteRepository {

    override fun getNotes(): Flow<List<NoteBO>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): NoteBO? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: NoteBO) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: NoteBO) {
        dao.deleteNote(note)
    }
}
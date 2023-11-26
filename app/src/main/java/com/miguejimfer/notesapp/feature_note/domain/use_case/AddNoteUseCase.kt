package com.miguejimfer.notesapp.feature_note.domain.use_case

import com.miguejimfer.notesapp.feature_note.data.repository.NoteRepository
import com.miguejimfer.notesapp.feature_note.domain.model.InvalidNoteException
import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO

class AddNoteUseCase (
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: NoteBO) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("El título no debe estar vacío")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("El cuerpo no debe estar vacío")
        }

        repository.insertNote(note)

    }
}
package com.miguejimfer.notesapp.feature_note.domain.use_case

import com.miguejimfer.notesapp.feature_note.data.repository.NoteRepository
import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
class DeleteNoteUseCase(
    private val repository: NoteRepository,
) {

    suspend operator fun invoke(
        note: NoteBO
    ) {
        repository.deleteNote(note)
    }

}
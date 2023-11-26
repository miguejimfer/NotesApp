package com.miguejimfer.notesapp.feature_note.presentation.notes

import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
import com.miguejimfer.notesapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: NoteBO): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()

}
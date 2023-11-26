package com.miguejimfer.notesapp.feature_note.presentation.notes

import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
import com.miguejimfer.notesapp.feature_note.domain.util.NoteOrder
import com.miguejimfer.notesapp.feature_note.domain.util.OrderType

//Pensar en todas las posibles acciones que pueden ser hechas por el usuario
data class NotesState(
    val notes: List<NoteBO> = emptyList(),
    val notesOrder: NoteOrder = NoteOrder.Title(OrderType.Descending),
    val isOrderSectionVisible: Boolean = true,
)

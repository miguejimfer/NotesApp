package com.miguejimfer.notesapp.feature_note.domain.use_case

import com.miguejimfer.notesapp.feature_note.data.repository.NoteRepository
import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
import com.miguejimfer.notesapp.feature_note.domain.util.NoteOrder
import com.miguejimfer.notesapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository,
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<NoteBO>> {
        repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                    }
                }
                OrderType.Descending -> TODO()
            }
        }
    }

}
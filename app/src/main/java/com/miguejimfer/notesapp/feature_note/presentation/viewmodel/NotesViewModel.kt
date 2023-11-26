package com.miguejimfer.notesapp.feature_note.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO
import com.miguejimfer.notesapp.feature_note.domain.use_case.NoteUseCases
import com.miguejimfer.notesapp.feature_note.domain.util.NoteOrder
import com.miguejimfer.notesapp.feature_note.domain.util.OrderType
import com.miguejimfer.notesapp.feature_note.presentation.notes.NotesEvent
import com.miguejimfer.notesapp.feature_note.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val notesState = mutableStateOf(NotesState())
    val getNotesState: State<NotesState> = notesState

    private var recentlyDeletedNote: NoteBO? = null

    private var getNotesjob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
       when(event) {
           is NotesEvent.DeleteNote -> {
               viewModelScope.launch {
                   noteUseCases.deleteNoteUseCase(event.note)
                   recentlyDeletedNote = event.note
               }
           }
           is NotesEvent.Order -> {
               if (notesState.value.notesOrder::class == event.noteOrder::class
                   && notesState.value.notesOrder.orderType == event.noteOrder.orderType) {
                   return
               }
               getNotes(event.noteOrder)
           }
           is NotesEvent.RestoreNote -> {
               viewModelScope.launch {
                   noteUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                   recentlyDeletedNote = null

               }

           }
           is NotesEvent.ToggleOrderSection -> {
               notesState.value = notesState.value.copy(
                   isOrderSectionVisible = !notesState.value.isOrderSectionVisible
               )
           }
       }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesjob?.cancel()
        getNotesjob = noteUseCases.getNotesUseCase(noteOrder)
            .onEach { notes ->
                notesState.value = getNotesState.value.copy(
                    notes = notes,
                    notesOrder = noteOrder,
                )
            }
            .launchIn(viewModelScope)
    }

}
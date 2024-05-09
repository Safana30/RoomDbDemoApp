
package com.spotbus.roomdbdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotbus.roomdbdemo.db.NoteDao
import com.spotbus.roomdbdemo.db.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao:NoteDao
):ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    private val _notes = dao.getNotes()
    val state= combine(_state,_notes){state,notes->
        state.copy(
            notes=notes
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.noteEntity)
                }
            }
            NoteEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingNote = false
                ) }
            }
            NoteEvent.SaveNote -> {
                val note=state.value.note
                if(note.isBlank()){
                    return
                }
                val noteToAdd=NoteEntity(note)

                viewModelScope.launch {
                    dao.upsertNote(noteToAdd)
                }
                _state.update { it.copy(
                    isAddingNote = false,
                    note = ""
                ) }
            }
            NoteEvent.ShowDialog -> {
               _state.update { it.copy(
                    isAddingNote = true
                ) }
            }
            is NoteEvent.setNote -> {

                _state.update { it.copy(
                    note = event.note
                ) }
            }
            is NoteEvent.UpdateNote ->{

            }
        }
    }
}
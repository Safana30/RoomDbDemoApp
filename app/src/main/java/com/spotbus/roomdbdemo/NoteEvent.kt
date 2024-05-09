package com.spotbus.roomdbdemo

import com.spotbus.roomdbdemo.db.NoteEntity


sealed interface NoteEvent {
     data object SaveNote: NoteEvent
    data class setNote(val note:String):NoteEvent
    data object ShowDialog:NoteEvent
    data object HideDialog:NoteEvent
    data object UpdateNote:NoteEvent
    data class DeleteNote(val noteEntity: NoteEntity):NoteEvent
}
package com.spotbus.roomdbdemo

import com.spotbus.roomdbdemo.db.NoteEntity

data class NoteState(
    val notes: List<NoteEntity> = emptyList(),
    val note: String = "",
    val isAddingNote:Boolean=false
)

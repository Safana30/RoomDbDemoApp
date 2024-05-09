package com.spotbus.roomdbdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    val note:String,

    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)

package com.spotbus.roomdbdemo


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(
    state: NoteState,
    onEvent: (NoteEvent)-> Unit,
    modifier: Modifier=Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
                           onEvent(NoteEvent.HideDialog)
        },
        title = {
            Text(text = "Add Note")
        },
        text = {
            Column(verticalArrangement = Arrangement.Center) {
                TextField(value = state.note, onValueChange ={
                    onEvent(NoteEvent.setNote(it))
                }, placeholder = {
                    Text(text = "Write a Note")
                } )
            }
        },
        confirmButton = {
            Box(modifier = Modifier.fillMaxWidth()){
                Button(onClick = { onEvent(NoteEvent.SaveNote) }) {
                    Text(text = "Save")
                }
            }
        }

        )
}
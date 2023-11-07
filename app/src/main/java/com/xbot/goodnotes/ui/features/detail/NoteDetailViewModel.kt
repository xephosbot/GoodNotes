package com.xbot.goodnotes.ui.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xbot.goodnotes.domain.models.InvalidNoteException
import com.xbot.goodnotes.domain.models.Note
import com.xbot.goodnotes.domain.usecases.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NoteDetailState> = MutableStateFlow(NoteDetailState())
    val state: StateFlow<NoteDetailState> = _state.asStateFlow()

    private var noteTitle: String = ""
    private var noteText: String = ""

    fun onEvent(event: NoteDetailEvent) {
        when (event) {
            is NoteDetailEvent.OpenNote -> {
                getNote(event.noteId)
            }
            is NoteDetailEvent.SaveTitle -> {
                noteTitle = event.title
            }
            is NoteDetailEvent.SaveText -> {
                noteText = event.text
            }
            is NoteDetailEvent.Save -> {
                saveNote()
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            try {
                val note = Note(
                    id = state.value.noteId.takeIf { it != -1L } ?: 0,
                    title = noteTitle,
                    text = noteText,
                    color = state.value.noteColor
                )
                noteUseCase.addNote(note)
            } catch (e: InvalidNoteException) {
                //TODO:
            }
        }
    }

    private fun getNote(noteId: Long) {
        viewModelScope.launch {
            noteUseCase.getNote(noteId)?.let { note ->
                noteTitle = note.title
                noteText = note.text
                _state.update { state ->
                    state.copy(
                        noteId = note.id,
                        noteTitle = note.title,
                        noteText = note.text,
                        noteColor = note.color
                    )
                }
            } ?: run {
                noteTitle = ""
                noteText = ""
                _state.update { NoteDetailState() }
            }
        }
    }
}

data class NoteDetailState(
    val noteId: Long = -1L,
    val noteTitle: String = "",
    val noteText: String = "",
    val noteColor: Int = (0xFFF6ECC9).toInt()
)

sealed class NoteDetailEvent {
    data class OpenNote(val noteId: Long) : NoteDetailEvent()
    data class SaveTitle(val title: String) : NoteDetailEvent()
    data class SaveText(val text: String) : NoteDetailEvent()
    data object Save : NoteDetailEvent()
}

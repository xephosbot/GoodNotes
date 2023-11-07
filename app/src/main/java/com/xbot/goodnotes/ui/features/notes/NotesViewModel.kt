package com.xbot.goodnotes.ui.features.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xbot.goodnotes.domain.models.Folder
import com.xbot.goodnotes.domain.models.Note
import com.xbot.goodnotes.domain.usecases.FolderUseCase
import com.xbot.goodnotes.domain.usecases.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    private val folderUseCase: FolderUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NotesState> = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state.asStateFlow()

    private var getNotesJob: Job? = null

    init {
        getNotes()
        getFolders()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.OpenFolder -> {
                openFolder(event.folderId)
            }
        }
    }

    private fun openFolder(folderId: Long?) {
        getNotes(folderId)
        updateState { copy(folderId = folderId) }
    }

    private fun getNotes(folderId: Long? = null) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.getNotes(folderId)
            .onEach { notes ->
                updateState { copy(notes = notes) }
            }
            .launchIn(viewModelScope)
    }

    private fun getFolders() {
        folderUseCase.getFolders()
            .onEach { folders ->
                updateState { copy(folders = folders) }
            }
            .launchIn(viewModelScope)
    }

    private inline fun updateState(transform: NotesState.() -> NotesState) {
        _state.update { it.transform() }
    }
}

data class NotesState(
    val notes: List<Note> = emptyList(),
    val folders: List<Folder> = emptyList(),
    val folderId: Long? = null
)

sealed class NotesEvent {
    data class OpenFolder(val folderId: Long? = null) : NotesEvent()
}

package com.xbot.goodnotes.domain.usecases

data class NoteUseCase(
    val getNote: GetNote,
    val getNotes: GetNotes,
    val addNote: AddNote
)
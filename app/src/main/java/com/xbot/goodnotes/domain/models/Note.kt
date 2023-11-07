package com.xbot.goodnotes.domain.models

data class Note(
    val id: Long = 0,
    val title: String,
    val text: String,
    val color: Int
)

class InvalidNoteException(message: String): Exception(message)

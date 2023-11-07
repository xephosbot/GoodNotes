package com.xbot.goodnotes.domain.usecases

import com.xbot.goodnotes.data.repository.NoteRepository
import com.xbot.goodnotes.domain.models.InvalidNoteException
import com.xbot.goodnotes.domain.models.Note
import com.xbot.goodnotes.domain.toNoteEntity

class AddNote (private val notesRepository: NoteRepository) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note, folderId: Long? = null) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.text.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        val noteEntity = note.toNoteEntity()
        notesRepository.addNote(noteEntity, folderId)
    }
}
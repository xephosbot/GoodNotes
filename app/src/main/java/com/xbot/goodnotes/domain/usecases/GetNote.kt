package com.xbot.goodnotes.domain.usecases

import com.xbot.goodnotes.data.repository.NoteRepository
import com.xbot.goodnotes.domain.models.Note
import com.xbot.goodnotes.domain.toNote
import javax.inject.Inject

class GetNote (private val notesRepository: NoteRepository) {
    suspend operator fun invoke(noteId: Long): Note? {
        return notesRepository.getNoteById(noteId)?.toNote()
    }
}
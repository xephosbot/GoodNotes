package com.xbot.goodnotes.domain.usecases

import com.xbot.goodnotes.data.models.NoteEntity
import com.xbot.goodnotes.data.repository.NoteRepository
import com.xbot.goodnotes.domain.models.Note
import com.xbot.goodnotes.domain.toNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes (private val notesRepository: NoteRepository) {
    operator fun invoke(folderId: Long? = null): Flow<List<Note>> {
        return notesRepository.getNotes(folderId).map{ it.map(NoteEntity::toNote) }
    }
}
package com.xbot.goodnotes.domain.usecases

import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.repository.NoteRepository
import com.xbot.goodnotes.domain.models.Folder
import com.xbot.goodnotes.domain.toFolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFolders (private val notesRepository: NoteRepository) {
    operator fun invoke(): Flow<List<Folder>> {
        return notesRepository.getFolders().map { it.map(FolderEntity::toFolder) }
    }
}
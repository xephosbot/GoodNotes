package com.xbot.goodnotes.domain.usecases

import com.xbot.goodnotes.data.repository.NoteRepository
import com.xbot.goodnotes.domain.models.Folder
import com.xbot.goodnotes.domain.toFolderEntity

class AddFolder (private val notesRepository: NoteRepository) {
    suspend operator fun invoke(folder: Folder) {
        val folderEntity = folder.toFolderEntity()
        notesRepository.addFolder(folderEntity)
    }
}
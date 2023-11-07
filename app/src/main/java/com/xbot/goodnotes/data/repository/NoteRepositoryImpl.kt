package com.xbot.goodnotes.data.repository

import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.models.NoteEntity
import com.xbot.goodnotes.data.models.NoteFolderCrossRef
import com.xbot.goodnotes.data.source.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNotes(folderId: Long?): Flow<List<NoteEntity>> {
        return if (folderId != null) {
            noteDao.getNotesByFolder(folderId)
        } else {
            noteDao.getNotes()
        }
    }

    override suspend fun getNoteById(noteId: Long): NoteEntity? {
        return noteDao.getNoteById(noteId)
    }

    override suspend fun addNote(note: NoteEntity, folderId: Long?) {
        val noteId = noteDao.insertNote(note)
        if (folderId != null) {
            val crossRef = NoteFolderCrossRef(noteId, folderId)
            noteDao.insertNoteFolderCrossRef(crossRef)
        }
    }

    override fun getFolders(): Flow<List<FolderEntity>> {
        return noteDao.getFolders()
    }


    override suspend fun addFolder(folder: FolderEntity) {
        noteDao.insertFolder(folder)
    }
}
package com.xbot.goodnotes.data.repository

import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.models.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(folderId: Long? = null): Flow<List<NoteEntity>>

    suspend fun getNoteById(noteId: Long): NoteEntity?

    suspend fun addNote(note: NoteEntity, folderId: Long? = null)

    fun getFolders(): Flow<List<FolderEntity>>

    suspend fun addFolder(folder: FolderEntity)
}
package com.xbot.goodnotes.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.models.NoteEntity
import com.xbot.goodnotes.data.models.NoteFolderCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Transaction
    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM FolderEntity")
    fun getFolders(): Flow<List<FolderEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id=:noteId")
    suspend fun getNoteById(noteId: Long): NoteEntity?

    @Transaction
    @Query("SELECT * FROM NoteEntity INNER JOIN NoteFolderCrossRef ON NoteEntity.id=NoteFolderCrossRef.noteId WHERE NoteFolderCrossRef.folderId=:folderId")
    fun getNotesByFolder(folderId: Long): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: FolderEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteFolderCrossRef(crossRef: NoteFolderCrossRef)
}
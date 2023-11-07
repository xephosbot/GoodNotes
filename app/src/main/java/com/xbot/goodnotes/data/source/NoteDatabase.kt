package com.xbot.goodnotes.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.models.NoteEntity
import com.xbot.goodnotes.data.models.NoteFolderCrossRef

@Database(
    entities = [NoteEntity::class, FolderEntity::class, NoteFolderCrossRef::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes-db"
    }
}
package com.xbot.goodnotes.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "folderId"])
data class NoteFolderCrossRef(
    val noteId: Long,
    val folderId: Long
)

package com.xbot.goodnotes.domain

import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.models.NoteEntity
import com.xbot.goodnotes.domain.models.Folder
import com.xbot.goodnotes.domain.models.Note

fun Note.toNoteEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    text = text,
    color = color
)

fun Folder.toFolderEntity(): FolderEntity = FolderEntity(
    id = id,
    name = name
)

fun NoteEntity.toNote(): Note = Note(
    id = id,
    title = title,
    text = text,
    color = color
)

fun FolderEntity.toFolder(): Folder = Folder(
    id = id,
    name = name
)

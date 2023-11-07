package com.xbot.goodnotes.di

import android.content.Context
import androidx.room.Room
import com.xbot.goodnotes.BuildConfig
import com.xbot.goodnotes.data.repository.FakeNoteRepository
import com.xbot.goodnotes.data.repository.NoteRepository
import com.xbot.goodnotes.data.repository.NoteRepositoryImpl
import com.xbot.goodnotes.data.source.NoteDatabase
import com.xbot.goodnotes.data.source.NoteDao
import com.xbot.goodnotes.domain.usecases.AddFolder
import com.xbot.goodnotes.domain.usecases.AddNote
import com.xbot.goodnotes.domain.usecases.FolderUseCase
import com.xbot.goodnotes.domain.usecases.GetFolders
import com.xbot.goodnotes.domain.usecases.GetNote
import com.xbot.goodnotes.domain.usecases.GetNotes
import com.xbot.goodnotes.domain.usecases.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteUseCase(noteRepository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            GetNote(noteRepository),
            GetNotes(noteRepository),
            AddNote(noteRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFolderUseCase(noteRepository: NoteRepository): FolderUseCase {
        return FolderUseCase(
            GetFolders(noteRepository),
            AddFolder(noteRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return if (BuildConfig.DEBUG) {
            FakeNoteRepository()
        } else NoteRepositoryImpl(noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: NoteDatabase): NoteDao {
        return appDatabase.noteDao
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
}
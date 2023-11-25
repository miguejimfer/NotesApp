package com.miguejimfer.notesapp.di

import androidx.room.Room
import com.miguejimfer.notesapp.NotesApplication
import com.miguejimfer.notesapp.feature_note.data.data_source.NoteDB
import com.miguejimfer.notesapp.feature_note.data.repository.NoteRepository
import com.miguejimfer.notesapp.feature_note.data.repository.NoteRepositoryImpl
import com.miguejimfer.notesapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.miguejimfer.notesapp.feature_note.domain.use_case.GetNotesUseCase
import com.miguejimfer.notesapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(app: NotesApplication): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class,
            NoteDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDB: NoteDB): NoteRepository {
        return NoteRepositoryImpl(noteDB.noteDao)
    }


    @Provides
    @Singleton
    fun provideNotesUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository = noteRepository),
            deleteNoteUseCase = DeleteNoteUseCase(repository = noteRepository)
        )
    }
}
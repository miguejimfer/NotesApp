package com.miguejimfer.notesapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miguejimfer.notesapp.feature_note.domain.model.NoteBO

@Database(
    entities = [NoteBO::class],
    version = 1
)
abstract class NoteDB: RoomDatabase() {

    abstract val noteDao: NoteDAO

    companion object {
        const val DB_NAME = "notesDB"
    }
}
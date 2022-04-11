package com.fathan.notesapps.data

import androidx.lifecycle.LiveData
import com.fathan.notesapps.data.local.entity.Notes
import com.fathan.notesapps.data.local.room.NotesDao

class NoteRepository (private val notesDao: NotesDao){

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
    val sortByHightPriority: LiveData<List<Notes>> = notesDao.sortByHightPriority()
    val sortByLowPriority: LiveData<List<Notes>> = notesDao.sortByLowPriority()


    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(query: String) : LiveData<List<Notes>> {
        return notesDao.searchNoteByQuery(query)
    }

    suspend fun deleteAllData() {
       return notesDao.deleteAllData()
    }

    suspend fun deleteNote(notes: Notes) {
        return notesDao.deleteNote(notes)
    }

    suspend fun updateNote(notes: Notes) {
       return notesDao.updateNote(notes)
    }
}
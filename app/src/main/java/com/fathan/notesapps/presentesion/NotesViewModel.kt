package com.fathan.notesapps.presentesion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fathan.notesapps.data.NoteRepository
import com.fathan.notesapps.data.local.entity.Notes
import com.fathan.notesapps.data.local.room.NotesDB
import com.fathan.notesapps.data.local.room.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao : NotesDao = NotesDB.getDatabase(application).noteDao()
    private val notesRepository = NoteRepository(notesDao)

    val sortByHighPriority : LiveData<List<Notes>> = notesRepository.sortByHightPriority
    val sortByLowPriority : LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun getAllNotes() : LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(note)
        }
    }

    fun searchNoteByQuery(query: String): LiveData<List<Notes>> {
        return notesRepository.searchNoteByQuery(query)
    }

    fun confirmDeleteAllData (){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun deleteNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }

    fun updateData(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(notes)
        }
    }
}
package com.fathan.notesapps.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fathan.notesapps.data.local.entity.Notes

@Dao
interface NotesDao {
    @Insert
    fun addNote(notes: Notes)

    @Query("SELECT * FROM tb_notes ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes WHERE title LIKE :query")
    fun searchNoteByQuery(query: String) : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END ")
    fun sortByHightPriority() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END ")
    fun sortByLowPriority() : LiveData<List<Notes>>

    @Query("DELETE FROM tb_notes")
    suspend fun deleteAllData()

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)
}
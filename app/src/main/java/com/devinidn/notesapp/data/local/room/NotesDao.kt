package com.devinidn.notesapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devinidn.notesapp.data.local.entity.Notes

@Dao
interface NotesDao {
    @Insert
    suspend fun addNotes(note: Notes)

    @Query("SELECT * FROM tb_notes")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes WHERE title LIKE :querySearch")
    fun searchNoteByQuery(querySearch: String) : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority() : LiveData<List<Notes>>

    @Query("DELETE FROM tb_notes")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: Notes)

    @Update
    suspend fun updateNote(note: Notes)
}
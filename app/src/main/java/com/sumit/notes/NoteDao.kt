package com.sumit.notes

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*

import androidx.room.Update
import java.io.*



@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(note: Note)


//    @Query("DELETE FROM note_table WHERE id= 0")
    @Delete
     fun delete(note: Note)

    @Query("SELECT * FROM NOTE_TABLE WHERE id=:id")
    fun getSpecificNote(id:Int) : Note

    @Query("Select * from note_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("Select * from note_table order by notePriority DESC")
    fun highToLow(): LiveData<List<Note>>

    @Query("Select * from note_table order by notePriority Asc")
    fun lowToHigh(): LiveData<List<Note>>

//    @Query("DELETE FROM note_table")
//    fun deleteAll()

    @Update
    fun updateUsers(note: Note)

    @Query("DELETE FROM note_table WHERE id =:id")
     fun deleteSpecificNote(id:Int)
}

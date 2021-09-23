package com.sumit.notes

import androidx.lifecycle.LiveData



class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    val hightolow: LiveData<List<Note>> = noteDao.highToLow()
    val lowtohigh: LiveData<List<Note>> = noteDao.lowToHigh()

  suspend   fun insert(note: Note) {
     noteDao.insert(note)
    }

     fun delete(note: Note) {

        noteDao.delete(note)
    }


     fun updateNote( note: Note){
        noteDao.updateUsers(note)
    }

     fun specificDelete(id:Int){
         noteDao.deleteSpecificNote(id)
     }

    fun specificGet(id: Int){
        noteDao.getSpecificNote(id)
    }
}

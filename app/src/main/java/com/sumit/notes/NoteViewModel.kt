package com.sumit.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val hightolow: LiveData<List<Note>>
    val lowtohigh: LiveData<List<Note>>

    val repository: NoteRepository

    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
         repository=NoteRepository(dao)
        allNotes=repository.allNotes
        hightolow=repository.hightolow
        lowtohigh=repository.lowtohigh
    }
    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note)=viewModelScope.launch(Dispatchers.IO){
     repository.insert(note)
    }

    fun updateNotes(note: Note)= viewModelScope.launch(Dispatchers.IO){
        repository.updateNote(note)
    }
    fun speficremove(id: Int)= viewModelScope.launch(Dispatchers.IO){
        repository.specificDelete(id)
    }

    fun speficUodate(id: Int)= viewModelScope.launch(Dispatchers.IO){
        repository.specificGet(id)
    }
}

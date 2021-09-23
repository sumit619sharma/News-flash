package com.sumit.notes

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_insert_note.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates

class insertNote : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel
      var priority: String="1"
     var getflt: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_note)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        getflt=intent.getIntExtra("filtercnt",0)

    red.setOnClickListener {
        red.setImageResource(R.drawable.ic_baseline_done_24)
        yelow.setImageResource(0)
        green.setImageResource(0)
        priority="1"
    }
        yelow.setOnClickListener {
            yelow.setImageResource(R.drawable.ic_baseline_done_24)
            red.setImageResource(0)
            green.setImageResource(0)
            priority="2"
        }
        green.setOnClickListener {
            green.setImageResource(R.drawable.ic_baseline_done_24)
            yelow.setImageResource(0)
            red.setImageResource(0)
            priority="3"
        }
    }

    fun noteDone(view: View) {

        val title= txttitle.text.toString()
        val sbtitle= txtsub.text.toString()
        val notedata= notesdata.text.toString()

        createNote(title,sbtitle,notedata)

        var allData: LiveData<List<Note>>
        if (getflt==0){
            allData= viewModel.allNotes
        }else if(getflt==1){
            allData= viewModel.lowtohigh
        }else{
            allData= viewModel.hightolow
        }
    }

    private fun createNote(title: String, sbtitle: String, notedata: String) {
//        val date: Date
//        val sequence: CharSequence= android.text.format.DateFormat.format("MMMM d,YYYY",date.time)
        val noteup= Note(title,sbtitle,notedata,priority)

        viewModel.insertNote(noteup)
   Toast.makeText(this, "notes created succesfully $priority",Toast.LENGTH_SHORT).show()
        finish()
    }
}

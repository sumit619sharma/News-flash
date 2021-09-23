package com.sumit.notes

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_insert_note.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_update_note.*
import kotlinx.android.synthetic.main.delete_bottom_sheet.view.*
import kotlin.properties.Delegates
import java.io.Serializable

class updateNote :Serializable,  AppCompatActivity() {
    var priority: String="1"
//   lateinit var Universal: Note
    lateinit var viewModel: NoteViewModel
     var uid: Int=0
    lateinit var utitle: String
    lateinit var usbtitle: String
    lateinit var unotes: String
    lateinit var upriority: String
    lateinit var notedelete: Note
    lateinit var  noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

//        var mApp= MainActivity()
//         Universal = mApp.univeral

     uid= intent.getIntExtra("id",0)
        utitle= intent.getStringExtra("title").toString()
        usbtitle= intent.getStringExtra("sbtitle").toString()
        unotes= intent.getStringExtra("notesdata").toString()
        upriority= intent.getStringExtra("priority").toString()
//        notedelete = intent.getSerializableExtra("extra_object") as Note

//        notedelete=NoteDatabase.getDatabase(application).getNoteDao().getSpecificNote(uid)
        uptitle.setText(utitle)
        upsub.setText(usbtitle)
        upnotes.setText(unotes)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

    if(upriority.equals("1")){
        upred.setImageResource(R.drawable.ic_baseline_done_24)
    }else if(upriority.equals("2")){
        upyelow.setImageResource(R.drawable.ic_baseline_done_24)
        }else if(upriority.equals("3")){
        upgreen.setImageResource(R.drawable.ic_baseline_done_24)
        }
upred.setOnClickListener {
    upred.setImageResource(R.drawable.ic_baseline_done_24)
    upyelow.setImageResource(0)
    upgreen.setImageResource(0)
    priority="1"
}
        upyelow.setOnClickListener {
            upyelow.setImageResource(R.drawable.ic_baseline_done_24)
            upred.setImageResource(0)
            upgreen.setImageResource(0)
            priority="2"
        }
        upgreen.setOnClickListener {
            upgreen.setImageResource(R.drawable.ic_baseline_done_24)
            upyelow.setImageResource(0)
            upred.setImageResource(0)
            priority="3"
        }
    }

//    Date Format in Kotlin
//    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//    currentDate = sdf.format(Date())

    fun updateNote(view: View) {
        val title= uptitle.text.toString()
        val sbtitle= upsub.text.toString()
        val notedata= upnotes.text.toString()

        createupNote(title,sbtitle,notedata)
    }

    private fun createupNote(title: String, sbtitle: String, notedata: String) {

        val pass= Note(title,sbtitle,notedata,priority)
//            NoteDatabase.getDatabase(application).getNoteDao().getSpecificNote(uid)
//            pass.title=title
//        pass.subtitle=sbtitle
//        pass.notedata=notedata
//        pass.priority=priority

        NoteDatabase.getDatabase(application).getNoteDao().updateUsers(pass)
        uptitle.setText("")
        upsub.setText("")
        upnotes.setText("")
        uppriority.setText("1")
        Toast.makeText(this, "notes updated succesfully $priority ", Toast.LENGTH_SHORT).show()
        finish()
    }



//    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_delete,menu)

    return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId==R.id.ic_delete) {

            val sheetDialog = MiDialog(this)
            val view = layoutInflater.inflate(R.layout.delete_bottom_sheet, null)
            sheetDialog.setContentView(view)
            sheetDialog.setCancelable(false)

            view.btmNo.setOnClickListener {
                sheetDialog.dismiss()
            }
            view.btmyes.setOnClickListener {


              viewModel.speficremove(uid)
                finish()
            }

            sheetDialog.show()
        }
        return true
    }

}

package com.sumit.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : AppCompatActivity(), INotesRVAdapter, Parcelable {
     var filtercnt: Int=0
    lateinit var univeral: Note
    lateinit var viewModel: NoteViewModel
    lateinit var madapter: NotesRVAdaptor
    lateinit var filterNoteAllList: List<Note>
    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel=ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)


        viewModel.allNotes.observe(this, Observer { list ->
            recyclerView.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            madapter=NotesRVAdaptor(this,this)


            list?.let {
                filterNoteAllList=it
                madapter.updateList(it)
            }
            recyclerView.adapter=madapter
         })

        no_filter.setBackgroundResource(R.drawable.filter_selected_shape)
        no_filter.setOnClickListener {
            filtercnt=0
            loadData(0);
            htl.setBackgroundResource(R.drawable.filter_shape)
            lth.setBackgroundResource(R.drawable.filter_shape)
            no_filter.setBackgroundResource(R.drawable.filter_selected_shape)

        }
        htl.setOnClickListener {
           filtercnt=2
            loadData(2);
            no_filter.setBackgroundResource(R.drawable.filter_shape)
            lth.setBackgroundResource(R.drawable.filter_shape)
            htl.setBackgroundResource(R.drawable.filter_selected_shape)

        }
        lth.setOnClickListener {
           filtercnt=1
            loadData(1);
            htl.setBackgroundResource(R.drawable.filter_shape)
            no_filter.setBackgroundResource(R.drawable.filter_shape)
            lth.setBackgroundResource(R.drawable.filter_selected_shape)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_notes,menu)
       val searchView: SearchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView
//          val serchView: SearchView= menuItem?.actionView as SearchView
       searchView.queryHint="Search note here ..."


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(text: String?): Boolean {
                var filterNamae= ArrayList<Note>()
                for (ti in filterNoteAllList){
                    if(ti.title?.contains(text!!)!! || ti.subtitle?.contains(text!!)!!) {
                        filterNamae.add(ti)
                    } }
                madapter.searchNote(filterNamae)
                madapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextSubmit(qString: String?): Boolean {

                return true
            }
        })

        return true
    }

//    private fun notesFilter(text: String?) {
////    Log.e("@@@@","notesFilter "+text)
//
////        recyclerView.adapter=madapter
//
//    }

    public fun loadData(i: Int) {
       if(i==0){

           viewModel.allNotes.observe(this, Observer { list ->
               setAdapter()

               list?.let {

                   madapter.updateList(it)
                   filterNoteAllList=it
               }
           })
       } else if(i==1){

           viewModel.lowtohigh.observe(this, Observer { list ->
               setAdapter()
               list?.let {

                   madapter.updateList(it)
                   filterNoteAllList=it
               }
           })
       }else{
           viewModel.hightolow.observe(this, Observer { list ->
               setAdapter()
               list?.let {

                   madapter.updateList(it)
                   filterNoteAllList=it
               }
           }) } }

    fun setAdapter(){
        recyclerView.layoutManager= GridLayoutManager(this,2)
         madapter=NotesRVAdaptor(this,this)
        recyclerView.adapter=madapter

    }

    override fun onItemClicked(note: Note) {


//        viewModel.deleteNote(note)
//        Toast.makeText(this,"${note.title} Deleted",Toast.LENGTH_SHORT).show()
        val intent=Intent(this,updateNote::class.java)
        intent.putExtra("id",note.nid)
        intent.putExtra("title",note.title)
        intent.putExtra("sbtitle",note.subtitle)
        intent.putExtra("notesdata",note.notedata)
        intent.putExtra("priority",note.priority)
//        intent.putExtra("extra_object", note as Serializable?)

        startActivity(intent)
    }


    fun addNote(view: View) {
        val intent=Intent(this,insertNote::class.java)
         intent.putExtra("filtercnt",filtercnt)
        startActivity(intent)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }


}




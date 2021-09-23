package com.sumit.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import  java.io.Serializable

class NotesRVAdaptor(private  val context: Context,private val listener: INotesRVAdapter): Serializable, RecyclerView.Adapter<NotesRVAdaptor.NoteViewHolder>() {

   private var allNotes= ArrayList<Note>()
//     lateinit var filterNotes: ArrayList<Note>

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleView=itemView.findViewById<TextView>(R.id.txtnote)
        val subView=itemView.findViewById<TextView>(R.id.itemsub)
//       val dateView=itemView.findViewById<TextView>(R.id.itemdate)
        val priorityView=itemView.findViewById<ImageView>(R.id.priorityimg)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchNote(filterNotes: List<Note>) {
        allNotes= filterNotes as ArrayList<Note>

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
       viewHolder.itemView.setOnClickListener {
        listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentView=allNotes[position]
        holder.titleView.text=currentView.title
        holder.subView.text=currentView.subtitle
//        holder.dateView.text=currentView.notedata
        if(currentView.priority=="1") {
            holder.priorityView.setBackgroundResource(R.drawable.red_shape)
        }else if(currentView.priority=="2"){
            holder.priorityView.setBackgroundResource(R.drawable.yellow_shape)
        } else if(currentView.priority=="3"){
            holder.priorityView.setBackgroundResource(R.drawable.green_shape)
        }
//        holder.itemView.setOnClickListener {
//            val intent= Intent(this, updateNote::class.java)
//            startActivity(intent)
//            finish()
//        }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}

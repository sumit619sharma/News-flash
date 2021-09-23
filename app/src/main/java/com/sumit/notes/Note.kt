package com.sumit.notes

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
class Note( @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "subtitle") var subtitle: String,
@ColumnInfo(name = "notedata") var notedata: String,
@ColumnInfo(name = "notePriority") var priority: String
){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var nid: Int?=null
}





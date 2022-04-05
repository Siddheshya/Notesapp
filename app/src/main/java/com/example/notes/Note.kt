package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "notesTable")
class Note(@ColumnInfo(name="title")val title:String,@ColumnInfo(name = "description")val notedescription:String,@ColumnInfo(name="timestamp")val timestamp:String ) {
    @PrimaryKey(autoGenerate = true) var id=0

}
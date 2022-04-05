package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteonclickDelete, Noteonclick {
    lateinit var notesRV:RecyclerView
    lateinit var add:FloatingActionButton
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRV=findViewById(R.id.idNotes)
        add=findViewById(R.id.floating)
        notesRV.layoutManager=LinearLayoutManager(this)
        val adapter=udapter(this,this,this)
        notesRV.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list->
            list?.let{
                adapter.updateList(it)
            }
        })
        add.setOnClickListener{
            val intent= Intent(this,AddNote::class.java)
            startActivity(intent)
            this.finish()
        }


    }

    override fun onDelete(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} Delete", Toast.LENGTH_SHORT).show()

    }

    override fun onNoteClick(note: Note) {
        val intent= Intent(this,AddNote::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.title)
        intent.putExtra("noteDescription",note.notedescription)
        intent.putExtra("ID",note.id)
        startActivity(intent)
        this.finish()

    }


}
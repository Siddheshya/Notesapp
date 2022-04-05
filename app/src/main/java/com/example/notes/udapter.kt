package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class udapter(var context:Context,val noteonclickDelete: NoteonclickDelete,val noteonclick: Noteonclick):RecyclerView.Adapter<udapter.Viewholder>() {
    inner class Viewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val noteTV=itemView.findViewById<TextView>(R.id.title)
        val timeTV=itemView.findViewById<TextView>(R.id.Time)
        val deleteTV=itemView.findViewById<ImageView>(R.id.delete)
    }
    private val allNotes=ArrayList<Note>()

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.noteTV.setText(allNotes[position].title)
        holder.timeTV.setText(allNotes.get(position).timestamp)
        holder.deleteTV.setOnClickListener{
            noteonclickDelete.onDelete(allNotes.get(position))
        }
        holder.itemView.setOnClickListener{
            noteonclick.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val item=LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        return Viewholder(item)
    }


}
interface NoteonclickDelete{
    fun onDelete(note:Note)
}
interface Noteonclick{
    fun onNoteClick(note:Note)
}

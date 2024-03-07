package com.example.examen02.flora

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.R
import com.example.examen02.models.Flora

class ReadFloraAdapter(private val sistemas: List<Flora>) : RecyclerView.Adapter<ReadFloraAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val colorTextView : TextView = view.findViewById(R.id.colorFloraTextView)
        val alturaFlora : TextView = view.findViewById(R.id.alturaFloraTextView)
        val enExtinsionFlora : TextView = view.findViewById(R.id.enExtinsionFloraTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.flora_read_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sistema = sistemas[position]
        holder.nombreTextView.text = sistema.nombre
        holder.colorTextView.text = sistema.color
        holder.alturaFlora.text = sistema.altura.toString()
        holder.enExtinsionFlora.text = sistema.enExtincion.toString()
    }

    override fun getItemCount() = sistemas.size
}
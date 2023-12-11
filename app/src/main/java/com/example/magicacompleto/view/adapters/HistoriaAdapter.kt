package com.example.magicacompleto.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicacompleto.R
import com.example.magicacompleto.model.response.AdapterH

class HistoriaAdapter (
    val context: Context,
    var listaComment: List<HistoriaAdapter>
    ): RecyclerView.Adapter<HistoriaAdapter.ViewHolder>() {

        class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
            val name = item.findViewById(R.id.name) as TextView
            val commment = item.findViewById(R.id.comment) as TextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_personajes, parent, false)
            return ViewHolder(vista)
        }

        override fun getItemCount(): Int {
            return listaComment.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val AdapterH = listaComment[position]

            // Utiliza la biblioteca Glide para cargar la imagen desde la URL en el ImageView
            Glide.with(context)
                .load(AdapterH.image)
                .into(holder.image)

            // Establece el nombre y el comentario en los TextView
            holder.name.text = AdapterH.name
            holder.commment.text = AdapterH.comment

        }
    }
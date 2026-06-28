package com.example.rania_lngshot.home.agenda_desa

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rania_lngshot.databinding.ItemAgendaBinding

class AgendaAdapter(

    private val list: List<AgendaModel>

) : RecyclerView.Adapter<AgendaAdapter.ViewHolder>() {

    inner class ViewHolder(

        val binding: ItemAgendaBinding

    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemAgendaBinding.inflate(

            LayoutInflater.from(parent.context),

            parent,

            false

        )

        return ViewHolder(binding)

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.tvTitle.text = item.title

        holder.binding.tvDate.text = item.date

        holder.binding.tvTime.text = item.time

        holder.binding.btnDetail.setOnClickListener {

            val intent = Intent(

                holder.itemView.context,

                AgendaDetailActivity::class.java

            )

            intent.putExtra("title", item.title)
            intent.putExtra("date", item.date)
            intent.putExtra("time", item.time)
            intent.putExtra("location", item.location)
            intent.putExtra("description", item.description)

            holder.itemView.context.startActivity(intent)

        }

    }

}
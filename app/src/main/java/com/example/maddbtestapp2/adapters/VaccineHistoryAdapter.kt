package com.example.maddbtestapp2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.EditDateActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.VaccineHistoryItem

class VaccineHistoryAdapter(private val items: List<VaccineHistoryItem>) :
    RecyclerView.Adapter<VaccineHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccineNameTextView: TextView = itemView.findViewById(R.id.vaccNametv)
        val doseNumberTextView: TextView = itemView.findViewById(R.id.nextDosetv)
        val editButton: Button = itemView.findViewById(R.id.btnEdit)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vaccine_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.vaccineNameTextView.text = item.vaccineName
        holder.doseNumberTextView.text = item.doseNumber.toString()

        holder.editButton.setOnClickListener {
            val intent = Intent(it.context, EditDateActivity::class.java)
            intent.putExtra("item", item.administrationDate.time)
            it.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            items.remoeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = items.size
}
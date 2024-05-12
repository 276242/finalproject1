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
import com.example.maddbtestapp2.vaccine.VaccineHistoryItem
import java.text.SimpleDateFormat
import java.util.Locale

class VaccineHistoryAdapter(
    var items: List<VaccineHistoryItem>,
    private val onDeleteClickListener: OnDeleteClickListener
) : RecyclerView.Adapter<VaccineHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccAdminDatetv: TextView = itemView.findViewById(R.id.vaccAdmDatetv)
        val editButton: Button = itemView.findViewById(R.id.btnEdit)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
    }
    interface OnDeleteClickListener {
        fun onDeleteClicked(item: VaccineHistoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vaccine_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val justDate = dateFormat.format(item.administrationDate)
        holder.vaccAdminDatetv.text = "Administered on: $justDate"

        holder.editButton.setOnClickListener {
            val intent = Intent(it.context, EditDateActivity::class.java)
            intent.putExtra("item", item.administrationDate.time)
            it.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClickListener.onDeleteClicked(item)
        }
    }

    override fun getItemCount() = items.size
}
//
//
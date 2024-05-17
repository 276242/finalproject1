package com.example.maddbtestapp2.adapters

import android.app.DatePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.EditDateActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.databaseConfig.DbConnect
import com.example.maddbtestapp2.history.HistoryQueries
import com.example.maddbtestapp2.vaccine.VaccineHistoryItem
import com.example.maddbtestapp2.vaccine.VaccinesQueries
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale

class VaccineHistoryAdapter(
    var items: MutableList<VaccineHistoryItem>,
    private val onDeleteClickListener: OnDeleteClickListener,
    private val vaccineName: String
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

        holder.editButton.tag = item.historyId

        holder.editButton.setOnClickListener {
            println(item.historyId)
            val calendar = Calendar.getInstance()
            calendar.time = item.administrationDate
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(it.context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = GregorianCalendar(selectedYear, selectedMonth, selectedDayOfMonth).time
                println(item.administrationDate)
                item.administrationDate = java.sql.Date(selectedDate.time)
                println(item.administrationDate)
                holder.vaccAdminDatetv.text = "Administered on: ${dateFormat.format(selectedDate)}"


                CoroutineScope(Dispatchers.IO).launch {
                    val connection = DbConnect.getConnection()
                    val historyQueries = HistoryQueries(connection)

                    items[position].historyId?.let { historyId ->
                        historyQueries.updateAdministrationDate(historyId, items[position].administrationDate)
                    }
                }

                notifyItemChanged(position)
            }, year, month, day).show()
        }

        holder.deleteButton.setOnClickListener {
            println(item.historyId)
            onDeleteClickListener.onDeleteClicked(item)

            CoroutineScope(Dispatchers.IO).launch {
                val connection = DbConnect.getConnection()
                val historyQueries = HistoryQueries(connection)
                historyQueries.deleteHistory(item.historyId!!)

                CoroutineScope(Dispatchers.Main).launch {
                    items = items.filter { it.historyId != item.historyId }.toMutableList()
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun addNewItem(item: VaccineHistoryItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}
//
//
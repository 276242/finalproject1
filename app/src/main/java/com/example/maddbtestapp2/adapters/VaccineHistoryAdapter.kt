package com.example.maddbtestapp2.adapters

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.databaseConfig.DbConnect
import com.example.maddbtestapp2.history.HistoryQueries
import com.example.maddbtestapp2.vaccine.VaccineHistoryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale

/**
 * Adapter class for displaying a list of VaccineHistoryItems in a RecyclerView.
 * @param items List of VaccineHistoryItems to display.
 * @param onDeleteClickListener Callback function to handle delete click events.
 * @param vaccineName Name of the vaccine to display.
 */
class VaccineHistoryAdapter(
    var items: MutableList<VaccineHistoryItem>,
    private val onDeleteClickListener: OnDeleteClickListener,
    private val vaccineName: String
) : RecyclerView.Adapter<VaccineHistoryAdapter.ViewHolder>() {

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     * @param itemView The root view of the item layout.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccAdminDatetv: TextView = itemView.findViewById(R.id.vaccAdmDatetv)
        val editButton: Button = itemView.findViewById(R.id.btnEdit)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
    }

    /**
     * Interface to handle delete click events.
     */
    interface OnDeleteClickListener {
        fun onDeleteClicked(item: VaccineHistoryItem)
    }

    /**
     * Creates a new ViewHolder when there are no existing ViewHolders that the RecyclerView can reuse.
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vaccine_history, parent, false)
        return ViewHolder(view)
    }

    /**
     * Binds the data to the views of the ViewHolder.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < items.size) {
            val item = items[position]

            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val justDate = dateFormat.format(item.administrationDate)
            holder.vaccAdminDatetv.text = "Administered on: $justDate"

            holder.editButton.tag = item.historyId

            holder.editButton.setOnClickListener {
                val calendar = Calendar.getInstance()
                calendar.time = item.administrationDate
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(it.context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val selectedDate = GregorianCalendar(selectedYear, selectedMonth, selectedDayOfMonth).time
                    item.administrationDate = java.sql.Date(selectedDate.time)
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
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = items.size

    /**
     * Adds a new item to the list and notifies the adapter.
     * @param item The VaccineHistoryItem to add.
     */
    fun addNewItem(item: VaccineHistoryItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}


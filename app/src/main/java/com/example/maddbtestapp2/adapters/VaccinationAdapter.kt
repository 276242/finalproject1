package com.example.maddbtestapp2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.vaccine.Vaccines
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter class for displaying a list of Vaccines in a RecyclerView.
 * @param itemList List of Vaccines to display.
 * @param onItemClick Callback function to handle item click events.
 */
class VaccinationAdapter(private var itemList: List<Vaccines>, private val onItemClick: (Vaccines) -> Unit) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     * @param itemView The root view of the item layout.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccineNametv: TextView = itemView.findViewById(R.id.vaccAdmDatetv)
        val nextDosetv: TextView = itemView.findViewById(R.id.nextDosetv)
    }

    /**
     * Creates a new ViewHolder when there are no existing ViewHolders that the RecyclerView can reuse.
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vaccine, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Binds the data to the views of the ViewHolder.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        holder.vaccineNametv.text = itemList[position].vaccineName

        val formattedNextDoseDate = dateFormat.format(currentItem.nextDoseDate)
        holder.nextDosetv.text = "Next dose: $formattedNextDoseDate"

        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return itemList.size
    }
}

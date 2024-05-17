/**
 * VaccinationAdapter is a RecyclerView.Adapter that displays a list of Vaccines.
 *
 * This adapter is responsible for creating the view holders which hold the description of each Vaccine item,
 * replacing the contents of a view holder, and returning the total number of items in the data set.
 *
 * @property itemList The list of Vaccines to display.
 * @property onItemClick A function that is invoked when an item in the list is clicked.
 */
package com.example.maddbtestapp2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.vaccine.Vaccines
import java.text.SimpleDateFormat
import java.util.Locale

class VaccinationAdapter(private var itemList: List<Vaccines>, private val onItemClick: (Vaccines) -> Unit) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    /**
     * ViewHolder provides a reference to the views for each data item.
     *
     * Each data item is just a Vaccines object which is presented in a view that is managed by the ViewHolder.
     * The view for each item is provided by the RecyclerView when a ViewHolder is created.
     *
     * @property vaccineNametv The TextView that displays the name of the vaccine.
     * @property nextDosetv The TextView that displays the date of the next dose.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccineNametv: TextView = itemView.findViewById(R.id.vaccAdmDatetv)
        val nextDosetv: TextView = itemView.findViewById(R.id.nextDosetv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vaccine, parent, false)
        return ViewHolder(itemView)
    }

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
    override fun getItemCount(): Int {
        return itemList.size
    }

}

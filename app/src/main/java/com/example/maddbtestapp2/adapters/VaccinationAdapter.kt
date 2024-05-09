package com.example.maddbtestapp2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.EditDateActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.vaccine.Vaccines

class VaccinationAdapter(private var itemList: List<Vaccines>, private val onItemClick: (Vaccines) -> Unit) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

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

        holder.vaccineNametv.text = itemList[position].vaccineName
        holder.nextDosetv.text = itemList[position].nextDoseDate.toString()
        itemList[position]


        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }

//            val vaccine = itemList[position]
//
//            holder.itemView.setOnClickListener {
//                val intent = Intent(it.context, EditDateActivity::class.java)
//                intent.putExtra("vaccine", vaccine.vaccineName)
//                it.context.startActivity(intent)
//            }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

package com.example.onyourway

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onyourway.room.Delivery

class DeliveryAdapter : ListAdapter<Delivery, DeliveryAdapter.DeliveryViewHolder>(DeliveryComparator ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        Log.e("createor","aqui" )

        return DeliveryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        Log.e("onbindholder","aqui" )

        val current = getItem(position)

        holder.bind(current.toString())
    }

    class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val deliveryItemView: TextView = itemView.findViewById(R.id.Deliverytitle)

        fun bind(text: String?) {
            Log.e("bind","aqui" )

            deliveryItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): DeliveryViewHolder {
                Log.e("companinpr","aqui" )

                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_delivery, parent, false)
                Log.e("deliveryview","aqui" )

                return DeliveryViewHolder(view)
            }
        }
    }

    class DeliveryComparator  : DiffUtil.ItemCallback<Delivery>() {
        override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            Log.e("same","aqui" )

            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            Log.e("contentssame","aqui" )

            return oldItem.toString() == newItem.toString()
        }
    }

}

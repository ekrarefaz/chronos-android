package com.icedtea.chronos.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icedtea.chronos.R
import com.icedtea.chronos.model.WatchDataClass
import kotlinx.android.synthetic.main.recycler_list_item.view.*

class RecyclerCollectionAdapter(private val itemList:List<WatchDataClass>,
                                private val clickListener: (WatchDataClass) -> Unit): RecyclerView.Adapter<RecyclerCollectionAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.list_img
        val listHeading: TextView = itemView.list_heading
        val listDescription: TextView = itemView.list_description
        var price: TextView = itemView.price
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_list_item,
            parent, false
        )
        return CustomViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.listHeading.setText(currentItem.watchName)
        holder.listDescription.setText(currentItem.watchDes)
        holder.price.setText(currentItem.watchPrice.toString())
        holder.itemView.setOnClickListener {
            clickListener(currentItem)
        }
    }
    override fun getItemCount() = itemList.size
}

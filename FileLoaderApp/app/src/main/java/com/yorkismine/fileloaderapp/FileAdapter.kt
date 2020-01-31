package com.yorkismine.fileloaderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yorkismine.fileloaderapp.R

class FileAdapter(private val list: List<DownloadedItem>) : RecyclerView.Adapter<FileAdapter.FileHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return FileHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        val item: DownloadedItem = list[position]
        holder.siteTextView.text = item.site
        val size = "${item.sizeOfItem} MB"
        holder.sizeOfFileTV.text = size
        holder.dateTextView.text = item.date
    }

    class FileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val siteTextView: TextView = itemView.findViewById(R.id.site_tv)
        val sizeOfFileTV: TextView = itemView.findViewById(R.id.size_tv)
        val dateTextView: TextView = itemView.findViewById(R.id.date_tv)
    }
}
package com.yorkismine.fileloaderapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yorkismine.fileloaderapp.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val pref = getPreferences(Context.MODE_PRIVATE)

        val intent = intent
        val list =
            intent.getParcelableArrayListExtra<DownloadedItem>("History") as ArrayList<DownloadedItem>
        var i = 0
        if (list.isNotEmpty()) {
            while (i <= list.size)
                for (item in list) {
                    val editor = pref.edit()
                    editor.putString(DATE_ITEM + "$i", item.date)
                    editor.putString(SITE_ITEM + "$i", item.site)
                    editor.putLong(SIZE_ITEM + "$i", item.sizeOfItem)
                    i++
                    editor.apply()
                }
        } else {
            var j = 0
            while (j <= i){
                val date: String? = pref.getString(DATE_ITEM + "$j", "")
                val size: Long = pref.getLong(SIZE_ITEM + "$j", 0)
                val site: String? = pref.getString(SITE_ITEM + "$j", "")
                j++
                Log.d("YYYY", "$j")
                val item = DownloadedItem(date, site, size)
                list.add(item)
            }
        }

        val adapter = FileAdapter(list)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        const val DATE_ITEM = "DATE_ITEM"
        const val SIZE_ITEM = "SIZE_ITEM"
        const val SITE_ITEM = "SITE_ITEM"
    }
}
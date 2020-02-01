package com.yorkismine.fileloaderapp.presenter

import android.util.Log
import com.yorkismine.fileloaderapp.contracts.HistoryContract
import com.yorkismine.fileloaderapp.model.DownloadedItem
import com.yorkismine.fileloaderapp.view.HistoryActivity

class HistoryPresenter(val view: HistoryContract.View) : HistoryContract.Presenter {
    override fun loadHistory(): List<DownloadedItem> {
        Log.d("YYYY", "isCalled")
        val pref = view.preferences
        val list: ArrayList<DownloadedItem> = ArrayList()
        var i = 0
        i = pref.getInt("INT_SAVER", 0)
        Log.d("YYYY", "i is $i")
        var j = 0
        while (j < i) {
            val date: String? = pref.getString(HistoryActivity.DATE_ITEM + "$j", "")
            val size: Long = pref.getLong(HistoryActivity.SIZE_ITEM + "$j", 0)
            val site: String? = pref.getString(HistoryActivity.SITE_ITEM + "$j", "")
            Log.d("YYYY", "loading - $j")
            j++
            val item = DownloadedItem(
                date,
                site,
                size
            )
            list.add(item)
        }

        return list
    }

}
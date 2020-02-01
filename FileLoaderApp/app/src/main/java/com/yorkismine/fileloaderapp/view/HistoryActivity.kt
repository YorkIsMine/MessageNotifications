package com.yorkismine.fileloaderapp.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.yorkismine.fileloaderapp.R
import com.yorkismine.fileloaderapp.adapters.FileAdapter
import com.yorkismine.fileloaderapp.contracts.HistoryContract
import com.yorkismine.fileloaderapp.model.DownloadedItem
import com.yorkismine.fileloaderapp.presenter.HistoryPresenter
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val presenter: HistoryContract.Presenter = HistoryPresenter(this)

        val adapter = FileAdapter(presenter.loadHistory())
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        const val DATE_ITEM = "DATE_ITEM"
        const val SIZE_ITEM = "SIZE_ITEM"
        const val SITE_ITEM = "SITE_ITEM"
    }

    override fun getPreferences(): SharedPreferences {
        return applicationContext.getSharedPreferences("GENERAL_PREF", Context.MODE_PRIVATE)
    }
}
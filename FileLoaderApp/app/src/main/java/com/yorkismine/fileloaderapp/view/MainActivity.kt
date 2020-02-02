package com.yorkismine.fileloaderapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.yorkismine.fileloaderapp.model.DownloadedItem
import com.yorkismine.fileloaderapp.R
import com.yorkismine.fileloaderapp.contracts.MainContract
import com.yorkismine.fileloaderapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), MainContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val downloaderET = downloadIn_et;

        val presenter: MainContract.Presenter = MainPresenter(this)

        // val text = "https://icons.androeed.ru/icons/2020/01/27/w_160_shadow-fight2---ico.png"
        execute_btn.setOnClickListener {
            val path = externalCacheDir!!.path + "/netFile"
            val text = downloaderET.text.toString()
            presenter.downloadFile(text, path)
            presenter.uploadFiles()
        }

        showH_btn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.file_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        return true
    }

    override fun isHistoryButtonClickable(isClickable: Boolean) {
        showH_btn.isClickable = isClickable
    }

    override fun showResult() {
        Toast.makeText(this, "Done!", Toast.LENGTH_LONG).show()
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun getPreferences(): SharedPreferences {
        return applicationContext.getSharedPreferences("GENERAL_PREF", Context.MODE_PRIVATE)
    }
}
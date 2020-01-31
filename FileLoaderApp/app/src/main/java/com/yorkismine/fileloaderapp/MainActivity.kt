package com.yorkismine.fileloaderapp

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.telecom.Connection
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.yorkismine.fileloaderapp.NetworkHelper
import com.yorkismine.fileloaderapp.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<DownloadedItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val downloaderET = downloadIn_et;

        list = ArrayList()
        execute_btn.setOnClickListener {


            //            val text = downloaderET.text.toString()
            val text = "https://icons.androeed.ru/icons/2020/01/27/w_160_shadow-fight2---ico.png"
            val path = externalCacheDir!!.path + "/netFile"
            var length: Long = 0

            val l: Long = 1
            showH_btn.isClickable = false
            Observable.just(l)
                .observeOn(Schedulers.io())
                .map(object : Function<Long, Long> {
                    override fun apply(t: Long): Long {
                        var n = t
                        n = NetworkHelper.downloadAndGetSizeOfFile(text, path)
                        Log.d("YYYY", "n is $n")

                        return n
                    }

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onComplete() {
                        Toast.makeText(applicationContext, "mm?", Toast.LENGTH_LONG).show()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Long) {
                        Log.d("YYYY", t.toString())
                        length = t
                        Log.d("YYYY", "done!")
                        Looper.prepare()
                        Toast.makeText(applicationContext, "Done!", Toast.LENGTH_LONG).show()
                        val site = text.subSequence(0, (text.indexOf("/", 10)) + 1) as String
                        Log.d("YYYY", site)
                        val s = SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH)
                        val d = Date()

                        val item = DownloadedItem(s.format(d), site, length / 1024 / 1024)
                        list.add(item)
                        showH_btn.isClickable = true
                        Looper.loop()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Looper.prepare()
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                        Looper.loop()
                    }

                })
        }

        showH_btn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("History", list)
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
}
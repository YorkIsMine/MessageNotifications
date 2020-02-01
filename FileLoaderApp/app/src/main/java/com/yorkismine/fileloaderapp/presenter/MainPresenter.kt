package com.yorkismine.fileloaderapp.presenter

import android.os.Looper
import android.util.Log
import com.yorkismine.fileloaderapp.model.DownloadedItem
import com.yorkismine.fileloaderapp.utils.NetworkHelper
import com.yorkismine.fileloaderapp.contracts.MainContract
import com.yorkismine.fileloaderapp.contracts.MainContract.Presenter
import com.yorkismine.fileloaderapp.view.HistoryActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(private val view: MainContract.View) : Presenter {
    val list: ArrayList<DownloadedItem> = ArrayList()

    override fun downloadFile(url: String, dir: String) {
        var length: Long = 0

        val l: Long = 1
        view.isHistoryButtonClickable(false)
        Observable.just(l)
            .observeOn(Schedulers.io())
            .map(object : Function<Long, Long> {
                override fun apply(t: Long): Long {
                    var n = t
                    n = NetworkHelper.downloadAndGetSizeOfFile(url, dir)
                    Log.d("YYYY", "n is $n")

                    return n
                }

            })
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Long) {
                    Log.d("YYYY", t.toString())
                    length = t
                    Log.d("YYYY", "done!")
                    Looper.prepare()
                    view.showResult()
                    val site = url.subSequence(0, (url.indexOf("/", 10)) + 1) as String
                    Log.d("YYYY", site)
                    val s = SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH)
                    val d = Date()

                    val item = DownloadedItem(
                        s.format(d), site, length / 1024 / 1024
                    )
                    list.add(item)
                    view.isHistoryButtonClickable(true)
                    Looper.loop()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Looper.prepare()
                    view.showError()
                    view.isHistoryButtonClickable(true)
                    Looper.loop()
                }

            })
    }

    override fun uploadFiles() {
        val pref = view.preferences
        var i = pref.getInt("INT_SAVER", 0)
        if ((i == 0) or (i < list.size)) {
            if (list.isNotEmpty()) {
                while (i <= list.size) {
                    for (item in list) {
                        val editor = pref.edit()
                        editor.putString(HistoryActivity.DATE_ITEM + "$i", item.date)
                        editor.putString(HistoryActivity.SITE_ITEM + "$i", item.site)
                        editor.putLong(HistoryActivity.SIZE_ITEM + "$i", item.sizeOfItem)
                        editor.apply()
                        Log.d("YYYY", "$i is normal")
                    }
                    i++
                }
                val saverI = pref.edit()
                saverI.putInt("INT_SAVER", i)
                saverI.apply()
            }
        } else {
            Log.d("YYYY", "else is called")
            Log.d("YYYY", "size is ${list.size}")
            var j = 0
            i++
            while (j <= list.size){
                for (item in list){
                    val editor = pref.edit()
                    editor.putString(HistoryActivity.DATE_ITEM + "$i", item.date)
                    editor.putString(HistoryActivity.SITE_ITEM + "$i", item.site)
                    editor.putLong(HistoryActivity.SIZE_ITEM + "$i", item.sizeOfItem)
                    editor.apply()
                    Log.d("YYYY", "$i is normal")
                }
                j++
                i++
            }
        }
    }
}
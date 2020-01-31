package com.yorkismine.fileloaderapp

import android.os.Parcel
import android.os.Parcelable

data class DownloadedItem(
    val date: String?,
    val site: String?,
    val sizeOfItem: Long) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(site)
        parcel.writeLong(sizeOfItem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DownloadedItem> {
        override fun createFromParcel(parcel: Parcel): DownloadedItem {
            return DownloadedItem(parcel)
        }

        override fun newArray(size: Int): Array<DownloadedItem?> {
            return arrayOfNulls(size)
        }
    }

}
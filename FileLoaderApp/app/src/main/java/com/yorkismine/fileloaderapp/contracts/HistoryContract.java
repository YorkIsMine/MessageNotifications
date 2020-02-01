package com.yorkismine.fileloaderapp.contracts;

import android.content.SharedPreferences;

import com.yorkismine.fileloaderapp.model.DownloadedItem;

import java.util.List;

public interface HistoryContract {
    interface View {
        SharedPreferences getPreferences();
    }

    interface Presenter {
        List<DownloadedItem> loadHistory();
    }
}

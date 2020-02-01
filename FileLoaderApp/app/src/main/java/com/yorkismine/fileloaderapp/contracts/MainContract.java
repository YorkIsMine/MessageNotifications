package com.yorkismine.fileloaderapp.contracts;

import android.content.SharedPreferences;

public interface MainContract {
    interface View {
        void isHistoryButtonClickable(boolean isClickable);

        void showResult();

        void showError();

        SharedPreferences getPreferences();
    }

    interface Presenter {
        void downloadFile(String url, String dir);

        void uploadFiles();
    }
}

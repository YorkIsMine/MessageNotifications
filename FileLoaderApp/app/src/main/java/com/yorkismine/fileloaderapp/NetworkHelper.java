package com.yorkismine.fileloaderapp;

import android.net.Uri;
import android.os.Build;
import android.os.FileUtils;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class NetworkHelper {
    public static long downloadAndGetSizeOfFile(String strUrl, String dir) throws Exception {
        long length = 0;

        URL website = new URL(strUrl);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(dir);
        length = fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//        FileUtils.copy(new URL(strUrl).openStream(), new FileOutputStream(dir));

        rbc.close();
        fos.close();

        return length;
    }
}

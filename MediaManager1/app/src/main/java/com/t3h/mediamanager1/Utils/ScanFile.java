package com.t3h.mediamanager1.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class ScanFile {

    private Context context;

    public ScanFile(Context context) {
        this.context = context;
    }

    public void scanfile(){
        String path = Environment.getExternalStorageDirectory().toString();

        File file = new File(path);

        Intent intent =
                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

    public void scanfile(File file){

        Intent intent =
                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

}

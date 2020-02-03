package com.t3h.mediamanager1.fileStorage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.t3h.mediamanager1.Utils.ScanFile;
import com.t3h.mediamanager1.models.Image;
import com.t3h.mediamanager1.models.Video;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;



public class FileStorage {

    private String pathImageStorage;
    private String pathVideoStorage;
    private Context context;

    public FileStorage(Context context) {
        createImageStore(context);
        this.context = context;
    }

//tạo thư mục ImageStore để lưu ảnh trong bộ nhớ trong
    private void createImageStore(Context context){
        File file1 = new File(context.getFilesDir(),"ImageStore");
        File file2 = new File(context.getFilesDir(),"VideoStore");
        if (file1.exists() == false){
            file1.mkdir();
        }
        if (file2.exists() == false){
            file2.mkdir();
        }
        pathImageStorage = file1.getPath();
        pathVideoStorage = file2.getPath();
    }


// =================================================================================================
//==================================================================================================

    public static boolean copyFile(String to, String from) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(from);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(from);
                FileOutputStream fs = new FileOutputStream(to);
                byte[] buffer = new byte[2048];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

// ================================================= Image =========================================

    public File createImage(){
        String pathFile = pathImageStorage+"/Image_"+System.currentTimeMillis()+".jpg";
        File file = new File(pathFile);
        return  file;
    }

//lấy danh sách ảnh trong bộ nhớ trong
    public ArrayList<Image> getImage(){
        String path = pathImageStorage;
        ArrayList<Image> arrImg = new ArrayList<>();

        File file = new File(path);
        File listFile[] = file.listFiles();

        for (File fileChil : listFile) {
            Image image = new Image();
            image.setTitle(file.getName());
            image.setChecked(false);
            image.setDisplay(View.INVISIBLE);
            image.setData(fileChil.getPath());

            arrImg.add(image);
        }
        return arrImg;

    }

    public boolean moveImgToInternal(File file){
        File newImg = new File(pathImageStorage,"Image"+file.getName());
        copyFile(newImg.getPath(),file.getPath());
        if(!file.exists()){
            return true;
        }else {
            return false;
        }
    }

    public void moveImgtoExternal(File file){
        File rootFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file1 = new File(rootFile+"/"+file.getName());
        copyFile(file1.getPath(),file.getPath());

        ScanFile scanFile = new ScanFile(context);
        scanFile.scanfile(file1);

        file.delete();
    }

    public void shareImage(ArrayList<Uri> arrUriImg){

        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        // set the type to 'email'
        shareIntent.setType("vnd.android.cursor.dir/email");
        shareIntent.setType("text/plain");
        // the attachment

        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,arrUriImg);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // the mail subject
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        context.startActivity(Intent.createChooser(shareIntent , "Send by ..."));
    }


// ========================================== Video ================================================

    public File createVideo(){
        String path = pathVideoStorage+"/Video_"+System.currentTimeMillis();
        File file = new File(path);
        return file;
    }

    //lấy danh sách video trong một folder
    public ArrayList<Video> getVideo(){
        ArrayList<Video> arrVideo = new ArrayList<>();

        File file = new File(pathVideoStorage);
        File listFile[] = file.listFiles();

        for (File file1: listFile){
            Video video = new Video();
            video.setTitle(file1.getName());
            video.setChecked(false);
            video.setDisplay(View.INVISIBLE);
            video.setData(file1.getPath());

            arrVideo.add(video);
        }
        return arrVideo;
    }

    public boolean moveVideoToInternal(File file){
        File newImg = new File(pathVideoStorage,"Video"+file.getName());
        copyFile(newImg.getPath(), file.getPath());
        if(!file.exists()){
            return true;
        }else {
            return false;
        }
    }

    public void moveVideotoExternal(File file){
        File rootFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file1 = new File(rootFile+"/"+file.getName());
        copyFile(file1.getPath(),file.getPath());

        ScanFile scanFile = new ScanFile(context);
        scanFile.scanfile(file1);

        file.delete();
    }

    public void shareVideo(ArrayList<Uri> arrUriVideo){

        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        // set the type to 'email'
        shareIntent.setType("vnd.android.cursor.dir/email");
        shareIntent.setType("text/plain");
        // the attachment

        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,arrUriVideo);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // the mail subject
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        context.startActivity(Intent.createChooser(shareIntent , "Send by ..."));
    }
}


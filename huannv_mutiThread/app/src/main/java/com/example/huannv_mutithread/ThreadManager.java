package com.example.huannv_mutithread;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {

    List<BaseWorker> listWorker = new ArrayList<>();

    public void executor() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        setDataListWorker();
        for (int i = 0; i < listWorker.size(); i++) {
            executorService.execute(listWorker.get(i));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        Log.e(" ========= ", "finish all threads");
    }

    private void setDataListWorker() {
        BaseWorker doActionTask = new DoActionTask(2, 1000);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(3, 1500);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(1, 500);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(4, 1000);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(5, 1300);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(6, 1200);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(8, 800);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(5, 700);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(2, 1800);
        listWorker.add(doActionTask);
        doActionTask = new DoActionTask(1, 500);
        listWorker.add(doActionTask);

        BaseWorker getDataTask = new GetDataTask(2, 1000);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(3, 1500);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(1, 500);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(4, 1000);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(5, 1300);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(6, 1200);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(8, 800);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(5, 700);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(2, 1800);
        listWorker.add(getDataTask);
        getDataTask = new GetDataTask(1, 500);
        listWorker.add(getDataTask);

        BaseWorker insertDataLocalTask = new InsertDataLocalTask(2, 1000);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(3, 1500);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(1, 500);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(4, 1000);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(5, 1300);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(6, 1200);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(8, 800);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(5, 700);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(2, 1800);
        listWorker.add(insertDataLocalTask);
        insertDataLocalTask = new InsertDataLocalTask(1, 500);
        listWorker.add(insertDataLocalTask);

        BaseWorker sendRequestTask = new SendRequestTask(2, 1000);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(3, 1500);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(1, 500);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(4, 1000);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(5, 1300);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(6, 1200);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(8, 800);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(5, 700);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(2, 1800);
        listWorker.add(sendRequestTask);
        sendRequestTask = new SendRequestTask(1, 500);
        listWorker.add(sendRequestTask);

        BaseWorker uploadTask = new UploadTask(2, 1000);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(3, 1500);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(1, 500);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(4, 1000);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(5, 1300);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(6, 1200);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(8, 800);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(5, 700);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(2, 1800);
        listWorker.add(uploadTask);
        uploadTask = new UploadTask(1, 500);
        listWorker.add(insertDataLocalTask);

        Collections.sort(listWorker, new Comparator<BaseWorker>() {
            @Override
            public int compare(BaseWorker baseWorker1, BaseWorker baseWorker2) {
                return String.valueOf(baseWorker1.getPriority()).compareTo(String.valueOf(baseWorker2.getPriority()));
            }
        });
    }
}

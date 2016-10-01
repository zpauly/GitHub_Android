package com.zpauly.githubapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.presenter.repos.DownloadContract;
import com.zpauly.githubapp.presenter.repos.DownloadPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import rx.Subscriber;

/**
 * Created by zpauly on 16/9/27.
 */

public class DownloadSevice extends Service implements DownloadContract.View {
    private final String TAG = getClass().getName();

    private DownloadContract.Presenter mPresenter;

    public static final String FULL_NAME = "FULL_NAME";
    public static final String ARCHIVE_FORMAT = "ARCHIVE_FORMAT";
    public static final String REF = "REF";
    public static final String OWNER = "OWNER";

    private String owner;
    private String archive_format;
    private String ref;
    private String fullName;
    private String repo;

    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;

    @Override
    public void onCreate() {
        super.onCreate();

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        new DownloadPresenter(this, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        archive_format = intent.getStringExtra(ARCHIVE_FORMAT);
        ref = intent.getStringExtra(REF);
        fullName = intent.getStringExtra(FULL_NAME);
        owner = intent.getStringExtra(OWNER);
        String[] strs = fullName.split("/");
        repo = strs[strs.length - 1];

        showNotification();

        mPresenter.downloadRepo();
        mBuilder.setProgress(0, 0, true);
        mNotificationManager.notify(R.string.remote_service_started, mBuilder.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mNotificationManager.cancel(R.string.remote_service_started);
        mPresenter.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification() {
        mBuilder = new Notification.Builder(this)
                .setTicker(getText(R.string.remote_service_started))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(fullName)
                .setContentText(getText(R.string.downloading));
    }

    @Override
    public void downloadRepoSuccess() {
        mBuilder.setProgress(0, 0, false)
                .setContentText(getText(R.string.download_complete));
        mNotificationManager.notify(R.string.remote_service_started, mBuilder.build());
    }

    @Override
    public void downloadRepoFail() {
        mBuilder.setProgress(0, 0, false)
                .setContentText(getText(R.string.download_fail));
        mNotificationManager.notify(R.string.remote_service_started, mBuilder.build());
    }

    @Override
    public void downloading(File file) {

    }

    @Override
    public void flatMap(retrofit2.Response<ResponseBody> responseBodyResponse, Subscriber<? super File> subscriber) {
        try {
            File file = writeFileFromResponseToDisk(responseBodyResponse.body().contentLength(),
                    responseBodyResponse.headers().get("Content-Disposition").replace("attachment; filename=", ""),
                    responseBodyResponse.body());
            subscriber.onNext(file);
            subscriber.onCompleted();
        } catch (IOException e) {
            subscriber.onError(e);
        }
//        try {
//            // you can access headers of response
//            String header = responseBodyResponse.headers().get("Content-Disposition");
//            // this is specific case, it's up to you how you want to save your file
//            // if you are not downloading file from direct link, you might be lucky to obtain file name from header
//            String fileName = header.replace("attachment; filename=", "");
//            // will create file in global Music directory, can be any other directory, just don't forget to handle permissions
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile(), fileName);
//
//            BufferedSink sink = Okio.buffer(Okio.sink(file));
//            // you can access body of response
//            sink.writeAll(responseBodyResponse.body().source());
//            sink.close();
//            subscriber.onNext(file);
//            subscriber.onCompleted();
//        } catch (IOException e) {
//            e.printStackTrace();
//            subscriber.onError(e);
//        }
    }

    @Override
    public String getRepo() {
        return repo;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getArchiveFormat() {
        return archive_format;
    }

    @Override
    public String getRef() {
        return ref;
    }

    @Override
    public void setPresenter(DownloadContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private File writeFileFromResponseToDisk(final long fileSize,
                                                final String fileName, ResponseBody responseBody) throws IOException {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//                + File.separator + fileName;
        int size = (int) fileSize;
        byte[] fileReader = new byte[size];

        long fileSizeDownload = 0;
        File downloadFile = new File(path, fileName);
        if (!downloadFile.exists())
            downloadFile.createNewFile();
        Log.i(TAG, "file");
        InputStream inputStream = responseBody.byteStream();
        OutputStream outputStream = new FileOutputStream(downloadFile);
        Log.i(TAG, "start to read");
        int read;
        while ((read = inputStream.read(fileReader)) != -1) {
            mBuilder.setProgress(size, (int) fileSizeDownload, false);
            mNotificationManager.notify(R.string.remote_service_started, mBuilder.build());
            outputStream.write(fileReader, 0, read);
            fileSizeDownload += read;
            Log.i(TAG, String.valueOf(fileSizeDownload) + "/" + String.valueOf(size));
        }
        return downloadFile;
    }
}

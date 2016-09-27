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

import okhttp3.ResponseBody;

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
        mNotificationManager.cancel(R.string.remote_service_started);
    }

    @Override
    public void downloading(ResponseBody responseBody) {
        writeFileFromResponseToDisk(this, responseBody.contentLength(),
                fullName + ".zip", responseBody);
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

    private void writeFileFromResponseToDisk(final Context context, final long fileSize,
                                                final String fileName, final ResponseBody responseBody) {
        File path = getExternalFilesDir(null);
//                + File.separator + fileName;
        int size = (int) fileSize;
        byte[] fileReader = new byte[size];

        long fileSizeDownload = 0;
        File downloadFile = new File(path, fileName);
        try {
//            if (!downloadFile.exists()) {
//                downloadFile.createNewFile();
//            }
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
                Log.i(TAG, String.valueOf(fileSizeDownload));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.zpauly.githubapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.util.LruCache;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.ui.URLDrawable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by root on 16-7-31.
 */

public class HtmlImageGetter implements Html.ImageGetter {
    private final String TAG = getClass().getName();

    private Context context;
    private TextView container;
    private String baseUrl;

    private File fileDir;
    private int width;
    private int height;

    public static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private static final LruCache<String, File> cache = new LruCache<String, File>(maxMemory / 8) {
        @Override
        protected int sizeOf(String key, File value) {
            return (int) (value.length() / 1024);
        }
    };

    public HtmlImageGetter(TextView t, Context c, String baseUrl) {
        this.context = c;
        this.container = t;
        this.baseUrl = baseUrl;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Point size;
        if (Build.VERSION.SDK_INT < 13) {
            Display display = wm.getDefaultDisplay();
            size = new Point(display.getWidth(), display.getHeight());
        } else {
            Point p = new Point();
            wm.getDefaultDisplay().getSize(p);
            size = p;
        }
        fileDir = context.getCacheDir();
        width = size.x;
        height = size.y;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();

        ImageGetterAsyncTask asyncTask =
                new ImageGetterAsyncTask(urlDrawable);

        asyncTask.execute(source);

        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            Drawable drawable = fetchDrawable(source);
            return drawable;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                // set the correct bound according to the result from HTTP call
                urlDrawable.setBounds(0, 0, result.getIntrinsicWidth(), result.getIntrinsicHeight());

                // change the reference of the current drawable to the result
                // from the HTTP call
                urlDrawable.drawable = result;

                // redraw the image by invalidating the container
                HtmlImageGetter.this.container.invalidate();

                HtmlImageGetter.this.container.setHeight((HtmlImageGetter.this.container.getMeasuredHeight()
                        + result.getIntrinsicHeight()));

                HtmlImageGetter.this.container.setEllipsize(null);
            } else {
                Log.i(getClass().getName(), "result == null in post");
            }
        }

        public Drawable fetchDrawable(String urlString) {
            String url;
            if (urlString.startsWith("/")) {
                url = baseUrl + urlString;
            } else if (urlString.startsWith("./") || urlString.startsWith("*/")) {
                url = baseUrl + urlString.substring(1);
            } else if (!urlString.startsWith("http")) {
                url = baseUrl + "/" + urlString;
            } else {
                url = urlString;
            }
            File imageFile;
            try {
                URL aURL = new URL(url);
                final URLConnection conn = aURL.openConnection();
                Log.i(TAG, url);
                if ((imageFile = cache.get(url)) == null || cache.get(url).length() == 0) {
                    conn.connect();
                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                    imageFile = File.createTempFile("image", ".tmp", fileDir);
                    FileUtil.save(imageFile, bis);
                    cache.put(url, imageFile);
                    if (cache.get(url) == null) {
                        Log.i(TAG, "cache failed");
                    }
                }
                if (conn.getContentType().startsWith("image/svg")) {
                    Bitmap bitmap = ImageUtil.renderSvgToBitmap(context.getResources(),
                            new FileInputStream(imageFile), width, height);
                    if (bitmap == null) {
                        return returnErrorDrawable();
                    }
                    Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    return drawable;
                }
                if (conn.getContentType().startsWith("image/gif")) {
                    GifDrawable gifDrawable = new GifDrawable(imageFile);
                    if (gifDrawable == null) {
                        return returnErrorDrawable();
                    }
                    gifDrawable.setBounds(0, 0, gifDrawable.getIntrinsicWidth(), gifDrawable.getIntrinsicHeight());
                    return gifDrawable;
                } else {
                    Bitmap bm = ImageUtil.getBitmap(imageFile, width, height);
                    if (bm == null) {
                        return returnErrorDrawable();
                    }
                    Drawable drawable = new BitmapDrawable(context.getResources(), bm);
                    drawable.setBounds(0, 0, bm.getWidth(), bm.getHeight());
                    return drawable;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return returnErrorDrawable();
            }
        }

        private Drawable returnErrorDrawable() {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_image_error);
            BitmapDrawable bd = new BitmapDrawable(bitmap);
            bd.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            return bd;
        }
    }
}

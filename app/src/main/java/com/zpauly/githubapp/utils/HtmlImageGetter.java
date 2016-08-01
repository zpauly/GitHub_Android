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
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.zpauly.githubapp.ui.URLDrawable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

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

    /***
     * Construct the URLImageParser which will execute AsyncTask and refresh the container
     * @param t
     * @param c
     */
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
        URLDrawable urlDrawable = new URLDrawable();

        // get the actual source
        ImageGetterAsyncTask asyncTask =
                new ImageGetterAsyncTask(urlDrawable);

        asyncTask.execute(source);

        // return reference to URLDrawable where I will change with actual image from
        // the src tag
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
                Log.i(getClass().getName(), "width = " + result.getIntrinsicWidth());
                // set the correct bound according to the result from HTTP call
                urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth(), 0
                        + result.getIntrinsicHeight());

                // change the reference of the current drawable to the result
                // from the HTTP call
                urlDrawable.drawable = result;

                // redraw the image by invalidating the container
                HtmlImageGetter.this.container.invalidate();

                HtmlImageGetter.this.container.setHeight((HtmlImageGetter.this.container.getHeight()
                        + result.getIntrinsicHeight()));

                HtmlImageGetter.this.container.setEllipsize(null);
            } else {
                Log.i(getClass().getName(), "result == null in post");
            }
        }

        /***
         * Get the Drawable from URL
         * @param urlString
         * @return
         */
        public Drawable fetchDrawable(String urlString) {
            String url;
            if (urlString.startsWith("/")) {
                url = baseUrl + urlString;
            } else {
                url = urlString;
            }
            try {
                Log.i(getClass().getName(), url);
                URL aURL = new URL(url);
                final URLConnection conn = aURL.openConnection();
                conn.connect();
                final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                Bitmap bm = ImageUtil.getBitmap(bis, width, height);
                if (bm == null) {
                    Log.i(TAG, "bm = null");
                }
                /*Bitmap bitmap = BitmapFactory.decodeStream(bis);
                if (bitmap == null) {
                    Log.i(TAG, "bitmap = null");
                }*/
                Drawable drawable = new BitmapDrawable(bm);
                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0
                        + drawable.getIntrinsicHeight());
                return drawable;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

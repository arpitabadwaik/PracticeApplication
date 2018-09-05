package com.example.user.practiceapp.utility;

import android.Manifest;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by Bynry on 26-12-2016.
 */

public class App extends MultiDexApplication
{
    public static final String TAG = App.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static App mInstance;
    public ArrayList<String> permissions;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
        permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.RECEIVE_SMS);
        permissions.add(Manifest.permission.READ_SMS);

//        initPicasoSetup();
    }

    public static synchronized App getInstance()
    {
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setShouldCache(false);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag)
    {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

   /* private void initPicasoSetup()
    {
        //Setup Picasso
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
//        built.setLoggingEnabled(true);
        builder.defaultBitmapConfig(Bitmap.Config.RGB_565);
        Picasso.setSingletonInstance(built);
    }*/

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

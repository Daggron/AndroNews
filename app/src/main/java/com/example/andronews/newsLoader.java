package com.example.andronews;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class newsLoader extends AsyncTaskLoader<List<news>> {

    private static final String LOG_TAG = newsLoader.class.getName();

    private String mUrl;

    newsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<news> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<news> result = queryUtils.fetchNewsData(mUrl);
        return result;
    }


}
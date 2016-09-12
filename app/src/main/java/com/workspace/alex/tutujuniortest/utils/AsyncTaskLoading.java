package com.workspace.alex.tutujuniortest.utils;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

/**
 * Отдельный поток для загрузки данных
 * Created by Alex on 13.09.2016.
 */
public class AsyncTaskLoading extends AsyncTask<Context, Void, Void> {


    @Override
    protected Void doInBackground(Context... contexts) {
        for (Context c: contexts)
        {
            try {
                JSONDataLoad.initDatas(c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

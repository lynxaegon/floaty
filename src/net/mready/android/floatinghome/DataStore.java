package net.mready.android.floatinghome;

import android.content.Intent;

/**
 * Created by Salut on 8/21/13.
 */
public class DataStore {
    public Intent appIntent;

    public static DataStore _instance = null;

    public static void init()
    {
        _instance = new DataStore();
    }

    public static DataStore getInstance()
    {
        return _instance;
    }
}

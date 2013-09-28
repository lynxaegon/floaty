package net.mready.android.floatinghome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataStore.init();
        DataStore.getInstance().appIntent = new Intent(getBaseContext(),AppStarter.class);
        startService(DataStore.getInstance().appIntent );
        finish();
    }
}

package net.mready.android.floatinghome;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Salut on 8/21/13.
 */
public class AppStarter extends Service {

    FloatingView mView;
    boolean touchconsumedbyMove = false;
    int recButtonLastX;
    int recButtonLastY;
    int recButtonFirstX;
    int recButtonFirstY;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView = new FloatingView(this);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                100, 100,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        final WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mView, params);

        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int totalDeltaX = recButtonLastX - recButtonFirstX;
                int totalDeltaY = recButtonLastY - recButtonFirstY;
                switch(event.getActionMasked())
                {
                    case MotionEvent.ACTION_DOWN:
                        recButtonLastX = (int) event.getRawX();
                        recButtonLastY = (int) event.getRawY();
                        recButtonFirstX = recButtonLastX;
                        recButtonFirstY = recButtonLastY;
                        break;
                    case MotionEvent.ACTION_UP:
                      break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = (int) event.getRawX() - recButtonLastX;
                        int deltaY = (int) event.getRawY() - recButtonLastY;
                        recButtonLastX = (int) event.getRawX();
                        recButtonLastY = (int) event.getRawY();
                        if (Math.abs(totalDeltaX) >= 5  || Math.abs(totalDeltaY) >= 5) {
                            if (event.getPointerCount() == 1) {
                                params.x += deltaX;
                                params.y += deltaY;
                                touchconsumedbyMove = true;
                                wm.updateViewLayout(mView, params);
                            }
                            else{
                                touchconsumedbyMove = false;
                            }
                        }else{
                            touchconsumedbyMove = false;
                        }
                        break;
                    default:
                        break;
                }
                return touchconsumedbyMove;
            }
        });
        stopSelf();
    }
}

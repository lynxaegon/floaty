package net.mready.android.floatinghome;


import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

public class FloatingView extends View {
    private Context context;
    public FloatingView(final Context context) {
        super(context);
        this.context = context;
        setBackgroundColor(getResources().getColor(R.color.red));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked !!",Toast.LENGTH_SHORT).show();
                removeSelf();
            }
        });
    }

    private void removeSelf()
    {
        final WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        wm.removeView(this);
    }

}

package baadraan.u.batman;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SnackMessage {
    private Context context;
    private String message;
    private View view;

    public SnackMessage(Context context, String message, View view) {
        this.context = context;
        this.message = message;
        this.view = view;
    }

    public void showMessage() {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View sView = snackbar.getView();
        sView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            sView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        sView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        sView.ALPHA.set(sView, (float) 0.9);
        TextView tv = (TextView) sView.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setTextAlignment(sView.TEXT_ALIGNMENT_CENTER);
        }
        tv.setTextColor(context.getResources().getColor(R.color.white));
        tv.setTextSize(16);
        snackbar.show();
    }
}
package baadraan.u.batman;

import android.app.Application;
import android.content.Context;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class Font extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("sans.TTF")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());


    }
}

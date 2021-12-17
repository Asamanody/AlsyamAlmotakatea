package com.el3asas.regym.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.el3asas.regym.R;
import com.el3asas.regym.ui.adapter.imagesAdapter;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ViewPager viewPager = findViewById(R.id.imagesScroller);
        imagesAdapter imagesAdapter = new imagesAdapter(StartActivity.this);
        viewPager.setAdapter(imagesAdapter);
        Button skip = findViewById(R.id.exitScroller);
        SharedPreferences pref=getSharedPreferences(getPackageName()+"_checkFirstTime",0);
        final SharedPreferences.Editor editor=pref.edit();

        skip.setOnClickListener(view -> {
            editor.putBoolean("firstTime",true);
            editor.apply();
            finish();
            startActivity(new Intent(StartActivity.this, MainActivity.class));
        });
    }
}

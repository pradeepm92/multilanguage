package com.example.changelanguageapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btn_change;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();
        btn_change=findViewById(R.id.btn_changelanguage);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalllanguageDialog();
            }
        });

    }
    private void doCalllanguageDialog() {
        final String[] languages = {"English", "தமிழ்"};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Choose Language");
        alertDialog.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    SetLocale("en");
                    recreate();
                } else if (i == 1) {
                    SetLocale("ta");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mdialog = alertDialog.create();
        mdialog.show();
    }

    private void SetLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config =new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences sharedPreferences = getSharedPreferences("settings",  Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "settings",lang);
        editor.apply();
        Log.e("language", lang );
//        Utils.setLocale(context,lang);
    }
    public void loadLocale(){
        SharedPreferences sharedPreferences= getSharedPreferences("settings", Context.MODE_PRIVATE);
        String language= sharedPreferences.getString("settings", "");
        SetLocale(language);
//        SetLocale(Utils.getLocale(context));
        SetLocale(language);
//        Log.e("language>>>>>", Utils.getLocale(context));
    }
}
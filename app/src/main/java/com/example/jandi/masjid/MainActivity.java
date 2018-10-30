package com.example.jandi.masjid;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
    }
    public void keMenu(View v){
        Intent intent = new Intent(this, list_user.class);
        startActivity(intent);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_masjid, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil) {
            Intent intent = new Intent(this, detail_user.class);
            intent.putExtra("ID", "1");
            this.startActivity(intent);
        } else if (item.getItemId() == R.id.ganti) {
            showChangeLanguageDialog();
        }
        return true;
    }
    public void showChangeLanguageDialog(){
        final String listItem[] = {"indonesia", "inggris"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("in");
                    recreate();
                }
                else if(i==1){
                    setLocale("fr");
                    recreate();
                }
                dialogInterface.dismiss();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("setting", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        String language =prefs.getString("My_Lang", "");
        setLocale(language);
    }
}

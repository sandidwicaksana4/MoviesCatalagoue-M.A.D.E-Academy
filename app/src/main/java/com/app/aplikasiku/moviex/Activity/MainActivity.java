package com.app.aplikasiku.moviex.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.aplikasiku.moviex.Fragment.AboutFragment;
import com.app.aplikasiku.moviex.Fragment.FavoriteFragment;
import com.app.aplikasiku.moviex.Fragment.HomeFragment;
import com.app.aplikasiku.moviex.Fragment.SettingFragment;
import com.app.aplikasiku.moviex.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private Fragment fragment;
    private String title = "";
    private static final String KEY_TITLE = "title";

    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLocale();
        toolbar = getSupportActionBar();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        title = getString(R.string.title_home);
                        menuItem.setTitle(title);
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_favorite:
                        title = getString(R.string.title_favorite);
                        menuItem.setTitle(title);
                        fragment = new FavoriteFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_about:
                        title = getString(R.string.title_about);
                        menuItem.setTitle(title);
                        fragment = new AboutFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_setting:
                        title = getString(R.string.title_pengaturan);
                        menuItem.setTitle(title);
                        fragment = new SettingFragment();
                        loadFragment(fragment);
                        return true;
                }
                return true;
            }
        });
        if (savedInstanceState == null) {
            loadLocale();
            loadBeranda();
        } else {
            title = savedInstanceState.getString(KEY_TITLE);
            toolbar.setTitle(title);
        }
    }

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        // load fragment
        toolbar.setTitle(title);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void loadBeranda() {
        title = getString(R.string.title_home);
        toolbar.setTitle(title);
        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.pesan_exit);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                finish();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        getApplicationContext().getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());
        //simpan bahasa
        SharedPreferences.Editor editor = getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        super.onSaveInstanceState(outState);
    }

}

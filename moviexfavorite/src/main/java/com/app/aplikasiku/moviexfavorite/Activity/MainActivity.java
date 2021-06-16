package com.app.aplikasiku.moviexfavorite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.app.aplikasiku.moviexfavorite.Fragment.FavoriteMovieFragment;
import com.app.aplikasiku.moviexfavorite.Fragment.FavoriteTvShowFragment;
import com.app.aplikasiku.moviexfavorite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private String title="";
    private static final String KEY_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView favorite = findViewById(R.id.favoritemenu);
        favorite.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_movie:
                        title = getString(R.string.title_film);
                        fragment = new FavoriteMovieFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.item_tv:
                        title = getString(R.string.title_tv);
                        fragment = new FavoriteTvShowFragment();
                        loadFragment(fragment);
                        return true;
                }
                return true;
            }
        });

        if (savedInstanceState == null) {
            loadFilm();
        }else {
            title = savedInstanceState.getString(KEY_TITLE);
        }
    }
    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_favorite, fragment);
        transaction.commit();
    }
    private void loadFilm(){
        title = getString(R.string.title_film);
        fragment = new FavoriteMovieFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_favorite,fragment).commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TITLE,title);
    }
}

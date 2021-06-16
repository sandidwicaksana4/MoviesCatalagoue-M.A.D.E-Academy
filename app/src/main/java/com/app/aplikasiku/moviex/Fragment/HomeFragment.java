package com.app.aplikasiku.moviex.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.aplikasiku.moviex.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Fragment fragment = new ListMovieFragment();
    private String title="";
    private static final String KEY_TITLE = "title";
    private static final String KEY_FRAGMENT = "fragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        BottomNavigationView beranda = view.findViewById(R.id.berandamenu);
        beranda.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_movie:
                        title = getString(R.string.title_film);
                        fragment = new ListMovieFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.item_tv:
                        title = getString(R.string.title_tv);
                        fragment = new ListTvShowFragment();
                        loadFragment(fragment);
                        return true;
                }
                return true;
            }
        });
        if (savedInstanceState == null) {
            loadFilm();
        }else {
            fragment = getChildFragmentManager().getFragment(savedInstanceState,KEY_FRAGMENT);
            title = savedInstanceState.getString(KEY_TITLE);
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_beranda, fragment);
        transaction.commit();
    }

    private void loadFilm() {
        fragment = new ListMovieFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.frame_beranda,fragment).commit();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_TITLE,title);
        getChildFragmentManager().putFragment(outState,KEY_FRAGMENT,fragment);
        super.onSaveInstanceState(outState);
    }
}

package com.app.aplikasiku.moviex.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.aplikasiku.moviex.Adapter.MovieAdapter;
import com.app.aplikasiku.moviex.MainViewModel;
import com.app.aplikasiku.moviex.Model.MovieItem;
import com.app.aplikasiku.moviex.R;

import java.util.ArrayList;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListMovieFragment extends Fragment{

    private MovieAdapter movieAdapter;
    SwipeRefreshLayout swipe;
    RecyclerView movieList;

    public ListMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        movieList = view.findViewById(R.id.rc_film);
        swipe = view.findViewById(R.id.swipe_refresh);

        loadView();
        setMovies();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadView();
                setMovies();
                swipe.setRefreshing(false);
            }
        });
    }

    private void loadView(){
        movieList.setLayoutManager(new LinearLayoutManager(getContext()));
        movieList.setHasFixedSize(true);
        movieAdapter = new MovieAdapter(getContext());
        movieList.setAdapter(movieAdapter);
    }

    private void setMovies(){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.setListMovies();
        viewModel.getMovies().observe(this,getMovies);
    }

    private Observer<ArrayList<MovieItem>> getMovies = new Observer<ArrayList<MovieItem>>(){
        @Override
        public void onChanged(ArrayList<MovieItem> movieItems) {
            if (movieItems != null){
                movieAdapter.refill(movieItems);
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search,menu);
        final MenuItem cari = menu.findItem(R.id.action_search);
        final SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint(getString(R.string.search_movie));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovies(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                setMovies();
                return false;
            }
        });
        cari.setActionView(searchView);
    }

    private void searchMovies(String query){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.searchMovies(query);
        viewModel.getMovies().observe(this,getMoviesResults);
    }

    private Observer<ArrayList<MovieItem>> getMoviesResults = new Observer<ArrayList<MovieItem>>(){
        @Override
        public void onChanged(ArrayList<MovieItem> movieItems) {
        if (movieItems != null && movieItems.size() > 0) {
            movieAdapter.refill(movieItems);
        } else {
            Toast.makeText(getContext(), R.string.empty_search, Toast.LENGTH_LONG).show();
        }
        }
    };

}

package com.app.aplikasiku.moviex.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.aplikasiku.moviex.Adapter.TvShowAdapter;
import com.app.aplikasiku.moviex.MainViewModel;
import com.app.aplikasiku.moviex.Model.TvShowItem;
import com.app.aplikasiku.moviex.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
public class ListTvShowFragment extends Fragment {

    private TvShowAdapter tvAdapter;
    SwipeRefreshLayout swipe;
    RecyclerView tvList;

    public ListTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        tvList = view.findViewById(R.id.rc_tv);
        swipe = view.findViewById(R.id.swipe_refresh);

        loadView();
        setTvShow();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadView();
                setTvShow();
                swipe.setRefreshing(false);
            }
        });
    }

    private void loadView(){
        tvList.setHasFixedSize(true);
        tvList.setLayoutManager(new LinearLayoutManager(getContext()));
        tvAdapter = new TvShowAdapter(getContext());
        tvList.setAdapter(tvAdapter);
    }

    private void setTvShow(){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.setListTvShow();
        viewModel.getTvShow().observe(this,getTvShows);
    }

    private Observer<ArrayList<TvShowItem>> getTvShows = new Observer<ArrayList<TvShowItem>>(){
        @Override
        public void onChanged(ArrayList<TvShowItem> tvItems) {
            if (tvItems != null){
                tvAdapter.refill(tvItems);
            }
        }
    };

    private void loadFailed() {
        Toast.makeText(getContext(), R.string.error_load_data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search,menu);
        final MenuItem cari = menu.findItem(R.id.action_search);
        final SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint(getString(R.string.search_tvshow));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTvShow(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setTvShow();
                return false;
            }
        });
        cari.setActionView(searchView);
    }

    private void searchTvShow(String query) {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.searchTvShow(query);
        viewModel.getTvShow().observe(this,getTvShowResults);
    }

    private Observer<ArrayList<TvShowItem>> getTvShowResults = new Observer<ArrayList<TvShowItem>>(){
        @Override
        public void onChanged(ArrayList<TvShowItem> tvItems) {
        if (tvItems != null && tvItems.size() > 0) {
            tvAdapter.refill(tvItems);
        } else {
            Toast.makeText(getContext(), R.string.empty_search, Toast.LENGTH_LONG).show();
        }
        }
    };

}

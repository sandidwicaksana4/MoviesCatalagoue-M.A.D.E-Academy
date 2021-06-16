package com.app.aplikasiku.moviex.Fragment;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.aplikasiku.moviex.Adapter.RcFavoriteTvshow;
import com.app.aplikasiku.moviex.Favorite.DatabaseHelper;
import com.app.aplikasiku.moviex.Model.DataFavorit;
import com.app.aplikasiku.moviex.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class FavoriteTvShowFragment extends Fragment {

    //data list favorite tvshow
    private ArrayList<DataFavorit> data_films = new ArrayList<>();
    RcFavoriteTvshow rcFavoritTvshow;
    RecyclerView listtvshow;
    ProgressDialog pd;
    RecyclerView.LayoutManager manager;
    SwipeRefreshLayout swipe;

    DatabaseHelper myFavorit = new DatabaseHelper(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_tv_show,container,false);

        myFavorit = new DatabaseHelper(getContext());

        listtvshow = view.findViewById(R.id.rc_tv);
        swipe = view.findViewById(R.id.swipe_refresh);
        //tampil data list favorite tvshow
                loadView();
                loadTvshow();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadView();
                loadTvshow();
            }
        });

        return view;
    }
    //load daftar favorite tv
    private void loadView() {
        pd = new ProgressDialog(getContext());
        manager = new LinearLayoutManager(getContext());
        listtvshow.setLayoutManager(manager);
        listtvshow.setHasFixedSize(true);
        rcFavoritTvshow = new RcFavoriteTvshow(getContext(), data_films);
        listtvshow.setAdapter(rcFavoritTvshow);
    }

    private void loadTvshow(){
        SQLiteDatabase db = myFavorit.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT *FROM tbfavorit WHERE kategori =? ORDER BY title asc",new String[]{"Tv Show"},null);
        data_films.clear();
        res.moveToFirst();
        for (int cc = 0; cc < res.getCount(); cc++) {
            res.moveToPosition(cc);
            DataFavorit dataFavorit = new DataFavorit();
            dataFavorit.setId(res.getInt(0));
            dataFavorit.setPoster(res.getString(1));
            dataFavorit.setBackground(res.getString(2));
            dataFavorit.setTitle(res.getString(3));
            dataFavorit.setPopular(res.getString(4));
            dataFavorit.setGenres(res.getString(5));
            dataFavorit.setRelease_date(res.getString(6));
            dataFavorit.setRuntime(res.getString(7));
            dataFavorit.setProduction_companies(res.getString(8));
            dataFavorit.setLanguage(res.getString(9));
            dataFavorit.setDecription(res.getString(10));
            dataFavorit.setStatus(res.getString(11));
            dataFavorit.setBudget(res.getString(12));
            dataFavorit.setRevenue(res.getString(13));
            dataFavorit.setEpisode(res.getString(14));
            dataFavorit.setSeason(res.getString(15));
            dataFavorit.setKategori(res.getString(16));
            data_films.add(dataFavorit);
        }while (res.moveToNext());
        res.close();
        rcFavoritTvshow.notifyDataSetChanged();
        swipe.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (data_films!=null){
            loadView();
            loadTvshow();
        }
    }
}

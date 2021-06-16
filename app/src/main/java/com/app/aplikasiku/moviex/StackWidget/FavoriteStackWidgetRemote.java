package com.app.aplikasiku.moviex.StackWidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.app.aplikasiku.moviex.Favorite.DatabaseHelper;
import com.app.aplikasiku.moviex.Model.DataFavorit;
import com.app.aplikasiku.moviex.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class FavoriteStackWidgetRemote implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<DataFavorit> data_films = new ArrayList<>();
    private Context mcontext;
    private int appWidgetId;
    private Cursor list;

    public FavoriteStackWidgetRemote(Context context, Intent intent) {
        mcontext = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        if (list != null) {
            list.close();
        }
            final DatabaseHelper myFavorit;
            myFavorit = new DatabaseHelper(mcontext);
            SQLiteDatabase db = myFavorit.getReadableDatabase();
            list = db.rawQuery("SELECT *FROM tbfavorit", null);
            data_films.clear();
            list.moveToFirst();
            for (int cc = 0; cc < list.getCount(); cc++) {
                list.moveToPosition(cc);
                DataFavorit dataFavorit = new DataFavorit();
                dataFavorit.setId(list.getInt(0));
                dataFavorit.setPoster(list.getString(1));
                dataFavorit.setBackground(list.getString(2));
                dataFavorit.setTitle(list.getString(3));
                dataFavorit.setPopular(list.getString(4));
                dataFavorit.setGenres(list.getString(5));
                dataFavorit.setRelease_date(list.getString(6));
                dataFavorit.setRuntime(list.getString(7));
                dataFavorit.setProduction_companies(list.getString(8));
                dataFavorit.setLanguage(list.getString(9));
                dataFavorit.setDecription(list.getString(10));
                dataFavorit.setStatus(list.getString(11));
                dataFavorit.setBudget(list.getString(12));
                dataFavorit.setRevenue(list.getString(13));
                dataFavorit.setEpisode(list.getString(14));
                dataFavorit.setSeason(list.getString(15));
                dataFavorit.setKategori(list.getString(16));
                data_films.add(dataFavorit);
            }while (list.moveToNext());
            list.close();
    }


    @Override
    public void onDestroy() {
        if (list != null) {
            list.close();
        }
    }

    @Override
    public int getCount() {
        return data_films.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mcontext.getPackageName(), R.layout.list_item_widget);
        try {
            Bitmap bitmap = Glide.with(mcontext)
                    .asBitmap()
                    .load(data_films.get(position).getPoster())
                    .apply(new RequestOptions().fitCenter())
                    .submit(800, 650).get();

            view.setImageViewBitmap(R.id.image_poster,bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setTextViewText(R.id.txt_title,data_films.get(position).getTitle());

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        extras.putString(FavoriteWidget.TOAST_ACTION,data_films.get(position).getTitle());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        view.setOnClickFillInIntent(R.id.image_poster, fillInIntent);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

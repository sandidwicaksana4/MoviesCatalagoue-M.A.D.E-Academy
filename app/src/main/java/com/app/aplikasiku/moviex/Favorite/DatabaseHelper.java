package com.app.aplikasiku.moviex.Favorite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "moviex.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbfavorit(id integer primary key," +
                "poster text not null," +
                "background text not null," +
                "title text not null," +
                "popular text not null," +
                "genres text not null," +
                "release_date text not null," +
                "runtime text not null," +
                "production_companies text not null," +
                "language text not null," +
                "decription text not null," +
                "status text not null," +
                "budget text ," +
                "revenue text ," +
                "number_episode text ," +
                "number_season text ," +
                "kategori text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteContract.TABLE_NAME);
        onCreate(db);
    }
}

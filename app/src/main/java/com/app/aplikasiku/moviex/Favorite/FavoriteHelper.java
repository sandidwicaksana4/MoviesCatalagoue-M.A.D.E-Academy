package com.app.aplikasiku.moviex.Favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FavoriteHelper {

    private static String DATABASE_TABLE = FavoriteContract.TABLE_NAME;
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase db;

    public FavoriteHelper(Context context) { this.context = context; }

    public FavoriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();
        return this;
    }

    //get movie
    public Cursor queryProviderMovie(String kat) {
        return db.query(DATABASE_TABLE,
                null,
                FavoriteContract.FavoriteColumns.KATEGORI + "= ?",
                new String[]{kat},
                null,null, FavoriteContract.FavoriteColumns.TITLE + " ASC ");
    }

    //get detail favorite
    public Cursor getDetailData(String id) {
        return db.query(
                DATABASE_TABLE,
                null,
                FavoriteContract.FavoriteColumns.ID + " = ?",
                new String[]{id},
                null,
                null,
                null
        );
    }

    //save favorite
    public long insertData(ContentValues values) {
        return db.insert(
                DATABASE_TABLE,
                null,
                values
        );
    }

    //update favorite
    public int updateData(String id, ContentValues values) {
        return db.update(
                DATABASE_TABLE,
                values,
                "id = ?",
                new String[]{id}
        );
    }

    //remove favorite
    public int deleteData(String id){
        return db.delete(
                DATABASE_TABLE,
                "id = ?",
                new String[]{id}
        );
    }
}

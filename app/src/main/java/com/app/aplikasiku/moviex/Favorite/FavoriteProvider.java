package com.app.aplikasiku.moviex.Favorite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FavoriteProvider extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private FavoriteHelper favoriteHelper;

    static {
        uriMatcher.addURI(FavoriteContract.AUTHORITY, FavoriteContract.TABLE_NAME , MOVIE);
        uriMatcher.addURI(FavoriteContract.AUTHORITY, FavoriteContract.TABLE_NAME + "/#", MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        favoriteHelper = new FavoriteHelper(getContext());
        favoriteHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case MOVIE :
                cursor = favoriteHelper.queryProviderMovie(s);
                break;
            case MOVIE_ID :
                cursor = favoriteHelper.getDetailData(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
            if (cursor != null) {
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
            }
            return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long added;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                added = favoriteHelper.insertData(contentValues);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(FavoriteContract.CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int deleted;
        switch (uriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted = favoriteHelper.deleteData(uri.getLastPathSegment());
                break;

            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int updated;
        switch (uriMatcher.match(uri)) {
            case MOVIE_ID:
                updated = favoriteHelper.updateData(uri.getLastPathSegment(), contentValues);
                break;

            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }
}

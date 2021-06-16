package com.app.aplikasiku.moviex.Favorite;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteContract {
    public static final String AUTHORITY = "com.app.aplikasiku.moviex";
    public static final String TABLE_NAME = "tbfavorit";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static final class FavoriteColumns implements BaseColumns {
        public static String ID = "id";
        public static String POSTER = "poster";
        public static String BACKGROUND = "background";
        public static String TITLE = "title";
        public static String POPULAR = "popular";
        public static String DESK = "decription";
        public static String TGL = "release_date";
        public static String GENRE = "genres";
        public static String RUNTIME = "runtime";
        public static String BAHASA = "language";
        public static String COMPANIES = "production_companies";
        public static String STATUS = "status";
        public static String BUDGET = "budget";
        public static String REVENUE = "revenue";
        public static String EPISODE = "number_episode";
        public static String SEASON = "number_season";
        public static String KATEGORI = "kategori";
    }
}

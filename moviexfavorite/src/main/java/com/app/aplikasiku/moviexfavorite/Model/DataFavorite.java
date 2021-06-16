package com.app.aplikasiku.moviexfavorite.Model;

import android.database.Cursor;

import com.app.aplikasiku.moviexfavorite.FavoriteContract;

public class DataFavorite {

    Integer id;
    String poster;
    String background;
    String title;
    String popular;
    String genres;
    String release_date;
    String runtime;
    String production_companies;
    String language;
    String decription;
    String status;
    String budget;
    String revenue;
    String episode;
    String season;
    String kategori;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(String production_companies) {
        this.production_companies = production_companies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public DataFavorite(Integer id, String poster, String background, String title, String popular, String genres, String release_date, String runtime, String production_companies, String language, String decription, String status, String budget, String revenue, String episode, String season, String kategori) {
        this.id = id;
        this.poster = poster;
        this.background = background;
        this.title = title;
        this.popular = popular;
        this.genres = genres;
        this.release_date = release_date;
        this.runtime = runtime;
        this.production_companies = production_companies;
        this.language = language;
        this.decription = decription;
        this.status = status;
        this.budget = budget;
        this.revenue = revenue;
        this.episode = episode;
        this.season = season;
        this.kategori = kategori;
    }

    public DataFavorite() {
    }

    public DataFavorite(Cursor mCursor){
        this.id = FavoriteContract.getColumnInt(mCursor, FavoriteContract.FavoriteColumns.ID);
        this.poster = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.POSTER);
        this.background = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.BACKGROUND);
        this.title = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.TITLE);
        this.popular = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.POPULAR);
        this.genres = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.GENRE);
        this.release_date = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.TGL);
        this.runtime = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.RUNTIME);
        this.production_companies = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.COMPANIES);
        this.language = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.BAHASA);
        this.decription = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.DESK);
        this.status = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.STATUS);
        this.budget = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.BUDGET);
        this.revenue = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.REVENUE);
        this.episode = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.EPISODE);
        this.season = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.SEASON);
        this.kategori = FavoriteContract.getColumnString(mCursor, FavoriteContract.FavoriteColumns.KATEGORI);
    }

}

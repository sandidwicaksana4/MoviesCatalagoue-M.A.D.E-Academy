package com.app.aplikasiku.moviex.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataFavorit implements Parcelable {
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

    public DataFavorit() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.poster);
        dest.writeString(this.background);
        dest.writeString(this.title);
        dest.writeString(this.popular);
        dest.writeString(this.genres);
        dest.writeString(this.release_date);
        dest.writeString(this.runtime);
        dest.writeString(this.production_companies);
        dest.writeString(this.language);
        dest.writeString(this.decription);
        dest.writeString(this.status);
        dest.writeString(this.budget);
        dest.writeString(this.revenue);
        dest.writeString(this.episode);
        dest.writeString(this.season);
        dest.writeString(this.kategori);
    }

    protected DataFavorit(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.poster = in.readString();
        this.background = in.readString();
        this.title = in.readString();
        this.popular = in.readString();
        this.genres = in.readString();
        this.release_date = in.readString();
        this.runtime = in.readString();
        this.production_companies = in.readString();
        this.language = in.readString();
        this.decription = in.readString();
        this.status = in.readString();
        this.budget = in.readString();
        this.revenue = in.readString();
        this.episode = in.readString();
        this.season = in.readString();
        this.kategori = in.readString();
    }

    public static final Creator<DataFavorit> CREATOR = new Creator<DataFavorit>() {
        @Override
        public DataFavorit createFromParcel(Parcel source) {
            return new DataFavorit(source);
        }

        @Override
        public DataFavorit[] newArray(int size) {
            return new DataFavorit[size];
        }
    };
}

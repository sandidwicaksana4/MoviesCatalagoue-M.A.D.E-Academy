package com.app.aplikasiku.moviex.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DetailTvShowItem implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("first_air_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("status")
    private String status;

    @SerializedName("genres")
    private List<GenresItem> genre;

    @SerializedName("episode_run_time")
    private List<Integer> runtime;

    @SerializedName("production_companies")
    private List<ProductionCompaniesItem> productionCompanies;

    @SerializedName("original_language")
    private String bahasa;

    @SerializedName("number_of_episodes")
    private Integer numberepisode ;

    @SerializedName("number_of_seasons")
    private Integer numberseason;

    @SerializedName("overview")
    private String overview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public List<GenresItem> getGenre() {
        return genre;
    }

    public List<Integer> getRuntime() {
        return runtime;
    }

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public String getBahasa() {
        return bahasa;
    }

    public Integer getNumberepisode() {
        return numberepisode;
    }

    public Integer getNumberseason() {
        return numberseason;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.status);
        dest.writeList(this.genre);
        dest.writeList(this.runtime);
        dest.writeList(this.productionCompanies);
        dest.writeString(this.bahasa);
        dest.writeValue(this.numberepisode);
        dest.writeValue(this.numberseason);
        dest.writeString(this.overview);
    }

    public DetailTvShowItem() {
    }

    protected DetailTvShowItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.status = in.readString();
        this.genre = new ArrayList<GenresItem>();
        in.readList(this.genre, GenresItem.class.getClassLoader());
        this.runtime = new ArrayList<Integer>();
        in.readList(this.runtime, Integer.class.getClassLoader());
        this.productionCompanies = new ArrayList<ProductionCompaniesItem>();
        in.readList(this.productionCompanies, ProductionCompaniesItem.class.getClassLoader());
        this.bahasa = in.readString();
        this.numberepisode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numberseason = (Integer) in.readValue(Integer.class.getClassLoader());
        this.overview = in.readString();
    }

    public static final Creator<DetailTvShowItem> CREATOR = new Creator<DetailTvShowItem>() {
        @Override
        public DetailTvShowItem createFromParcel(Parcel source) {
            return new DetailTvShowItem(source);
        }

        @Override
        public DetailTvShowItem[] newArray(int size) {
            return new DetailTvShowItem[size];
        }
    };
}

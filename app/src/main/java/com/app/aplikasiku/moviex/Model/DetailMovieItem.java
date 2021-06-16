package com.app.aplikasiku.moviex.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieItem implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("status")
    private String status;

    @SerializedName("genres")
    private List<GenresItem> genre;

    @SerializedName("runtime")
    private Integer runtime;

    @SerializedName("production_companies")
    private List<ProductionCompaniesItem> productionCompanies;

    @SerializedName("original_language")
    private String bahasa;

    @SerializedName("revenue")
    private double revenue;

    @SerializedName("budget")
    private double budget;

    @SerializedName("overview")
    private String overview;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getBahasa() {
        return bahasa;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getBudget() {
        return budget;
    }

    public DetailMovieItem() {
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
        dest.writeValue(this.runtime);
        dest.writeList(this.productionCompanies);
        dest.writeString(this.bahasa);
        dest.writeDouble(this.revenue);
        dest.writeDouble(this.budget);
        dest.writeString(this.overview);
    }

    protected DetailMovieItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.status = in.readString();
        this.genre = new ArrayList<GenresItem>();
        in.readList(this.genre, GenresItem.class.getClassLoader());
        this.runtime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.productionCompanies = new ArrayList<ProductionCompaniesItem>();
        in.readList(this.productionCompanies, ProductionCompaniesItem.class.getClassLoader());
        this.bahasa = in.readString();
        this.revenue = in.readDouble();
        this.budget = in.readDouble();
        this.overview = in.readString();
    }

    public static final Creator<DetailMovieItem> CREATOR = new Creator<DetailMovieItem>() {
        @Override
        public DetailMovieItem createFromParcel(Parcel source) {
            return new DetailMovieItem(source);
        }

        @Override
        public DetailMovieItem[] newArray(int size) {
            return new DetailMovieItem[size];
        }
    };
}

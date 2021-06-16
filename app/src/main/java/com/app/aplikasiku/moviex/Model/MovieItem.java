package com.app.aplikasiku.moviex.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieItem implements Parcelable {

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

    @SerializedName("genre_ids")
    private List<Integer> genre;

    @SerializedName("original_language")
    private String bahasa;

    @SerializedName("overview")
    private String overview;

    @SerializedName("results")
    private ArrayList<MovieItem> results;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = "https://image.tmdb.org/t/p/w342/" +posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = "https://image.tmdb.org/t/p/w342/" +backdropPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setResults(ArrayList<MovieItem> results) {
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/" +posterPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342/" +backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public String getBahasa() {
        return bahasa;
    }

    public String getOverview() {
        return overview;
    }

    public ArrayList<MovieItem> getResults() {
        return results;
    }

    public MovieItem() {
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
        dest.writeList(this.genre);
        dest.writeString(this.bahasa);
        dest.writeString(this.overview);
        dest.writeTypedList(this.results);
    }

    protected MovieItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.genre = new ArrayList<Integer>();
        in.readList(this.genre, Integer.class.getClassLoader());
        this.bahasa = in.readString();
        this.overview = in.readString();
        this.results = in.createTypedArrayList(MovieItem.CREATOR);
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}
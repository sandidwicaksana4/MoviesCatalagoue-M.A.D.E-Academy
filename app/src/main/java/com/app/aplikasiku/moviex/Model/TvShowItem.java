package com.app.aplikasiku.moviex.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShowItem implements Parcelable {

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

    @SerializedName("original_language")
    private String bahasa;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("genre_ids")
    private List<Integer> genre;

    @SerializedName("overview")
    private String overview;

    @SerializedName("results")
    private ArrayList<TvShowItem> results;

    public String getBahasa() {
        return bahasa;
    }

    public ArrayList<TvShowItem> getResults() {
        return results;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/" + posterPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342/" + backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public TvShowItem() {
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
        dest.writeString(this.bahasa);
        dest.writeDouble(this.voteAverage);
        dest.writeList(this.genre);
        dest.writeString(this.overview);
        dest.writeTypedList(this.results);
    }

    protected TvShowItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.bahasa = in.readString();
        this.voteAverage = in.readDouble();
        this.genre = new ArrayList<Integer>();
        in.readList(this.genre, Integer.class.getClassLoader());
        this.overview = in.readString();
        this.results = in.createTypedArrayList(TvShowItem.CREATOR);
    }

    public static final Creator<TvShowItem> CREATOR = new Creator<TvShowItem>() {
        @Override
        public TvShowItem createFromParcel(Parcel source) {
            return new TvShowItem(source);
        }

        @Override
        public TvShowItem[] newArray(int size) {
            return new TvShowItem[size];
        }
    };
}

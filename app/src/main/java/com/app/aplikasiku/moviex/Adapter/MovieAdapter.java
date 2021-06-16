package com.app.aplikasiku.moviex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aplikasiku.moviex.Activity.DetailMovieActivity;
import com.app.aplikasiku.moviex.Model.MovieItem;
import com.app.aplikasiku.moviex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<MovieItem> list = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void refill(ArrayList<MovieItem> items) {
        this.list = items;
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieHolder movieHolder, int i) {
        movieHolder.onBind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        private TextView judul, tanggal, popular;
        private ImageView gambar, background;

        public MovieHolder(final View item) {
            super(item);

            context = item.getContext();

            gambar = item.findViewById(R.id.image_poster);
            judul = item.findViewById(R.id.title_movie);
            background = item.findViewById(R.id.background);
            tanggal = item.findViewById(R.id.date_release);
            popular = item.findViewById(R.id.popular);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent masuk;
                    masuk = new Intent(context, DetailMovieActivity.class);
                    masuk.putExtra(DetailMovieActivity.EXTRA_MOV,list.get(getAdapterPosition()));
                    context.startActivity(masuk);
                }
            });
        }

        void onBind(MovieItem item) {
            if (item.getPosterPath() != null && !item.getPosterPath().isEmpty()) {
                Picasso.get().load(item.getPosterPath()).transform(new CropCircleTransformation()).into(gambar);
            }

            if (item.getBackdropPath() != null && !item.getBackdropPath().isEmpty()) {
                Picasso.get().load(item.getPosterPath()).transform(new BlurTransformation(itemView.getContext(), 20)).into(background);
            }

            String title = checkTextIfNull(item.getTitle());
            if (title.length() > 30) {
                judul.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                judul.setText(checkTextIfNull(item.getTitle()));
            }

            tanggal.setText(checkTextIfNull(item.getReleaseDate()));
            popular.setText(checkTextIfNull("" + item.getVoteAverage()));
        }

        String checkTextIfNull(String text) {
            if (text != null && !text.isEmpty()) {
                return text;
            } else {
                return "-";
            }
        }

    }
}

package com.app.aplikasiku.moviex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aplikasiku.moviex.Activity.DetailFavoritTvShowActivity;
import com.app.aplikasiku.moviex.Model.DataFavorit;
import com.app.aplikasiku.moviex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class RcFavoriteTvshow extends RecyclerView.Adapter<RcFavoriteTvshow.ViewHolder> {
    private Context context;
    private ArrayList<DataFavorit> data_fav;

    public static final String EXTRA_FAV = "extra_fav";

    public RcFavoriteTvshow(Context context, ArrayList<DataFavorit> data_fav) {
        this.context = context;
        this.data_fav = data_fav;
    }

    @Override
    public RcFavoriteTvshow.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        RcFavoriteTvshow.ViewHolder holder = new RcFavoriteTvshow.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RcFavoriteTvshow.ViewHolder holder, int position) {
        DataFavorit data = data_fav.get(position);
        Picasso.get().load(data.getPoster()).transform(new CropCircleTransformation()).into(holder.poster);
        Picasso.get().load(data.getPoster()).transform(new BlurTransformation(context)).into(holder.background);
        holder.title.setText(data.getTitle());
        holder.tgl.setText(data.getRelease_date());
        holder.popular.setText(data.getPopular());
    }
    @Override
    public int getItemCount() {
        return data_fav.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,tgl,popular;
        ImageView poster,background;
        public ViewHolder(View item) {
            super(item);
            poster = item.findViewById(R.id.image_poster);
            title = item.findViewById(R.id.title_movie);
            background = item.findViewById(R.id.background);
            tgl = item.findViewById(R.id.date_release);
            popular = item.findViewById(R.id.popular);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent masuk ;
                    masuk = new Intent(context, DetailFavoritTvShowActivity.class);
                    masuk.putExtra(EXTRA_FAV,data_fav.get(getAdapterPosition()));
                    context.startActivity(masuk);
                }
            });
        }
    }
}

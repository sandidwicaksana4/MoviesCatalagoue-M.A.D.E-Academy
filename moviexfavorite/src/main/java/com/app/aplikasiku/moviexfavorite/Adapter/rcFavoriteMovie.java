package com.app.aplikasiku.moviexfavorite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aplikasiku.moviexfavorite.Activity.DetailFavoritMovieActivity;
import com.app.aplikasiku.moviexfavorite.FavoriteContract;
import com.app.aplikasiku.moviexfavorite.Model.DataFavorite;
import com.app.aplikasiku.moviexfavorite.R;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class rcFavoriteMovie extends RecyclerView.Adapter<rcFavoriteMovie.ViewHolder> {

    private Context context;
    private Cursor mCursor;

    public rcFavoriteMovie(Cursor data, Context context) {
        this.context = context;
        setData(data);
    }

    public void setData(Cursor data) {
        mCursor = data;
        notifyDataSetChanged();
    }

    @Override
    public rcFavoriteMovie.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        rcFavoriteMovie.ViewHolder holder = new rcFavoriteMovie.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(rcFavoriteMovie.ViewHolder holder, int position) {
        final DataFavorite favorite = getItem(position);
        Picasso.get().load(favorite.getPoster()).transform(new CropCircleTransformation()).into(holder.poster);
        Picasso.get().load(favorite.getPoster()).transform(new BlurTransformation(context)).into(holder.background);
        holder.title.setText(favorite.getTitle());
        holder.tgl.setText(favorite.getRelease_date());
        holder.popular.setText(favorite.getPopular());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masuk ;
                masuk = new Intent(context, DetailFavoritMovieActivity.class);
                Uri uri = Uri.parse(FavoriteContract.CONTENT_URI+"/"+favorite.getId());
                masuk.putExtra(DetailFavoritMovieActivity.EXTRA_ID,favorite.getId());
                masuk.setData(uri);
                context.startActivity(masuk);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    private DataFavorite getItem(int position) {
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Position Invalid");
        }
        return new DataFavorite(mCursor);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,tgl,popular;
        ImageView poster,background;
        public ViewHolder(final View item) {
            super(item);
            poster = item.findViewById(R.id.image_poster);
            title = item.findViewById(R.id.title_movie);
            background = item.findViewById(R.id.background);
            tgl = item.findViewById(R.id.date_release);
            popular = item.findViewById(R.id.popular);
        }
    }
}

package com.app.aplikasiku.moviex.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aplikasiku.moviex.Favorite.FavoriteContract;
import com.app.aplikasiku.moviex.MainViewModel;
import com.app.aplikasiku.moviex.Model.DetailMovieItem;
import com.app.aplikasiku.moviex.Model.MovieItem;
import com.app.aplikasiku.moviex.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView ivposter, ivbackground;
    TextView txttitle, txtgenre, txtpopular, txttanggal, txtruntime, txtdirector, txtbahasa, txtdeskripsi, txtstatus, txtrevenue, txtbudget;
    FloatingActionButton btnfav;

    public static final String EXTRA_MOV = "extra_mov";
    private MovieItem item;
    private MainViewModel viewModel;

    CollapsingToolbarLayout collapsingToolbarLayout;

    private Boolean isFavorite = false;
    private Boolean isInserted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_detail_movie));
        }
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        btnfav = findViewById(R.id.btn_fav);
        ivbackground = findViewById(R.id.image_backdrop);
        ivposter = findViewById(R.id.image_poster);
        txtpopular = findViewById(R.id.popular);
        txttitle = findViewById(R.id.txt_title);
        txtgenre = findViewById(R.id.txt_genre);
        txttanggal = findViewById(R.id.txt_tanggal);
        txtruntime = findViewById(R.id.txt_runtime);
        txtrevenue = findViewById(R.id.txt_revenue);
        txtstatus = findViewById(R.id.txt_status);
        txtbudget = findViewById(R.id.txt_budget);
        txtdirector = findViewById(R.id.txt_director);
        txtbahasa = findViewById(R.id.txt_bahasa);
        txtdeskripsi = findViewById(R.id.txt_deskripsi);

        item = getIntent().getParcelableExtra(EXTRA_MOV);
        loadData();

        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    DisLike();
                } else {
                    Like();
                }
            }
        });
    }

    private void loadFavorite() {
        if (isFavorite) {
            btnfav.setImageResource(R.drawable.ic_terfavorite);
            isFavorite = true;
        } else {
            btnfav.setImageResource(R.drawable.ic_favorite);
            isFavorite = false;
        }
    }

    private void DisLike() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.dislike);
        builder.setMessage(R.string.pesan_dislike);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DeleteFavorit();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void DeleteFavorit() {
        getContentResolver().delete(
                Uri.parse(FavoriteContract.CONTENT_URI + "/" + item.getId()),
                null,
                null
        );
        btnfav.setImageResource(R.drawable.ic_favorite);
        isFavorite = false;
        Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_LONG).show();
    }

    private void Like() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.like);
        builder.setMessage(R.string.pesan_like);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saveFavorit();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadSQLite() {
        Cursor cursor = getContentResolver().query(
                Uri.parse(FavoriteContract.CONTENT_URI + "/" + item.getId()),
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        loadFavorite();
    }

    private void saveFavorit() {
        ContentValues cv = new ContentValues();
        cv.put(FavoriteContract.FavoriteColumns.ID, item.getId().toString());
        cv.put(FavoriteContract.FavoriteColumns.POSTER, item.getPosterPath());
        cv.put(FavoriteContract.FavoriteColumns.BACKGROUND, item.getBackdropPath());
        cv.put(FavoriteContract.FavoriteColumns.TITLE, txttitle.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.POPULAR, txtpopular.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.GENRE, txtgenre.getText().toString().replace("\n", ", "));
        cv.put(FavoriteContract.FavoriteColumns.TGL, txttanggal.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.RUNTIME, txtruntime.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.COMPANIES, txtdirector.getText().toString().replace("\n", ", "));
        cv.put(FavoriteContract.FavoriteColumns.BAHASA, txtbahasa.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.DESK, txtdeskripsi.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.STATUS, txtstatus.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.BUDGET, txtbudget.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.REVENUE, txtrevenue.getText().toString());
        cv.put(FavoriteContract.FavoriteColumns.KATEGORI, "Movie");
        getContentResolver().insert(FavoriteContract.CONTENT_URI, cv);
        if (isInserted = true) {
            btnfav.setImageResource(R.drawable.ic_terfavorite);
            isFavorite = true;
            Toast.makeText(this, getString(R.string.save_favorite), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.error_favorite), Toast.LENGTH_LONG).show();
        }
    }

    private void loadData() {
        loadSQLite();
        loadDataInServer(String.valueOf(item.getId()));
        Picasso.get().load(item.getBackdropPath()).into(ivbackground);
        txttitle.setText(item.getTitle());
        Picasso.get().load(item.getPosterPath()).into(ivposter);
        txtpopular.setText("" + item.getVoteAverage());
        txttanggal.setText(dateFormat(item.getReleaseDate()));
        txtbahasa.setText(item.getBahasa());
        txtdeskripsi.setText(item.getOverview());
    }

    private String dateFormat(String oldDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(oldDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM yyyy");

        return newFormat.format(myDate);

    }

    private void loadDataInServer(String movie_id) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.setDetailMovies(movie_id);
        viewModel.getDetailMovies().observe(this, getDetailMovies);
    }

    private Observer<DetailMovieItem> getDetailMovies = new Observer<DetailMovieItem>() {
        @Override
        public void onChanged(DetailMovieItem data) {
            int size = 0;

            String genres = "";
            size = data.getGenre().size();
            for (int i = 0; i < size; i++) {
                genres += data.getGenre().get(i).getName() + (i + 1 < size ? "\n" : "");
            }
            txtgenre.setText(genres);
            String companies = "";
            size = data.getProductionCompanies().size();
            for (int i = 0; i < size; i++) {
                companies += data.getProductionCompanies().get(i).getName() + (i + 1 < size ? "\n" : "");
            }
            txtdirector.setText(companies);
            txtruntime.setText("" + data.getRuntime() + " " + getString(R.string.runtime));
            txtstatus.setText(data.getStatus());
            txtbudget.setText("$ " + NumberFormat.getIntegerInstance().format(data.getBudget()));
            txtrevenue.setText("$ " + NumberFormat.getIntegerInstance().format(data.getRevenue()));
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

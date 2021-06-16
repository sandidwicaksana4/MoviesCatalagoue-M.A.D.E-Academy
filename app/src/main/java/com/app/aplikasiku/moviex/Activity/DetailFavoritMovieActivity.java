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
import com.app.aplikasiku.moviex.Model.DataFavorit;
import com.app.aplikasiku.moviex.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class DetailFavoritMovieActivity extends AppCompatActivity {

    ImageView ivposter, ivbackground;
    TextView txttitle, txtgenre, txtpopular, txttanggal, txtruntime, txtdirector, txtbahasa, txtdeskripsi, txtstatus, txtrevenue, txtbudget;
    FloatingActionButton btnfav;

    public static final String EXTRA_FAV = "extra_fav";
    private DataFavorit favorit;

    CollapsingToolbarLayout collapsingToolbarLayout;

    private Boolean isFavorite = false;
    private Boolean isInserted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorit_movie);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_detail_favorit));
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

        favorit = getIntent().getParcelableExtra(EXTRA_FAV);
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
                Uri.parse(FavoriteContract.CONTENT_URI + "/" + favorit.getId()),
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


    private void saveFavorit() {
        ContentValues cv = new ContentValues();
        cv.put(FavoriteContract.FavoriteColumns.ID, favorit.getId().toString());
        cv.put(FavoriteContract.FavoriteColumns.POSTER, favorit.getPoster());
        cv.put(FavoriteContract.FavoriteColumns.BACKGROUND, favorit.getBackground());
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

    private void loadSQLite() {
        Cursor cursor = getContentResolver().query(
                Uri.parse(FavoriteContract.CONTENT_URI + "/" + favorit.getId()),
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

    private void loadData() {
        loadSQLite();
        Picasso.get().load(favorit.getBackground()).into(ivbackground);
        Picasso.get().load(favorit.getPoster()).into(ivposter);
        txtpopular.setText(favorit.getPopular());
        txttitle.setText(favorit.getTitle());
        txtgenre.setText(favorit.getGenres().replace(", ", "\n"));
        txttanggal.setText(favorit.getRelease_date());
        txtruntime.setText(favorit.getRuntime());
        txtstatus.setText(favorit.getStatus());
        txtbudget.setText(favorit.getBudget());
        txtrevenue.setText(favorit.getRevenue());
        txtdirector.setText(favorit.getProduction_companies().replace(", ", "\n"));
        txtbahasa.setText(favorit.getLanguage());
        txtdeskripsi.setText(favorit.getDecription());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

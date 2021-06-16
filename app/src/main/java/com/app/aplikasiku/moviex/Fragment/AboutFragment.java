package com.app.aplikasiku.moviex.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aplikasiku.moviex.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_about,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivfoto = view.findViewById(R.id.image_me);
        TextView name = view.findViewById(R.id.txt_name);
        TextView email = view.findViewById(R.id.txt_email);
        TextView phone = view.findViewById(R.id.txt_phone);

        Picasso.get().load(R.drawable.moviex).transform(new CropCircleTransformation()).into(ivfoto);
        name.setText("Sandi Dwi Wicaksono");
        email.setText("sandidwicaksana4@yahoo.com");
        phone.setText("08x xxx xxx xxx");

    }
}

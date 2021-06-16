package com.app.aplikasiku.moviex.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.app.aplikasiku.moviex.BuildConfig;
import com.app.aplikasiku.moviex.R;
import com.app.aplikasiku.moviex.Reminder.NotificationReceiver;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    RadioGroup radioGroup;
    Button ganti;
    SwitchCompat daily,release;

    private NotificationReceiver notificationReceiver ;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radiogrup);
        daily = view.findViewById(R.id.daily_switch);
        release = view.findViewById(R.id.release_switch);

        sharedPreferences = getActivity().getSharedPreferences(BuildConfig.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        notificationReceiver = new NotificationReceiver(getActivity());

        loadLocale();
        switchChanged();
        savePreferences();

        ganti = view.findViewById(R.id.btn_bahasa);
        ganti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedid = radioGroup.getCheckedRadioButtonId();
                switch (selectedid){
                    case R.id.indo:
                        setLocale("in");
                        getActivity().recreate();
                        break;
                    case R.id.english:
                        setLocale("en");
                        getActivity().recreate();
                        break;
                }
            }
        });
    }

    private void switchChanged(){
        daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                editor = sharedPreferences.edit();
                editor.putBoolean("daily_reminder",isChecked);
                editor.apply();
                if (isChecked){
                    notificationReceiver.setDailyReminder();
                }else {
                    notificationReceiver.cancelDailyReminder(getActivity());
                }

            }
        });
        release.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                editor = sharedPreferences.edit();
                editor.putBoolean("release_reminder",isChecked);
                editor.apply();
                if (isChecked){
                    notificationReceiver.setReleaseTodayReminder();
                }else {
                    notificationReceiver.cancelReleaseToday(getActivity());
                }
            }
        });
    }

    private void savePreferences(){
        boolean dailyReminder = sharedPreferences.getBoolean("daily_reminder", false);
        boolean releaseReminder = sharedPreferences.getBoolean("release_reminder", false);

        daily.setChecked(dailyReminder);
        release.setChecked(releaseReminder);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config,getContext().getResources().getDisplayMetrics());
        //simpan bahasa
        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_Lang","");
        setLocale(language);
    }
}

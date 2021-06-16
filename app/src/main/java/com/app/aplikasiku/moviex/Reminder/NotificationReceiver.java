package com.app.aplikasiku.moviex.Reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.app.aplikasiku.moviex.Activity.MainActivity;
import com.app.aplikasiku.moviex.Adapter.Language;
import com.app.aplikasiku.moviex.Model.MovieItem;
import com.app.aplikasiku.moviex.Network.APIItem;
import com.app.aplikasiku.moviex.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationReceiver extends BroadcastReceiver {
	private static final String EXTRA_TYPE = "type";
	private static final String TYPE_DAILY = "daily_reminder";
	private static final String TYPE_RELEASE = "release_reminder";
	private static final int ID_DAILY_REMINDER = 100;
	private static final int ID_RELEASE_TODAY = 101;

	private Call<MovieItem> call;
	private APIItem apiItem = new APIItem();
	private Context context;

	public NotificationReceiver(){

	}
	public NotificationReceiver(Context context) {
		this.context = context;
	}


	@Override
	public void onReceive(Context context, Intent intent) {
		String type = intent.getStringExtra(EXTRA_TYPE);
		if (type.equals(TYPE_DAILY)) {
			showDailyReminder(context);
		} else if (type.equals(TYPE_RELEASE)) {
			getReleaseToday(context);
		}
	}

	private Calendar getReminderTime(String type) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, type.equals(TYPE_DAILY) ? 7 : 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		if (calendar.before(Calendar.getInstance())) {
			calendar.add(Calendar.DATE, 1);
		}

		return calendar;
	}

	private Intent getReminderIntent(String type) {
		Intent intent = new Intent(context, NotificationReceiver.class);
		intent.putExtra(EXTRA_TYPE, type);
		return intent;
	}

	public void setDailyReminder() {
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY_REMINDER, getReminderIntent(TYPE_DAILY), 0);
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getReminderTime(TYPE_DAILY).getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

	}

	public void setReleaseTodayReminder() {
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE_TODAY, getReminderIntent(TYPE_RELEASE), 0);
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getReminderTime(TYPE_RELEASE).getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

	}

	private void showDailyReminder(Context context) {
		int NOTIFICATION_ID = 1;
		String CHANNEL_ID = "Channel_1";
		String CHANNEL_NAME = "Daily Reminder";

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Uri uriRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
				.setSmallIcon(R.drawable.moviex)
				.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.moviex))
				.setContentTitle(context.getResources().getString(R.string.title_daily_moviex))
				.setContentText(context.getResources().getString(R.string.daily_message_reminder))
				.setContentIntent(pendingIntent)
				.setAutoCancel(true)
				.setSound(uriRingtone)
				.setColor(ContextCompat.getColor(context, android.R.color.transparent))
				.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
				.setLights(Color.RED,3000,3000)
				.setPriority(NotificationCompat.PRIORITY_DEFAULT);
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
			mBuilder.setChannelId(CHANNEL_ID);
			mNotificationManager.createNotificationChannel(channel);

			if (mNotificationManager != null) {
				mNotificationManager.createNotificationChannel(channel);
			}
		}
		if (mNotificationManager != null) {
			mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		}
	}

	private void showReleaseToday(Context context, String title, String desc, int id) {
		String CHANNEL_ID = "Channel_2";
		String CHANNEL_NAME = "Release Reminder";

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Uri uriRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
				.setSmallIcon(R.drawable.moviex)
				.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.moviex))
				.setContentTitle(title)
				.setContentText(desc)
				.setContentIntent(pendingIntent)
				.setAutoCancel(true)
				.setColor(ContextCompat.getColor(context, android.R.color.transparent))
				.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
				.setSound(uriRingtone)
				.setLights(Color.RED,3000,3000)
				.setPriority(NotificationCompat.PRIORITY_DEFAULT);
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
			mBuilder.setChannelId(CHANNEL_ID);

			if (mNotificationManager != null) {
				mNotificationManager.createNotificationChannel(channel);
			}
		}
		if (mNotificationManager != null) {
			mNotificationManager.notify(id, mBuilder.build());
		}
	}

	private void getReleaseToday(final Context context) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date date = new Date();
		final String now = dateFormat.format(date);

		call = apiItem.getService().getReleasedMovies(Language.getCountry(),now, now);
		call.enqueue(new Callback<MovieItem>() {
			@Override
			public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
				if (response.isSuccessful()) {
					ArrayList<MovieItem> movies = response.body().getResults();
					int id = 2;
					for (int i = 0; i < movies.size(); i++) {
						String title = movies.get(i).getTitle();
						String desc = title + " " + context.getString(R.string.release_reminder_message);
						showReleaseToday(context, title, desc, id);
						id++;
					}
				}
			}

			@Override
			public void onFailure(Call<MovieItem> call, Throwable t) {

			}
		});
	}

	private void cancelReminder(Context context, String type) {
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, NotificationReceiver.class);
		int requestCode = type.equalsIgnoreCase(TYPE_DAILY) ? ID_DAILY_REMINDER : ID_RELEASE_TODAY;
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
		pendingIntent.cancel();
		if (alarmManager != null) {
			alarmManager.cancel(pendingIntent);
		}
	}

	public void cancelDailyReminder(Context context) {
		cancelReminder(context, TYPE_DAILY);
	}

	public void cancelReleaseToday(Context context) {
		cancelReminder(context, TYPE_RELEASE);
	}
}

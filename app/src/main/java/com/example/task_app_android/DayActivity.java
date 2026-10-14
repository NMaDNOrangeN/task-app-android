package com.example.task_app_android;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewDay = findViewById(R.id.textViewDay);
        textViewDay.setText("Every day You need to do:\n1) Lunch\n2) Small sleep\n3) Exercise");
        textViewDay.setTextSize(20);
        textViewDay.setTextColor(Color.BLACK);

        showNotification();
    }

    private static final String CHANNEL_ID = "activityDay_channel";
    private void showNotification() {
        NotificationManager managerDay = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channelDay = new NotificationChannel(CHANNEL_ID, "Day Notification", NotificationManager.IMPORTANCE_HIGH);
        managerDay.createNotificationChannel(channelDay);
        Notification notificationDay = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("About work")
                .setContentText("The end of work day is soon")
                .build();
        managerDay.notify(1, notificationDay);
    }

    public void onClickDayToMain(View view) {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentMain);
    }

    public void onClickDayToEvening(View view) {
        Intent intentEvening = new Intent(this, EveningActivity.class);
        intentEvening.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentEvening);
    }
}
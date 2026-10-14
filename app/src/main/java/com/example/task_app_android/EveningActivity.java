package com.example.task_app_android;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EveningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_evening);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewEvening = findViewById(R.id.textViewEvening);
        textViewEvening.setText("Films and serials to watch:");
        textViewEvening.setTextSize(20);
        textViewEvening.setTextColor(Color.BLACK);


        Spinner spinnerEvening = findViewById(R.id.spinnerEvening);
        String[] films = {
                "The Shawshank Redemption",
                "The Simpsons",
                "Star Wars",
                "Breaking Bad"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, films);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvening.setAdapter(adapter);

        showNotification();
    }

    private static final String CHANNEL_ID = "activityEvening_channel";
    private void showNotification() {
        NotificationManager managerEvening = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channelEvening = new NotificationChannel(CHANNEL_ID, "Evening Notification", NotificationManager.IMPORTANCE_HIGH);
        managerEvening.createNotificationChannel(channelEvening);
        Notification notificationEvening = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Go to sleep")
                .setContentText("You need to go and sleep well")
                .build();
        managerEvening.notify(1, notificationEvening);
    }

    public void onClickEveningToMain(View view) {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentMain);
    }

    public void onClickEveningToNight(View view) {
        Intent intentNight = new Intent(this, NightActivity.class);
        intentNight.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentNight);
    }
}
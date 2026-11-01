package com.example.task_app_android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton buttonBell = view.findViewById(R.id.buttonBell);

        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
        }

        NotificationManager manager = requireContext().getSystemService(NotificationManager.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "student_channel", "Student", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        buttonBell.setOnClickListener(v -> {
            TextView textView1 = requireActivity().findViewById(R.id.textView1);
            TextView textView2 = requireActivity().findViewById(R.id.textView2);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "student_channel")
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Student data")
                    .setContentText(textView1.getText() + ", group: " + textView2.getText());

            manager.notify(1, builder.build());
        });
    }
}
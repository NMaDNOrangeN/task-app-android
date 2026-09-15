package com.example.task_app_android;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView studentData = findViewById(R.id.textView1);
        TextView groupData = findViewById(R.id.textView2);
        ImageView imageView = findViewById(R.id.imageView);
        Button buttonHideInfo = findViewById(R.id.button);
        ImageButton imageButtonHide = findViewById(R.id.imageButton1);

        studentData.setText("Shabalin E.K.");
        studentData.setTextSize(20);
        studentData.setTextColor(Color.GRAY);

        groupData.setText("T-433901-IST");
        groupData.setTextSize(20);
        groupData.setTextColor(Color.RED);

        imageView.setImageResource(R.drawable.btr);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        buttonHideInfo.setText("Hide INFO");
        buttonHideInfo.setTextSize(20);
        buttonHideInfo.setOnClickListener(v -> {
            if (studentData.getVisibility() == TextView.VISIBLE) {
                studentData.setVisibility(TextView.INVISIBLE);
                groupData.setVisibility(TextView.INVISIBLE);
            }
            else {
                studentData.setVisibility(TextView.VISIBLE);
                groupData.setVisibility(TextView.VISIBLE);
            }
        });

        imageButtonHide.setImageResource(R.drawable.inv);
        imageButtonHide.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
        imageButtonHide.setOnClickListener(v -> {
            if (imageView.getVisibility() == ImageView.VISIBLE)
                imageView.setVisibility(ImageView.INVISIBLE);
            else
                imageView.setVisibility(ImageView.VISIBLE);
        });
    }
}
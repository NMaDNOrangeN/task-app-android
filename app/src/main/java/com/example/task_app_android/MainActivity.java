package com.example.task_app_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    private Button buttonScan;

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

        buttonScan = findViewById(R.id.buttonScan);

        studentData.setText("Shabalin E.K.");
        studentData.setTextSize(20);
        studentData.setTextColor(Color.GRAY);

        groupData.setText("T-433901-IST");
        groupData.setTextSize(20);
        groupData.setTextColor(Color.RED);

        buttonScan.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.CAMERA
                }, 100);
            } else {
                startScan();
            }
        });
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            openResult(result.getContents());
        }
    });

    private void startScan() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Point the camera at the QR-code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startScan();
        }
        else {
            Toast.makeText(this, "Needs camera permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void openResult(String content) {
        String text = content.trim().toLowerCase();
        String title;
        int image;

        switch (text) {
            case "house":
                title = "House";
                image = R.drawable.house;
                break;
            case "dog":
                title = "Dog";
                image = R.drawable.dog;
                break;
            case "cat":
                title = "Cat";
                image = R.drawable.cat;
                break;
            case "car":
                title = "Car";
                image = R.drawable.car;
                break;
            case "gun":
                title = "Gun";
                image = R.drawable.gun;
                break;
            default:
                Toast.makeText(this, "Unknown code: " + content, Toast.LENGTH_LONG).show();
                return;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("image", image);
        startActivity(intent);
    }
}
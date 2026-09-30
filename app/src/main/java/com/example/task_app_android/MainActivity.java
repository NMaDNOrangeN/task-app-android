package com.example.task_app_android;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button short_toastButton;
    private Button long_toastButton;
    private Button dialog_and_iconButton;
    private Button multi_choice_dialogButton;

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

        short_toastButton = findViewById(R.id.button1);
        long_toastButton = findViewById(R.id.button2);
        dialog_and_iconButton = findViewById(R.id.button3);
        multi_choice_dialogButton = findViewById(R.id.button4);


        studentData.setText("Shabalin E.K.");
        studentData.setTextSize(20);
        studentData.setTextColor(Color.GRAY);

        groupData.setText("T-433901-IST");
        groupData.setTextSize(20);
        groupData.setTextColor(Color.RED);

        short_toastButton.setOnClickListener(v -> short_toastShow());
        long_toastButton.setOnClickListener(v -> long_toastShow());
        dialog_and_iconButton.setOnClickListener(v -> dialog_and_iconShow());
        multi_choice_dialogButton.setOnClickListener(v -> multi_choice_dialogShow());

    }

    private void short_toastShow() {
        Toast.makeText(this, "This toast is SHORT!", Toast.LENGTH_SHORT).show();
    }

    private void long_toastShow() {
        Toast longToast = Toast.makeText(this, "This toast is LONG!!!", Toast.LENGTH_LONG);
        longToast.setGravity(Gravity.TOP, 0, 150);
        longToast.show();
    }

    private void dialog_and_iconShow() {
        CustomDialogFragment dialog_and_icon = new CustomDialogFragment();
        dialog_and_icon.show(getSupportFragmentManager(), "dialog_and_icon");
    }

    public void makeButtonsTextRed() {
        short_toastButton.setTextColor(Color.RED);
        long_toastButton.setTextColor(Color.RED);
        dialog_and_iconButton.setTextColor(Color.RED);
        multi_choice_dialogButton.setTextColor(Color.RED);
    }

    private void multi_choice_dialogShow() {
        final String[] calibers = {
                ".308 Win",
                ".223 Remington",
                "7.62x39",
                ".45 ACP",
                ".338 Lapua Magnum",
                "7.62x51",
                ".300 BLK",
                "12 gauge",
                "277 Fury",
                "5.7x28",
                ".22LR",
                "4.6x30",
                "T65"
        };

        final  boolean[] trueAnswers = {
                true,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                true
        };

        final boolean[] checkedAnswers = new boolean[calibers.length];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select the same calibers (with different names)");
        builder.setMultiChoiceItems(calibers, checkedAnswers, (dialog, which, isChecked) -> {
            checkedAnswers[which] = isChecked;
        });

        builder.setPositiveButton("OK", (dialog, which) -> {
            boolean isCorrect = true;

            for (int i = 0; i < calibers.length; i++) {
                if (checkedAnswers[i] != trueAnswers[i]) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                Toast.makeText(this, "You're right!", Toast.LENGTH_SHORT).show();
            } else {
                hideButtons();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void hideButtons() {
        short_toastButton.setVisibility(Button.INVISIBLE);
        long_toastButton.setVisibility(Button.INVISIBLE);
        dialog_and_iconButton.setVisibility(Button.INVISIBLE);
        multi_choice_dialogButton.setVisibility(Button.INVISIBLE);
    }
}
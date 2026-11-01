package com.example.task_app_android;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        TextView studentData = findViewById(R.id.textView1);
        TextView groupData = findViewById(R.id.textView2);

        studentData.setText("Shabalin E.K.");
        studentData.setTextSize(20);
        studentData.setTextColor(Color.GRAY);

        groupData.setText("T-433901-IST");
        groupData.setTextSize(20);
        groupData.setTextColor(Color.RED);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Fragment firstFragment = new FirstFragment();
        Fragment secondFragment = new SecondFragment();
        Fragment thirdFragment = new ThirdFragment();

        setCurrentFragment(firstFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            try {
                int id = item.getItemId();
                if (id == R.id.feedback) {
                    setCurrentFragment(firstFragment);
                } else if (id == R.id.test) {
                    setCurrentFragment(secondFragment);
                } else if (id == R.id.notification) {
                    setCurrentFragment(thirdFragment);
                }
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.csFragment, fragment).commit();
    }
}
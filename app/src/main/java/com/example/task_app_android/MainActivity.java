package com.example.task_app_android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listCategories;
    private ListView listProducts;
    private SQLiteDatabase database;

    private final ArrayList<Integer> categoryIds = new ArrayList<>();
    private int currentCategoryId = -1;

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

        studentData.setText("Shabalin E.K.");
        studentData.setTextSize(20);
        studentData.setTextColor(Color.GRAY);

        groupData.setText("T-433901-IST");
        groupData.setTextSize(20);
        groupData.setTextColor(Color.RED);

        listCategories = findViewById(R.id.listCategories);
        listProducts = findViewById(R.id.listProducts);
        Button buttonRefresh = findViewById(R.id.buttonRefresh);

        DBHelper helper = new DBHelper(this);
        database = helper.getReadableDatabase();

        loadCategories();

        listCategories.setOnItemClickListener((parent, view, position, id) ->
                showProducts(categoryIds.get(position)));

        buttonRefresh.setOnClickListener(v -> {
            loadCategories();
            if (currentCategoryId != -1) {
                showProducts(currentCategoryId);
            }
            Toast.makeText(this, "Data refreshed", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadCategories() {
        categoryIds.clear();
        ArrayList<String> names = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT id, name FROM categories", null);
        while (cursor.moveToNext()) {
            categoryIds.add(cursor.getInt(0));
            names.add(cursor.getString(1));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, names);
        listCategories.setAdapter(adapter);
    }

    private void showProducts(int categoryId) {
        currentCategoryId = categoryId;

        ArrayList<HashMap<String, String>> goods = new ArrayList<>();

        Cursor cursor = database.rawQuery(
                "SELECT name, description, price FROM goods WHERE category_id = ?",
                new String[]{String.valueOf(categoryId)});

        while (cursor.moveToNext()) {
            HashMap<String, String> item = new HashMap<>();
            item.put("name", cursor.getString(0));
            item.put("description", cursor.getString(1));
            item.put("price", cursor.getInt(2) + " ₽");
            goods.add(item);
        }
        cursor.close();

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                goods,
                R.layout.listview_item,
                new String[]{"name", "description", "price"},
                new int[]{R.id.textName, R.id.textDescription, R.id.textPrice});
        listProducts.setAdapter(adapter);
    }
}
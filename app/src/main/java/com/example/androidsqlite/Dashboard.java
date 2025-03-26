package com.example.androidsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {

    Button btnData, btnFitur2, btnFitur3, btnFitur4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Menyesuaikan tampilan dengan sistem insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi tombol
        btnData = findViewById(R.id.btnData);
        btnFitur2 = findViewById(R.id.btnFitur2);
        btnFitur3 = findViewById(R.id.btnFitur3);
        btnFitur4 = findViewById(R.id.btnFitur4);

        // Tambahkan onClickListener untuk setiap tombol
        btnData.setOnClickListener(view -> openActivity(MainActivity.class));
        btnFitur2.setOnClickListener(view -> openActivity(MainActivity.class));
        btnFitur3.setOnClickListener(view -> openActivity(MainActivity.class));
        btnFitur4.setOnClickListener(view -> openActivity(MainActivity.class));
    }

    // Fungsi untuk berpindah ke activity lain
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(Dashboard.this, activityClass);
        startActivity(intent);
    }
}

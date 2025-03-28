package com.example.androidsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    private String username = "Riska";
    private String password = "Admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().equalsIgnoreCase(username) && etPassword.getText().toString().equalsIgnoreCase(password)){
                    Intent login = new Intent(Login.this, Dashboard.class);
                    startActivity(login);

                    Toast.makeText(Login.this, "LOGIN BERHASIL!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Username atau Password Yang Anda Masukkan Salah!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
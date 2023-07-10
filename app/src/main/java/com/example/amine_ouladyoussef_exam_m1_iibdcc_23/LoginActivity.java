package com.example.amine_ouladyoussef_exam_m1_iibdcc_23;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEditText, passwordEditText;
    private Button loginButton;

    private SharedPrefManager sharedPrefManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPrefManager = new SharedPrefManager(this);


        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = loginEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                boolean isAuthenticated = authenticate(login, password);

                if (isAuthenticated) {

                    sharedPrefManager.saveLoginCredentials(login, password);


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid login or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean authenticate(String login, String password) {

        return password.equals("password");
    }
}

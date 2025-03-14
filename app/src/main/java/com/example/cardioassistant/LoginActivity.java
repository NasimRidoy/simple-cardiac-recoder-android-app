package com.example.cardioassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public TextView emailAddress, password , create_new_account;
    public Button loginButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = emailAddress.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                if (emailString.isEmpty()) {
                    emailAddress.setError("Email is required");
                    emailAddress.requestFocus();
                    return;
                }

                if (passwordString.isEmpty()) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(emailString,passwordString).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        mainActivityIntent();
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Invalid Email or password"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerIntent();
            }
        });
    }
    private void mainActivityIntent() {
        Intent mainIntent = new Intent(this,HomeActivity.class);
        this.startActivity(mainIntent);
    }
    private void registerIntent() {
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        this.startActivity(registerIntent);
    }
    private void initialization() {
        mAuth = FirebaseAuth.getInstance();
        emailAddress = (TextView) findViewById(R.id.login_email_view);
        password = (TextView) findViewById(R.id.login_password_view);
        loginButton = (Button) findViewById(R.id.login_loginButton);
        create_new_account = (TextView) findViewById(R.id.login_create_new_account);
    }
}
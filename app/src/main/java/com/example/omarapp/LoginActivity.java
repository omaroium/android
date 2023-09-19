package com.example.omarapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;

    Button loginButton;
    TextView error;
    TextView createAnAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText=findViewById(R.id.email_login);
        passwordEditText=findViewById(R.id.password_login);
        loginButton=findViewById(R.id.loginPageBt);
        createAnAcc=findViewById(R.id.create_acc);
        error=findViewById(R.id.login_error);
        createAnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,SigninActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setVisibility(View.GONE);
                final FirebaseAuth mAuth=FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    error.setVisibility(View.VISIBLE);
                                    Log.d("error","error");

                                }
                            }
                        });
            }
        });
    }
}
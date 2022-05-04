package com.example.sapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {
    private EditText editTextEmail,editTextPass;
    private Button btnLogin;
    private TextView textViewRe;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        mAuth=FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.inputEmail);
        editTextPass = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnRe);
        textViewRe = findViewById(R.id.Re);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        textViewRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                re();
            }
        });

    }
    private void login() {
        String email,pass;
        email=editTextEmail.getText().toString();
        pass = editTextPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui long nhap Email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui long nhap mat khau!",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DangNhapActivity.this,MainActivity.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Dang nhap that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void re() {
        Intent i = new Intent(DangNhapActivity.this,DangkyActivity.class);
        startActivity(i);
    }


}
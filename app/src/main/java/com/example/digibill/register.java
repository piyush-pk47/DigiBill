package com.example.digibill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button registerbttn;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.regemail);
        password=findViewById(R.id.regpassword);
        registerbttn=findViewById(R.id.registerBttn);
        auth=FirebaseAuth.getInstance();

        registerbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finEmail=email.getText().toString();
                String finPass=password.getText().toString();

                if(TextUtils.isEmpty(finEmail) || TextUtils.isEmpty(finPass))
                {
                    Toast.makeText(register.this,"Empty",Toast.LENGTH_SHORT).show();
                }
                else if(strongPassword(finEmail)==0)
                {
                    Toast.makeText(register.this,"Password is too weak",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registerUser(finEmail,finPass);
                }
            }
        });



    }

    private void registerUser(String finEmail, String finPass)
    {
        auth.createUserWithEmailAndPassword(finEmail,finPass).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(register.this,"Registerd succesfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(register.this,MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(register.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int strongPassword(String finEmail)
    {
        if(finEmail.length()>=6)
            return 1;
        return 0;
    }
}
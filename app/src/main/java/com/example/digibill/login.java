package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private FirebaseAuth auth;
    public void registeractivity(View view)
    {
        startActivity(new Intent(login.this,register.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.logemail);
        password=findViewById(R.id.logpass);
        login=findViewById(R.id.logbttn);
        auth= FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finemail=email.getText().toString();
                String finpass=password.getText().toString();
                if(TextUtils.isEmpty(finemail) || TextUtils.isEmpty(finpass))
                {
                    Toast.makeText(login.this,"Empty",Toast.LENGTH_SHORT).show();
                }
                loginuser(finemail,finpass);
            }
        });
    }

    private void loginuser(String finemail, String finpass)
    {
        auth.signInWithEmailAndPassword(finemail,finpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this,"Login successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this,MainActivity.class));
                finish();
            }
        });
    }

}
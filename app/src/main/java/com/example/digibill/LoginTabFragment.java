package com.example.digibill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment implements View.OnClickListener {

    EditText email;
    EditText password;
    Button login;
    TextView forgetpass;
    private FirebaseAuth auth;
    float v = 0;

    public LoginTabFragment() {
        // empty constructor = asach
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        email = root.findViewById(R.id.logemail);
        password = root.findViewById(R.id.logpass);
        login = root.findViewById(R.id.logbttn);
        forgetpass = root.findViewById(R.id.forgetpass);

        email.setTranslationX(800);
        password.setTranslationX(800);
        login.setTranslationX(800);
        forgetpass.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);
        forgetpass.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

        return root;
    }

    @Override
    public void onClick(View v) {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        LoginUser(mail, pass);
    }

    private void LoginUser(String mail,String password) {
        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(),"Login Succesful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(),MainActivity.class));
                } else {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), task.getException().getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}

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
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpTabFragment extends Fragment implements View.OnClickListener {

    EditText shopee_name;
    EditText regemail;
    EditText regpassword;
    EditText reg_mobile;
    Button signupbttn;
    private FirebaseAuth auth;

    float v = 0;

    public SignUpTabFragment() {
        // empty constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_tab_fragment,container,false);

        shopee_name = root.findViewById(R.id.shopee_name);
        regemail = root.findViewById(R.id.regemail);
        regpassword = root.findViewById(R.id.regpassword);
        reg_mobile = root.findViewById(R.id.reg_mobile);
        signupbttn = root.findViewById(R.id.signupbttn);

        shopee_name.setAlpha(v);
        regemail.setAlpha(v);
        regpassword.setAlpha(v);
        reg_mobile.setAlpha(v);
        signupbttn.setAlpha(v);

        shopee_name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        regemail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        regpassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        reg_mobile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        signupbttn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1000).start();

        signupbttn.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

        return root;
    }

    @Override
    public void onClick(View v) {
        SignUpUser(regemail.getText().toString(),regpassword.getText().toString());
    }

    private void SignUpUser(String mail,String password) {
        auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(),"Registerd succesfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),MainActivity.class));
                } else {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), task.getException().getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}



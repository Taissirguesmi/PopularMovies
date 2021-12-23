package com.azhar.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.movies.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.azhar.moviedb.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_tab extends Fragment {
    private FirebaseAuth mAuth;
    EditText email,password;
    TextView forget;
    Button login;
    float v=0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.login_tab,container,false);
        mAuth = FirebaseAuth.getInstance();
        email=root.findViewById(R.id.Email);
        password=root.findViewById(R.id.password);
        forget=root.findViewById(R.id.forget);
        login=root.findViewById(R.id.login);

        email.setTranslationY(300);
        password.setTranslationY(300);
        forget.setTranslationY(300);
        login.setTranslationY(300);

        email.setAlpha(v);
        password.setAlpha(v);
        forget.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        forget.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.login:
                        userLogin();
                        break;
                }
            }
        });


        return root;
    }
    private void userLogin() {
        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(mail.isEmpty()){
            email.setError("Full name is required");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(password.length()<6){
            password.setError("Min Password length should be 6 characters");
            password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity( new Intent(login_tab.this.getActivity(), MainActivity.class));

                        }else{
                            Toast.makeText(login_tab.this.getActivity(),"Try again!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}







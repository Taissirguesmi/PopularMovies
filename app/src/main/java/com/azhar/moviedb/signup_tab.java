package com.azhar.moviedb;
import com.movies.R;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup_tab extends Fragment {
    EditText email,pass,age,username;
    Button signup;
    private FirebaseAuth mAuth;
    float v=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.signup_tab,container,false);

        mAuth = FirebaseAuth.getInstance();

        username=root.findViewById(R.id.name);
        pass=root.findViewById(R.id.password);
        email=root.findViewById(R.id.Email);
        age=root.findViewById(R.id.age);
        signup=root.findViewById(R.id.signup);


            email.setTranslationY(300);
            pass.setTranslationY(300);
            username.setTranslationY(300);
            age.setTranslationY(300);
            signup.setTranslationY(300);

            email.setAlpha(v);
            pass.setAlpha(v);
            username.setAlpha(v);
            age.setAlpha(v);
            signup.setAlpha(v);

            email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
            pass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
            username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
            age.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
            signup.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();


            signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.signup:
                        registerUser();
                        break;
                }

            }
        });



        return root;
    }

    private void registerUser() {
        String mail=email.getText().toString().trim();
        String password=pass.getText().toString().trim();
       String user=username.getText().toString().trim();
       String agee=age.getText().toString().trim();

        if(user.isEmpty()){
            username.setError("Full name is required");
            username.requestFocus();
            return;
        }
        if(agee.isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
            return;
        }
        if(mail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Min Password length should be 6 characters");
            pass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             User user1=new User(user,agee,mail);
                             FirebaseDatabase.getInstance().getReference("Users")
                                     .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                     .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if(task.isSuccessful()){
                                         Toast.makeText(getContext().getApplicationContext(),"User has been registred Successfully!",Toast.LENGTH_SHORT).show();

                                     }
                                     else{
                                         Toast.makeText(getContext().getApplicationContext(),"faild to register!",Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             });
                         }else{
                             Toast.makeText(getContext().getApplicationContext(),"Faild to register",Toast.LENGTH_SHORT).show();
                         }
                    }
                });

    }

}

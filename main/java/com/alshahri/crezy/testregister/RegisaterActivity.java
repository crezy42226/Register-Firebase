package com.alshahri.crezy.testregister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisaterActivity extends AppCompatActivity {

    TextInputLayout names,emails,passwords;
    Button bteRegister;
    FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisater);

        names = (TextInputLayout) findViewById(R.id.text_name_reg);
        emails = (TextInputLayout) findViewById(R.id.text_email_reg);
        passwords = (TextInputLayout) findViewById(R.id.text_password_reg);

        bteRegister = (Button)findViewById(R.id.but_register);
        auth = FirebaseAuth.getInstance();
        bteRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = names.getEditText().getText().toString();
                String email = emails.getEditText().getText().toString();
                String password = passwords.getEditText().getText().toString();

                registerUser(name,email,password);
            }
        });
    }

    private void registerUser(String name, String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent i = new Intent(RegisaterActivity.this,MainActivity.class);
                    startActivity(i);
                }

            }
        });
    }
}

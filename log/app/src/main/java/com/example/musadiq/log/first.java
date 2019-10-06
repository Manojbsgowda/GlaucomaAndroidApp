package com.example.musadiq.log;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class first extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Name = (EditText) findViewById(R.id.etname);
        Password = (EditText) findViewById(R.id.etpass);
        Info = (TextView) findViewById(R.id.fg);
        Login = (Button) findViewById(R.id.btnlogin);
        userRegistration = (TextView) findViewById(R.id.tvreigister);
        forgotPassword = (TextView)findViewById(R.id.forgot);


        Info.setText("No of attempts remaning:5");
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            //  finish();
            //    startActivity(new Intent(first.this, register.class));//
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(first.this, register.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(first.this, PasswordActivity.class));
            }
        });
    }

    private void validate(String userEmail, String userPassword) {
        progressDialog.setMessage("Have Patience");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(first.this, "login sucessfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(first.this, dash.class));
                    FirebaseUser user=mAuth.getCurrentUser();
                    checkEmailVerification();
                } else {
                    Toast.makeText(first.this, "login unsucessfull", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("no of attempts remaning: " + counter);
                    progressDialog.dismiss();
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }

                }
            }


        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(first.this, dash.class));
    }
}

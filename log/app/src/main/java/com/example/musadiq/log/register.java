package com.example.musadiq.log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class register extends AppCompatActivity {
    private EditText userName,userPassword,userEmail,userEye,userAge,userSex,userFieldtest,userDrops;
    private Button registerr;
    private TextView already;
    private FirebaseAuth mAuth;
    String email, name, age, password;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        mAuth=FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        registerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserData();
                                mAuth.signOut();
                                Toast.makeText(register.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(register.this, first.class));
                            }else{
                                Toast.makeText(register.this,"registration unsucessfull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,first.class));
            }
        });
    }
    private void setupUIViews(){
        userName=(EditText)findViewById(R.id.uname);
        userEmail=(EditText)findViewById(R.id.useremail);
        userPassword=(EditText)findViewById(R.id.userpass);
        registerr=(Button)findViewById(R.id.b1);
        already=(TextView)findViewById(R.id.signup);
        userEye=(EditText)findViewById(R.id.ey);
        userAge=(EditText)findViewById(R.id.Ag);
        userSex=(EditText)findViewById(R.id.Se);
        userFieldtest=(EditText)findViewById(R.id.Field);
        userDrops=(EditText)findViewById(R.id.drops);
    }
    private Boolean validate()
    {
        Boolean result=false;
        String name=userName.getText().toString();
        String Password=userPassword.getText().toString();
        String email=userEmail.getText().toString();
        String eye=userEye.getText().toString();
        String age=userAge.getText().toString();
        String sex=userSex.getText().toString();
        String fieldtest=userFieldtest.getText().toString();
        String drops=userDrops.getText().toString();

        if(name.isEmpty() || Password.isEmpty() || email.isEmpty() || eye.isEmpty() || age.isEmpty() || sex.isEmpty() || fieldtest.isEmpty() || drops.isEmpty()){
            Toast.makeText(this, "please fill the details",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;

    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(register.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(register.this, first.class));
                    }else{
                        Toast.makeText(register.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(mAuth.getUid());
        StorageReference imageReference = storageReference.child(mAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg

        UserProfile userProfile = new UserProfile(age, email, name);
        myRef.setValue(userProfile);
    }
}



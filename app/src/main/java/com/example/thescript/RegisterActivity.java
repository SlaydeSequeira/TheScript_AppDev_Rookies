package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    // Widgets
    EditText userET, passET, emailET,city;
    Button registerBtn;
    RadioButton r1,r2;
    RadioGroup rg;
    String a;


    // Firebase
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Initializing Widgets:
        r1= findViewById(R.id.radio1);
        r2= findViewById(R.id.radio2);
        userET = findViewById(R.id.edittext1);
        passET = findViewById(R.id.edittext3);
        emailET = findViewById(R.id.edittext2);
        city = findViewById(R.id.edittext4);
        registerBtn = findViewById(R.id.loginbtn);

        rg = findViewById(R.id.radio);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                String r= String.valueOf(radioButton);
                if(r=="Customer")
                {
                    a="0";
                }
                else
                {
                    a="1";
                }
            }
        });



        // Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Adding Event Listener to Button Register
        registerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String username_text = userET.getText().toString();
                String email_text    = emailET.getText().toString();
                String pass_text     = passET.getText().toString();
                String c = city.getText().toString();
                if (TextUtils.isEmpty(username_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    RegisterNow(username_text, email_text , pass_text,c,a );
                }
            }
        });

    }

    private void RegisterNow(final String username, String email, String password,String c,String a){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstance().getReference("MyUsers")
                                    .child(userid);

                            // HashMaps
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("status" , "offline");
                            hashMap.put("city",c);
                            hashMap.put("role",a);
                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Intent i = new Intent(RegisterActivity.this, Middle.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });



                            // Opening the Main Activity after Success Registration


                        }else{
                            Toast.makeText(RegisterActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }



                    }
                });




    }
}
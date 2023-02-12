package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thescript.model.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class AddFood extends AppCompatActivity {

    String temp,temp1,temp2,temp10;
    EditText editText,editText2,editText3;
    Button button,button1;
    String c,a;
    int flag=0;
    ImageView imageView;
    String username;

    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        imageView = findViewById(R.id.image_view);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database.getReference("market").child("count");
        editText= findViewById(R.id.edit);
        editText2=findViewById(R.id.edit2);
        editText3=findViewById(R.id.unit);
        button = findViewById(R.id.submit_btn);
        button1= findViewById(R.id.buttonPanel);
  ;


        RadioGroup rg = findViewById(R.id.radio);
        rg = findViewById(R.id.radio);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                String r= String.valueOf(radioButton);
                if(r=="Veg")
                {
                    a="Veg";
                }
                else
                {
                    a="NonVeg";
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        FirebaseUser fuser;
                        DatabaseReference reference;
                        fuser = FirebaseAuth.getInstance().getCurrentUser();
                        temp10=editText3.getText().toString();
                        reference = FirebaseDatabase.getInstance().getReference("MyUsers")
                                .child(fuser.getUid());



                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Users user = dataSnapshot.getValue(Users.class);
                                username=(user.getUsername()).toString();

                                DatabaseReference myRef5 = database.getReference("market").child("provider");
                                HashMap<String, Object> hashMap5 = new HashMap<>();
                                hashMap5.put(c,username);
                                myRef5.updateChildren(hashMap5);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                        temp = String.valueOf(snapshot.getValue());
                        int count=Integer.parseInt(temp);
                        if(flag==1) {flag=0;
                            addcount(count);
                            uploadImage(count,imageUri);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void addcount(int count) {

        EditText edt= findViewById(R.id.exp);
        String t=edt.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database.getReference("market").child("count");
        DatabaseReference myRef1 = database.getReference("market").child("Title");
        DatabaseReference myRef3 = database.getReference("market").child("cost");
        DatabaseReference myRef4 = database.getReference("market").child("veg");
        DatabaseReference myRef5 = database.getReference("market").child("time");
        DatabaseReference myRef6 = database.getReference("market").child("unit");
        temp2= editText2.getText().toString().trim();
        temp1= editText.getText().toString().trim();
        HashMap<String, Object> hashMap = new HashMap<>();
        c=String.valueOf(count);
        hashMap.put(c , temp1);
        myRef1.updateChildren(hashMap);

        HashMap<String, Object> hashMap10 = new HashMap<>();
        hashMap10.put(c,temp10);
        myRef6.updateChildren(hashMap10);

        HashMap<String,Object> hashMap5 = new HashMap<>();
        hashMap5.put(c,t);
        myRef5.updateChildren(hashMap5);

        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put(c , temp2);
        myRef3.updateChildren(hashMap1);

        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put(c,a);
        myRef4.updateChildren(hashMap2);

        count++;
        myRef2.setValue(count);
        finish();
    }
    private void uploadImage(int count,Uri uri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName+".jpg");
        try {

            storageReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageView = findViewById(R.id.image_view);
                            imageView.setImageURI(imageUri);
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String a= uri.toString();
                                    Toast.makeText(AddFood.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef4 = database.getReference("market").child("image");

                                    c=String.valueOf(count);
                                    HashMap<String, Object> hashMap4 = new HashMap<>();
                                    hashMap4.put(c , a);
                                    myRef4.updateChildren(hashMap4);

                                    //I have no idea what it is but apparently app uploads has it
                                    // acc to me it just crashes the app
                                    // Note to self (slayde) see if it necessary
                                    // progressDialog.dismiss();



                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddFood.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Toast.makeText(AddFood.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
                        }
                    });
        }catch (Exception e){

        }
    }


    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){
            imageView = findViewById(R.id.image_view);
            imageUri = data.getData();
            imageView.setImageURI(imageUri);


        }
    }










}
package com.example.daddy.firebasenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button SubmitButton ;

    Button DisplayButton;

    EditText Name, Rate;
    private boolean isAdmin = false;

    LinearLayout llInsertData;

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://fir-e2f9c.firebaseio.com/";

    // Declaring String variables to store name & rate get from EditText.
    String NameHolder, RateHolder;

    Firebase firebase;

    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Product_Details_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llInsertData = findViewById(R.id.llInsertData);

        if(isAdmin){
            llInsertData.setVisibility(View.VISIBLE);
        }else{
            llInsertData.setVisibility(View.GONE);
        }

                Firebase.setAndroidContext(MainActivity.this);

                firebase = new Firebase(Firebase_Server_URL);

                databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

                SubmitButton = (Button)findViewById(R.id.submit);

                Name = (EditText)findViewById(R.id.name);

                Rate = (EditText)findViewById(R.id.rate);

                DisplayButton = (Button)findViewById(R.id.DisplayButton);

                SubmitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ProductDetails productDetails = new ProductDetails();

                        GetDataFromEditText();

                        // Adding name into class function object.
                        productDetails.setProductname(NameHolder);

                        // Adding rate into class function object.
                        productDetails.setProductrate(RateHolder);

                        // Getting the ID from firebase database.
                        String ProjectIDFromServer = databaseReference.push().getKey();

                        // Adding the both name and rate values using student details class object using ID.
                        databaseReference.child(ProjectIDFromServer).setValue(productDetails);

                        // Showing Toast message after successfully data submit.
                        Toast.makeText(MainActivity.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

                    }
                });

                DisplayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MainActivity.this, ShowProductDetails.class);
                        startActivity(intent);

                    }
                });

            }

            public void GetDataFromEditText(){

                NameHolder = Name.getText().toString().trim();

                RateHolder = Rate.getText().toString().trim();

            }
        }

package com.example.daddy.firebasenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class ShowProductDetails extends AppCompatActivity {

        DatabaseReference databaseReference;

        ProgressDialog progressDialog;

        List<ProductDetails> list = new ArrayList<>();

        RecyclerView recyclerView;

        RecyclerView.Adapter adapter ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_product_details);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(ShowProductDetails.this));

            progressDialog = new ProgressDialog(ShowProductDetails.this);

            progressDialog.setMessage("Loading Data from Firebase Database");

            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference(MainActivity.Database_Path);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        ProductDetails productDetails = dataSnapshot.getValue(ProductDetails.class);

                        list.add(productDetails);
                    }

                    adapter = new RecyclerViewAdapter(ShowProductDetails.this, list);

                    recyclerView.setAdapter(adapter);

                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    progressDialog.dismiss();

                }
            });

        }
    }
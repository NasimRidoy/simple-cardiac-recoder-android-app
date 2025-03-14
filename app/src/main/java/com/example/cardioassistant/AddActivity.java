package com.example.cardioassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    public TextView Systolic_pressure,Diastolic_pressure,Heart_rate,Measure_time,Measure_date,Comment;
    public Button Add_button;
    DatabaseReference database;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Initialization();
        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String systolic_pressureString = Systolic_pressure.getText().toString().trim();
                String Diastolic_pressureString = Diastolic_pressure.getText().toString().trim();
                String Heart_rateString = Heart_rate.getText().toString().trim();
                String Measure_timeString = Measure_time.getText().toString().trim();
                String Measure_dateString = Measure_date.getText().toString().trim();
                String CommentString = Comment.getText().toString().trim();

                if (systolic_pressureString.isEmpty()) {
                    Systolic_pressure.setError("field is required");
                    Systolic_pressure.requestFocus();
                    return;
                }
                else if(Integer.parseInt(systolic_pressureString) >= 300){
                    Systolic_pressure.setError("too large");
                    Systolic_pressure.requestFocus();
                    return;
                }

                else if (Diastolic_pressureString.isEmpty()) {
                    Diastolic_pressure.setError("field is required");
                    Diastolic_pressure.requestFocus();
                    return;
                }
                else if(Integer.parseInt(systolic_pressureString) >= 200)
                {
                    Diastolic_pressure.setError("too large");
                    Diastolic_pressure.requestFocus();
                    return;
                }

                else if (Heart_rateString.isEmpty()) {
                    Heart_rate.setError("field is required");
                    Heart_rate.requestFocus();
                    return;
                }

                else if (Measure_timeString.isEmpty()) {
                    Measure_time.setError("field is required");
                    Measure_time.requestFocus();
                    return;
                }

                else if (Measure_dateString.isEmpty()) {
                    Measure_date.setError("field is required");
                    Measure_date.requestFocus();
                    return;
                }

                else if (CommentString.isEmpty()) {
                    Comment.setError("field is required");
                    Comment.requestFocus();
                    return;
                }
                Integer Systolic_pressureNumber = Integer.parseInt(systolic_pressureString);
                Integer Diastolic_pressureNumber = Integer.parseInt(Diastolic_pressureString);
                Integer Heart_rateNumber = Integer.parseInt(Heart_rateString);

                Record record = new Record(Measure_dateString,Measure_timeString,Systolic_pressureNumber,Diastolic_pressureNumber,Heart_rateNumber,CommentString);
                database.push().setValue(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Added Successful", Toast.LENGTH_SHORT).show();
                        Intent HomeIntenet = new Intent(AddActivity.this,HomeActivity.class);
                        AddActivity.this.startActivity(HomeIntenet);
                    }
                });
            }
        });
    }
    private void Initialization() {
        Systolic_pressure = (TextView) findViewById(R.id.Add_Enter_Systolic_pressure);
        Diastolic_pressure = (TextView) findViewById(R.id.Add_Enter_Diastolic_pressure);
        Heart_rate = (TextView) findViewById(R.id.Add_Enter_Heart_Rate);
        Measure_time = (TextView) findViewById(R.id.ADD_Enter_Measure_Time);
        Measure_date = (TextView) findViewById(R.id.ADD_Enter_Measure_Date);
        Comment = (TextView) findViewById(R.id.ADD_Enter_Comment);

        Add_button = (Button) findViewById(R.id.ADD_ADDButton);

        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getUid();//.toString()
        database = FirebaseDatabase.getInstance().getReference("Record").child(userId);
    }
}
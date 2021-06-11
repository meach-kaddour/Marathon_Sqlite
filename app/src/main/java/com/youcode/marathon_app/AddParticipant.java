package com.youcode.marathon_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddParticipant extends AppCompatActivity {

    EditText editName,editAge,editCin,editPhone;
    Button btnConfirm;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);
        editName=findViewById(R.id.editName);
        editCin=findViewById(R.id.editCin);
        editAge=findViewById(R.id.editAge);
        editPhone=findViewById(R.id.editPhone);
        btnConfirm=findViewById(R.id.btnConfirm);

        db=new Database(this);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString();
                String cin=editCin.getText().toString();
                int  age=Integer.parseInt(editAge.getText().toString());
                int  phone=Integer.parseInt(editPhone.getText().toString());

                Participant participant=new Participant(name,age,cin,phone);

                db.insertData(participant);
                Toast.makeText(AddParticipant.this,"Participant Added",Toast.LENGTH_LONG).show();
            }
        });
    }
}
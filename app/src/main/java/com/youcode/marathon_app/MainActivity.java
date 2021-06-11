package com.youcode.marathon_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listParticipants;
    Button btnAdd;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listParticipants=findViewById(R.id.listParticipant);
        btnAdd=findViewById(R.id.btnAdd);
        db =new Database(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainActivity.this,AddParticipant.class);
                 startActivity(intent);
             }
         });
         listParticipants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Participant selectedParticipant= (Participant) parent.getItemAtPosition(position);
                 Intent intent=new Intent(MainActivity.this,UpdateParticipant.class);
                 intent.putExtra("keId",selectedParticipant.getId());
                 startActivity(intent);
             }
         });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Participant> participants=db.getAllUsers();

        ParticipantAdapter participantAdapter=new ParticipantAdapter(this,R.layout.item_participant,participants);
        listParticipants.setAdapter(participantAdapter);
    }
}
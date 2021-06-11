package com.youcode.marathon_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateParticipant extends AppCompatActivity {
    EditText editName,editCin,editAge,editPhone;
    Button btnUpdate;
    Database db;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_participant);

        btnUpdate=findViewById(R.id.btnUpdate);
        //get Id
        id=getIntent().getIntExtra("keId",0);

        db=new Database(this);

        Participant participant= db.getParticipantParId(id);

        editName=(EditText) findViewById(R.id.editName);
        editCin=(EditText)findViewById(R.id.editCin);
        editAge=(EditText)findViewById(R.id.editAge);
        editPhone=(EditText)findViewById(R.id.editPhone);

        editName.setText(participant.getName());
        editAge.setText(participant.getAge()+"");
        editCin.setText(participant.getCin());
        editPhone.setText(participant.getPhone()+"");


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString();
                String cin =editCin.getText().toString();
                int age=Integer.parseInt(editAge.getText().toString());
                int phone=Integer.parseInt(editPhone.getText().toString());
                Participant participant1=new Participant(id,name,age,cin,phone);
                db.updateParticipant(participant1);
                Toast.makeText(UpdateParticipant.this,"Participant Updated",Toast.LENGTH_LONG).show();



            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_delete:
                showAlert();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder alertBuilder= new AlertDialog.Builder(this);
        alertBuilder.setTitle("Confirmtion")
                .setMessage("Are you sure ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete participant    from db
                        db.deleteParticipant(id);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog= alertBuilder.create();
        dialog.show();

    }
}
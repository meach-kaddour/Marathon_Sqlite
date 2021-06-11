package com.youcode.marathon_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database  extends SQLiteOpenHelper {

    private static final String TABLE_USERS = "USERS";
    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CIN = "cin";
    private static final String KEY_AGE = "age";
    private static final String KEY_PHONE = "phone";

    ///////////////////////////////////////////////////////////////////
    // Creating table query
    private static final String CREATE_TABLE = "create table "+TABLE_USERS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT" +
            ","+KEY_NAME+" TEXT NOT NULL,"+KEY_CIN+" TEXT NOT NULL,"+KEY_AGE+" integer NOT NULL ,"+KEY_PHONE+" integer NOT NULL) ";
    ///////////////////////////////////////////
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    //////////////CRUD  OPERATIONS///////////////////////
////INSERTION////
    public void insertData(Participant participant) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        Values.put(KEY_NAME, participant.getName());
        Values.put(KEY_CIN, participant.getCin());
        Values.put(KEY_AGE,participant.getAge());
        Values.put(KEY_PHONE,participant.getPhone());

        db.insert(TABLE_USERS, null, Values);
    }
///////////////GET ALL RECORDS
public ArrayList<Participant> getAllUsers(){
    ArrayList participants= new ArrayList();
    String select_query="select * from "+TABLE_USERS;
    SQLiteDatabase db =this.getReadableDatabase();
    Cursor cursorResponse = db.rawQuery(select_query, null) ;

        if (cursorResponse.moveToFirst()) {
            do {
                String name = cursorResponse.getString(cursorResponse.getColumnIndex(KEY_NAME));
                String cin = cursorResponse.getString(cursorResponse.getColumnIndex(KEY_CIN));
                int age = cursorResponse.getInt(cursorResponse.getColumnIndex(KEY_AGE));
                int phone = cursorResponse.getInt(cursorResponse.getColumnIndex(KEY_PHONE));
                Participant participant = new Participant(name, age, cin, phone);
                participants.add(participant);

            } while (cursorResponse.moveToNext());
        }

    return participants;
}
//////////////////
    public Participant getParticipantParId(int id){
/*
        String select_query="select * from "+TABLE_USERS+" where id="+id;
        SQLiteDatabase db =this.getReadableDatabase();
         Cursor cursor=db.rawQuery(select_query,null);
         Participant participant=null;
         if (cursor.moveToFirst()){
             int id_part=cursor.getInt(cursor.getColumnIndex(KEY_ID));
             String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
             String cin = cursor.getString(cursor.getColumnIndex(KEY_CIN));
             int age = cursor.getInt(cursor.getColumnIndex(KEY_AGE));
             int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
             participant=new Participant(id_part,name,age,cin,phone);
         }*/
          Participant participant=null;
          SQLiteDatabase db =this.getReadableDatabase();
          Cursor cursor = db.query(TABLE_USERS,new String[]{"id","name","cin","age","phone",}, "id=?",
                  new String[]{String.valueOf(id)},null,null,null
                  );
        if (cursor.moveToFirst()){
            int id_part=cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String cin = cursor.getString(cursor.getColumnIndex(KEY_CIN));
            int age = cursor.getInt(cursor.getColumnIndex(KEY_AGE));
            int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
            participant=new Participant(id_part,name,age,cin,phone);
        }

         return participant;
    }
    /////////Update
    public void updateParticipant(Participant participant){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, participant.getName());
        values.put(KEY_CIN, participant.getCin());
        values.put(KEY_AGE,participant.getAge());
        values.put(KEY_PHONE,participant.getPhone());

        db.update(TABLE_USERS,values,"id=?",new String[]{String.valueOf(participant.getId())});

    }

    //////////////////Delete

    public void deleteParticipant(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USERS,"id=?",new String[]{String.valueOf(id)});
    }


}

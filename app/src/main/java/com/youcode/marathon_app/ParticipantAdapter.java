package com.youcode.marathon_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ParticipantAdapter  extends ArrayAdapter<Participant> {
Context context;
int resource;
    public ParticipantAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Participant> objects) {
        super(context, resource,objects);
        this.context=context;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(resource,parent,false);

        TextView tvName=convertView.findViewById(R.id.tvName);
        TextView tvAge=convertView.findViewById(R.id.tvAge);
        TextView tvCin=convertView.findViewById(R.id.tvCin);
        TextView tvPhone=convertView.findViewById(R.id.tvPhone);
        Participant currentParticipant=getItem(position);
        tvName.setText(currentParticipant.getName());
        tvAge.setText(String.valueOf(currentParticipant.getAge())+"Ans");
        tvCin.setText(currentParticipant.getCin());
        tvPhone.setText(String.valueOf(currentParticipant.getPhone()));

        return convertView;
    }
}

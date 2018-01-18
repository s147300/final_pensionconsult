package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class exampleAdapter extends ArrayAdapter<Example> implements ValueEventListener {

    public exampleAdapter(@NonNull Context context) {
        super(context, -1, new ArrayList<Example>());

        DatabaseReference articleRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pensionconsult-84ffd.firebaseio.com/example");
        articleRef.addValueEventListener(this);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Example example = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.example_item_view, parent, false);

        TextView articleTitle = rowView.findViewById(R.id.textViewTitle);
        TextView articleDate = rowView.findViewById(R.id.textViewDate);
        ImageView articlePicture = rowView.findViewById(R.id.imageViewPic);

        String url = example.url;
        articleTitle.setText(example.title);
        articleDate.setText(example.date);


        return rowView;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        this.clear();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            this.add(child.getValue(Example.class));
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("Failed to retrieve Database data!");
        System.out.println(databaseError.getMessage());
    }
}

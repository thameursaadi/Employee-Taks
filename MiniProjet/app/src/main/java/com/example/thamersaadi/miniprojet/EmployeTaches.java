package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeTaches extends AppCompatActivity {

    DatabaseReference dababaseTaches;

    ListView listViewTaches;

    List<Tache> tacheList;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_taches);
        Intent incomingIntent = getIntent();
        id= incomingIntent.getStringExtra("Employee_Id");
        tacheList= new ArrayList<>();

        dababaseTaches= FirebaseDatabase.getInstance().getReference("taches");


        listViewTaches=(ListView)findViewById(R.id.listViewTache);

    }

    @Override
    protected void onStart() {
        super.onStart();
        dababaseTaches.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tacheList.clear();
                for (DataSnapshot tacheSnapShot : dataSnapshot.getChildren())
                {
                    Tache tache = tacheSnapShot.getValue(Tache.class);

                    if (tache.idEmploye.equals(id))
                    {
                        tacheList.add(tache);
                    }
                }
                TacheList adaptater =new TacheList(EmployeTaches.this,tacheList);
                listViewTaches.setAdapter(adaptater);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

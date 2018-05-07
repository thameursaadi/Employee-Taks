package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AjouterTache extends AppCompatActivity {

    String idEmp;
    EditText editTextTache;
    Button btnAdd;

    DatabaseReference dababaseTaches;

    ListView listViewTaches;

    List<Tache>tacheList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_tache);

        Intent incomingIntent = getIntent();
        idEmp= incomingIntent.getStringExtra("Employee_Id");

        dababaseTaches= FirebaseDatabase.getInstance().getReference("taches");

        editTextTache=(EditText)findViewById(R.id.txtdesc);
        btnAdd=(Button)findViewById(R.id.btnadd);
        listViewTaches=(ListView)findViewById(R.id.listViewTaches);

        tacheList=new ArrayList<>();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTache();
            }
        });
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

                    if (tache.idEmploye.equals(idEmp))
                    {
                        tacheList.add(tache);
                    }
                }
                TacheList adaptater =new TacheList(AjouterTache.this,tacheList);
                listViewTaches.setAdapter(adaptater);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private  void  addTache()
    {
        String description = editTextTache.getText().toString();
        if (!TextUtils.isEmpty(description))
        {
            String id = dababaseTaches.push().getKey();
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            Tache tache = new Tache(id,idEmp,description,false,currentDateTimeString);
            dababaseTaches.child(id).setValue(tache);
            Toast.makeText(this,"Tache effectué",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"veuillez entrer la tache a effectuer",Toast.LENGTH_LONG).show();
        }
    }
}

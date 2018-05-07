package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeDetails extends AppCompatActivity {

    TextView tNom,tPrenom,tEmail,tDepartement,tSexe,tTelephone,tPW,tCin;
    String id="",nom="",prenom="",email="",departement ="",sexe="",telephone="",pw="",cin="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_details);

        tNom=(TextView)findViewById(R.id.AfficheNom);
        tPrenom=(TextView)findViewById(R.id.AffichePrenom);
        tEmail=(TextView)findViewById(R.id.AfficheEmail);
        tDepartement=(TextView)findViewById(R.id.AfficheDep);
        tSexe=(TextView)findViewById(R.id.AfficheSexe);
        tTelephone=(TextView)findViewById(R.id.AfficheTel);
        tPW=(TextView)findViewById(R.id.AffichePW);
        tCin=(TextView)findViewById(R.id.AfficheCin);


        Intent incomingIntent = getIntent();
        id= incomingIntent.getStringExtra("Employee_Id");
        nom= incomingIntent.getStringExtra("Employee_Nom");
        prenom= incomingIntent.getStringExtra("Employee_Prenom");
        email= incomingIntent.getStringExtra("Employee_Email");
        departement= incomingIntent.getStringExtra("Employee_Dep");
        sexe= incomingIntent.getStringExtra("Employee_Sexe");
        telephone= incomingIntent.getStringExtra("Employee_Telephone");
        pw= incomingIntent.getStringExtra("Employee_PW");
        cin= incomingIntent.getStringExtra("Employee_Cin");

        tNom.setText(nom);
        tPrenom.setText(prenom);
        tEmail.setText(email);
        tDepartement.setText(departement);
        tSexe.setText(sexe);
        tTelephone.setText(telephone);
        tCin.setText(cin);
        tPW.setText(pw);
    }

    public void btnAppeler_Click(View view) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        dialIntent.setData(Uri.parse("tel:"+telephone));
        // If package resolves to an app, send intent.
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {

        }
    }

    public void btnMessage_Click(View view) {
    }

    public void btnSupprimer_Click(View view) {
        DatabaseReference dbEmployee= FirebaseDatabase.getInstance().getReference("employees").child(id);
        dbEmployee.removeValue();
        Toast.makeText(this,"Employee supprimé",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),IndexEmployee.class);
        startActivity(intent);
    }

    public void btnEdit_Click(View view) {
        Intent intent = new Intent(getApplicationContext(),ModifiereMPLOYEE.class);
        intent.putExtra("Employee_Id",id);
        intent.putExtra("Employee_Nom",nom);
        intent.putExtra("Employee_Prenom",prenom);
        intent.putExtra("Employee_Email",email);
        intent.putExtra("Employee_Telephone",telephone+"");
        intent.putExtra("Employee_PW",pw);
        intent.putExtra("Employee_Dep",departement);
        intent.putExtra("Employee_Sexe",sexe);
        intent.putExtra("Employee_Cin",cin+"");
        startActivity(intent);
    }

    public void btnTache_Click(View view) {
        Intent intent = new Intent(getApplicationContext(),AjouterTache.class);
        intent.putExtra("Employee_Id",id);
        startActivity(intent);
    }
}

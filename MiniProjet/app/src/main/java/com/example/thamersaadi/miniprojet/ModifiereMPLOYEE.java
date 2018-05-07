package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifiereMPLOYEE extends AppCompatActivity {
    EditText tNom,tPrenom,tEmail,tTelephone,tPW,tCin;
    Spinner tDepartement,tSexe;
    private TextView txtdate;
    String id="",nom="",prenom="",email="",departement ="",sexe="",telephone="",pw="",cin="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifiere_mployee);

        tNom=(EditText) findViewById(R.id.txtnom);
        tPrenom=(EditText) findViewById(R.id.txtprenom);
        tEmail=(EditText) findViewById(R.id.txtemail);
        tDepartement=(Spinner) findViewById(R.id.txtdepart);
        tSexe=(Spinner)findViewById(R.id.txtsexe);
        tTelephone=(EditText) findViewById(R.id.txttel);
        tPW=(EditText) findViewById(R.id.txtpw);
        tCin=(EditText) findViewById(R.id.txtcin);
        txtdate=(TextView)findViewById(R.id.date);

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
        tDepartement.setSelection(1);
        tSexe.setSelection(1);
        tTelephone.setText(telephone);
        tCin.setText(cin);
        tPW.setText(pw);

    }

    public void btnModifier_Click(View view) {
        int tel=0,cin=0;
        String nom=tNom.getText().toString();
        String prenom=tPrenom.getText().toString();
        String date = txtdate.getText().toString();
        String sexe = tSexe.getSelectedItem().toString();
        try {

            cin=Integer.parseInt(tCin.getText().toString());
        }
        catch(NumberFormatException nfe) {

        }
        String email=tEmail.getText().toString();
        try {

            tel=Integer.parseInt(tTelephone.getText().toString());
        }
        catch(NumberFormatException nfe) {

        }
        String pw =tPW.getText().toString();
        String dep = tDepartement.getSelectedItem().toString();

        //Toast.makeText(this,employee.toString(),Toast.LENGTH_LONG).show();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("employees").child(id);
        Employee employee = new Employee(id,tel,nom,sexe,prenom,email,date,cin,pw,dep);
        databaseReference.setValue(employee);
        Toast.makeText(this,"Employé modifié",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),IndexEmployee.class);
        startActivity(intent);
    }
}

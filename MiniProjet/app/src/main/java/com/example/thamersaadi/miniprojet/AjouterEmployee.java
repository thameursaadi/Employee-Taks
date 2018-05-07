package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by thamersaadi on 02/04/2018.
 */

public class AjouterEmployee extends AppCompatActivity {
    private static final String TAG = "AjouterEmployee";
    private TextView txtdate;
    private ImageView btnimage;
    byte[] imagesource;
    private EditText txtnom,txtprenom,txtcin,txttel,txtemail,txtpw;
    private Spinner txtDepartement,txtGenre;
    private static final int pickImage=100;
    private Button btnValider;
    Uri imageUri;
    DatabaseReference databaseEmployee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouteremployee);

        databaseEmployee= FirebaseDatabase.getInstance().getReference("employees");

        txtnom=(EditText)findViewById(R.id.txtnom);
        txtprenom=(EditText)findViewById(R.id.txtprenom);
        txttel=(EditText)findViewById(R.id.txttel);
        txtDepartement=(Spinner) findViewById(R.id.txtdepart);
        txtGenre=(Spinner) findViewById(R.id.txtsexe);
        txtcin=(EditText)findViewById(R.id.txtcin);
        txtemail=(EditText)findViewById(R.id.txtemail);
        txtdate=(TextView)findViewById(R.id.date);
        btnimage=(ImageView)findViewById(R.id.imgprofil);
        txtpw=(EditText)findViewById(R.id.txtpw);
        btnValider=(Button)findViewById(R.id.btnvalider);
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        if (date==null)
        {
            txtdate.setText("jj/mm/aaa");
        }
        else
        {
            txtdate.setText(date);
        }


        String  uniqueID = UUID.randomUUID().toString();
        String chpw = uniqueID.substring(0,9);
        txtpw.setText(chpw);

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AjouterEmployee.this,CalendarActivity.class);
                startActivity(intent);
            }
        });

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
openGllery();
            }
        });
        btnValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    private  void  addEmployee()
    {
        int tel=0,cin=0;
        String nom=txtnom.getText().toString();
        String prenom=txtnom.getText().toString();
        String date = txtdate.getText().toString();
        String sexe = txtGenre.getSelectedItem().toString();
        try {

            cin=Integer.parseInt(txtcin.getText().toString());
        }
        catch(NumberFormatException nfe) {

        }
        String email=txtnom.getText().toString();
        try {

            tel=Integer.parseInt(txttel.getText().toString());
        }
        catch(NumberFormatException nfe) {

        }
        String pw =txtpw.getText().toString();
        String dep = txtDepartement.getSelectedItem().toString();
        String id = databaseEmployee.push().getKey();
        Employee employee = new Employee(id,tel,nom,sexe,prenom,email,date,cin,pw,dep);
        //Toast.makeText(this,employee.toString(),Toast.LENGTH_LONG).show();
        try {
            databaseEmployee.child(id).setValue(employee);
        } catch(Exception ex) {
            System.out.println( "******************************");
            System.out.println( ex.getMessage());
        }

        Toast.makeText(this,"Employee Added",Toast.LENGTH_LONG).show();
    }

    private  void openGllery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,pickImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==pickImage)
        {
            imageUri=data.getData();
            btnimage.setImageURI(imageUri);

            try {
                InputStream iStream =   getContentResolver().openInputStream(imageUri);
                imagesource = getBytes(iStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(AjouterEmployee.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


}

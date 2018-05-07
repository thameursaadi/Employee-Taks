package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    DatabaseReference databaseEmmployees;
    List<Employee> employeeList;
    EditText tmail,tpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseEmmployees= FirebaseDatabase.getInstance().getReference("employees");
        tmail=(EditText)findViewById(R.id.loginmail);
        tpw=(EditText)findViewById(R.id.loginpw);
        employeeList= new ArrayList<>();

    }

    public void btnSeConnecter_Click(View view)
    {
        if (tmail.getText().toString().equals("test") && tpw.getText().toString().equals("test"))
        {
            Intent intent =new Intent(Login.this,HomeAdmin.class);
            startActivity(intent);
        }
        else
        {databaseEmmployees.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                employeeList.clear();
                int i=0;
                for (DataSnapshot employeeSnapshot : dataSnapshot.getChildren())
                {
                    // Artist artist = postSnapshot.getValue(Artist.class);
                    try {
                        Employee employee = employeeSnapshot.getValue(Employee.class);
                        if (tmail.getText().toString().equals(employee.email) && tpw.getText().toString().equals(employee.motDePasse))
                            employeeList.add(employee);
                    }
                    catch(Exception ex) {
                        System.out.println( "******************************");
                        System.out.println( ex.getMessage());
                    }
                }
                if (employeeList.size()>0)
                {
                    Intent intent =new Intent(Login.this,EmployeTaches.class);
                    intent.putExtra("Employee_Id",employeeList.get(0).Id);
                    startActivity(intent);

                }
                //EmployeeList adapter = new EmployeeList(IndexEmployee.this,employeeList);
                //listViewEmployees.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    }
}

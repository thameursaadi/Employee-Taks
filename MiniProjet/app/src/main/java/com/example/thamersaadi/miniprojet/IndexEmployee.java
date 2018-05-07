package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexEmployee extends AppCompatActivity {

    ListView listViewEmployees;
    DatabaseReference databaseEmmployees;

    List<Employee> employeeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_employee);
        databaseEmmployees= FirebaseDatabase.getInstance().getReference("employees");

        listViewEmployees = (ListView)findViewById(R.id.listViewEmployees);

        employeeList=new ArrayList<>();

        listViewEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee selectedEmployee =employeeList.get(position);
                Intent intent = new Intent(getApplicationContext(),EmployeDetails.class);
                intent.putExtra("Employee_Id",selectedEmployee.Id);
                intent.putExtra("Employee_Nom",selectedEmployee.nom);
                intent.putExtra("Employee_Prenom",selectedEmployee.prenom);
                intent.putExtra("Employee_Email",selectedEmployee.email);
                intent.putExtra("Employee_Telephone",selectedEmployee.numeroTelephone+"");
                intent.putExtra("Employee_PW",selectedEmployee.motDePasse);
                intent.putExtra("Employee_Dep",selectedEmployee.departement);
                intent.putExtra("Employee_Sexe",selectedEmployee.sexe);
                intent.putExtra("Employee_Cin",selectedEmployee.cin+"");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseEmmployees.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                employeeList.clear();
                for (DataSnapshot employeeSnapshot : dataSnapshot.getChildren())
                {
                   // Artist artist = postSnapshot.getValue(Artist.class);
                    try {
                        Employee employee = employeeSnapshot.getValue(Employee.class);
                        employeeList.add(employee);
                    }
                    catch(Exception ex) {
                        System.out.println( "******************************");
                        System.out.println( ex.getMessage());
                    }

                }
                EmployeeList adapter = new EmployeeList(IndexEmployee.this,employeeList);
                listViewEmployees.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

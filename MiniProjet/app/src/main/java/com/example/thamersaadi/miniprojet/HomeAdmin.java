package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    public void btnChercher_Click(View view) {
        Intent intent =new Intent(HomeAdmin.this,HomeAdmin.class);
        startActivity(intent);
    }

    public void btnListe_Click(View view) {
        Intent intent =new Intent(HomeAdmin.this,IndexEmployee.class);
        startActivity(intent);
    }

    public void btnAjouter_Click(View view) {
        Intent intent =new Intent(HomeAdmin.this,AjouterEmployee.class);
        startActivity(intent);
    }

    public void btnOut_Click(View view) {
        Intent intent =new Intent(HomeAdmin.this,Login.class);
        startActivity(intent);
    }

    public void btnStat_Click(View view) {
        Intent intent =new Intent(HomeAdmin.this,StatistiqueEmploye.class);
        startActivity(intent);
    }
}

package com.example.thamersaadi.miniprojet;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thamersaadi on 20/04/2018.
 */

public class EmployeeList extends ArrayAdapter<Employee> {
    private Activity context;
    private List<Employee> employeeList;
    public EmployeeList(Activity context, List<Employee> employeeList)
    {
        super(context,R.layout.list_layout,employeeList);
        this.context=context;
        this.employeeList=employeeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater infalter = context.getLayoutInflater();
        View listViewItem=infalter.inflate(R.layout.list_layout,null,true);
        TextView textViewNom=(TextView)listViewItem.findViewById(R.id.textViewNom);
        TextView textViewPrenom=(TextView)listViewItem.findViewById(R.id.textViewPrenom);
        TextView textViewTelephone=(TextView)listViewItem.findViewById(R.id.textViewTel);

        Employee e = employeeList.get(position);

        textViewNom.setText(e.nom);
        textViewPrenom.setText(e.prenom);
        textViewTelephone.setText(e.numeroTelephone+"");

        return  listViewItem;
    }
}

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
 * Created by thamersaadi on 22/04/2018.
 */

public class TacheList extends ArrayAdapter<Tache> {
    private Activity context;
    private List<Tache>tacheList;
    public TacheList(Activity context,List<Tache>tacheList)
    {
        super(context,R.layout.tache_layout,tacheList);
        this.context=context;
        this.tacheList=tacheList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.tache_layout,null,true);
        TextView textViewDes=(TextView)listViewItem.findViewById(R.id.textViewDescription);
        TextView textViewDate=(TextView)listViewItem.findViewById(R.id.textViewDate);
        TextView textViewValid=(TextView)listViewItem.findViewById(R.id.textViewValide);

        Tache tache = tacheList.get(position);
        textViewDes.setText(tache.description);
        textViewDate.setText(tache.date);
        textViewValid.setText(tache.valide+"");

        return listViewItem;
    }
}

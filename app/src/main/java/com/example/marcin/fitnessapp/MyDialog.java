package com.example.marcin.fitnessapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Bartosz on 2015-03-04.
 */
public class MyDialog extends DialogFragment implements View.OnClickListener {
    Button yes, no;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view, null);
        yes=(Button)view.findViewById(R.id.btOk);
        no=(Button)view.findViewById(R.id.btCancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        setCancelable(false);
        return view;

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btOk){
            Toast.makeText(getActivity(), "Kliknieto tak!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(), "kliknięto nie!", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.nataly.finalbabytip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.nataly.finalbabytip.data.BabyTipContract;
import com.example.nataly.finalbabytip.data.BabyTipData;

import java.util.ArrayList;
import java.util.List;

import model.Baby;

public class LoginActivity extends AppCompatActivity {

    private BabyTipData data;
    private Spinner list;
    private List<Integer> ids;
    private List<String> values;
    private CheckBox chk_terminos;
    private  List<Baby> bebes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        chk_terminos =(CheckBox)findViewById(R.id.chk_terminos);
        data  = new BabyTipData(this);
        bebes = data.getAllBabys();
        values = new ArrayList<String>();
        ids = new ArrayList<Integer>();

        for(int i=0;i<bebes.size();i++){
            values.add(bebes.get(i).getName());
            ids.add(bebes.get(i).getId_baby());
        }

        list = (Spinner) findViewById(R.id.sp_nombre);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,values);
        // ArrayAdapter<Baby> adapter= new MyListAdapter();
        list.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void Ingresar(View view) {
        boolean isTerminosAccepted = chk_terminos.isChecked();

        if (isTerminosAccepted && list.getSelectedItem() != null) {
            int id_baby = bebes.get(list.getSelectedItemPosition()).getId_baby();
            Intent intent_home = new Intent(this, HomeActivity.class);
            intent_home.putExtra(BabyTipContract.BabysEntry._ID,id_baby);
            intent_home.putExtra("selected_index",list.getSelectedItemPosition());
            startActivity(intent_home);
        }
        else if (!isTerminosAccepted)
           showMessage("Debes aceptar los términos y condiciones");


    }

    public void nuevoBebe(View view) {
        Intent intent_new_baby = new Intent(this, NewBabyActivity.class) ;
        startActivity(intent_new_baby);
    }
    public void showMessage(String message){
        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("BabytipApp");
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // show it
        alertDialog.show();
    }
}

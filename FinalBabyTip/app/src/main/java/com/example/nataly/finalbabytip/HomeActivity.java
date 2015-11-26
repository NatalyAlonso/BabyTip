package com.example.nataly.finalbabytip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.nataly.finalbabytip.data.BabyTipContract;
import com.example.nataly.finalbabytip.data.BabyTipData;

import java.util.ArrayList;
import java.util.List;

import model.Baby;
import model.BitmapEditor;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView img_foto;
    private BabyTipData data;
    private Baby bebe;
    private Bitmap bitmap;
    private Spinner sp_names;
    private  List<Baby> bebes;
    private List<String> values;
    private Spinner list;
    private List<Integer> ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        data = new BabyTipData(getApplicationContext());
        img_foto = (ImageView  )findViewById(R.id.img_foto);
        sp_names = (Spinner)findViewById(R.id.sp_seleccionar);


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
        } else {
            int id_baby = extras.getInt(BabyTipContract.BabysEntry._ID);
            int selected_index = extras.getInt("selected_index");
            data = new BabyTipData(this);
            bebe=data.getBabyById(id_baby);

            if (bebe.getPicture() != null) {
                bitmap = BitmapFactory.decodeFile(bebe.getPicture());
            }
            else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bebe_peque);
            }
            bitmap = Bitmap.createScaledBitmap(bitmap, BitmapEditor.IMAGE_WIDTH, BitmapEditor.getHeight(bitmap.getWidth(), bitmap.getHeight()), false);
            bitmap = BitmapEditor.getRoundedCornerBitmap(bitmap,false);
            img_foto.setImageBitmap(bitmap);

            bebes = data.getAllBabys();
            values = new ArrayList<String>();
            ids = new ArrayList<Integer>();

            for(int i=0;i<bebes.size();i++){
                values.add(bebes.get(i).getName());
                ids.add(bebes.get(i).getId_baby());
            }

            list = (Spinner) findViewById(R.id.sp_seleccionar);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,values);
            // ArrayAdapter<Baby> adapter= new MyListAdapter();
            list.setAdapter(adapter);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_names.setSelection(selected_index);
            sp_names.setOnItemSelectedListener(this);



        }

    }

    public void ViewProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(BabyTipContract.BabysEntry._ID, bebe.getId_baby());
        intent.putExtra("selected_index",sp_names.getSelectedItemPosition());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int id_baby = bebes.get(list.getSelectedItemPosition()).getId_baby();
        bebe = data.getBabyById(id_baby);
        if (bebe.getPicture() != null) {
            bitmap = BitmapFactory.decodeFile(bebe.getPicture());
        }
        else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bebe_peque);
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, BitmapEditor.IMAGE_WIDTH, BitmapEditor.getHeight(bitmap.getWidth(), bitmap.getHeight()), false);
        bitmap = BitmapEditor.getRoundedCornerBitmap(bitmap,false);
        img_foto.setImageBitmap(bitmap);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void nuevoBebe(View view) {
        Intent intent_new_baby = new Intent(this, NewBabyActivity.class) ;
        startActivity(intent_new_baby);
    }
}

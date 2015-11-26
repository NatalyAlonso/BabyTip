package com.example.nataly.finalbabytip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.nataly.finalbabytip.data.BabyTipContract;
import com.example.nataly.finalbabytip.data.BabyTipData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Baby;
import model.BitmapEditor;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton btnAgregar;
    private AutoCompleteTextView txt_nombre;
    private AutoCompleteTextView txt_peso;
    private AutoCompleteTextView txt_estatura;
    private DatePicker dp_fecha_nacimiento;

    private ImageView img_foto;
    private BabyTipData data;
    private Baby bebe;
    private Bitmap bitmap;
    String foto;
    String fecha_nac;
    Bitmap img;
    int selected_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnAgregar = (FloatingActionButton)findViewById(R.id.btn_agregar);
        txt_nombre = (AutoCompleteTextView)findViewById(R.id.txt_nombre);
        txt_peso = (AutoCompleteTextView)findViewById(R.id.txt_peso);
        txt_estatura= (AutoCompleteTextView)findViewById(R.id.txt_estatura);
        dp_fecha_nacimiento = (DatePicker)findViewById(R.id.dp_fecha_nac);

        data = new BabyTipData(getApplicationContext());
        img_foto = (ImageView)findViewById(R.id.image_toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
        } else {
            int id_baby = extras.getInt(BabyTipContract.BabysEntry._ID);
            selected_index = extras.getInt("selected_index");
            data = new BabyTipData(this);
            bebe=data.getBabyById(id_baby);

            if (bebe.getPicture() != null) {
                bitmap = BitmapFactory.decodeFile(bebe.getPicture());
                foto = bebe.getPicture();
            }
            else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bebe_peque);
            }

            img_foto.setImageBitmap(bitmap);

            txt_nombre.setText(bebe.getName());
            txt_estatura.setText(bebe.getEstatura()+"");
            txt_peso.setText(bebe.getPeso()+"");
            String [] date_values = bebe.getFecha_nacimiento().split("-");
            dp_fecha_nacimiento.updateDate(Integer.parseInt(date_values[2]), Integer.parseInt(date_values[1])-1, Integer.parseInt(date_values[0]));
        }


        img_foto.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_agregar:
                guardarBebe();
                break;
            case R.id.image_toolbar:
                selecionarFoto();
                break;
        }
    }

    private void guardarBebe(){
        boolean isValidBaby = true;

        String message = "";

        if (isValidName(txt_nombre.getText().toString()))
            bebe.setName(txt_nombre.getText().toString());
        else {
            isValidBaby = false;
            message += "El nombre no puede estar vacio" + Html.fromHtml("<br />");
        }
        if (isValidNumber(txt_peso.getText().toString()))
            bebe.setPeso(Integer.parseInt(txt_peso.getText().toString()));
        else {
            isValidBaby = false;
            message += "Peso debe ser un n"+Html.fromHtml("&uacute;")+"mero" + Html.fromHtml("<br />");
        }
        if (isValidNumber(txt_estatura.getText().toString()))
            bebe.setEstatura(Integer.parseInt(txt_estatura.getText().toString()));
        else {
            isValidBaby = false;
            message += "Estatura debe ser un n"+Html.fromHtml("&uacute;")+"mero" + Html.fromHtml("<br />");
        }
        if (foto != null && !foto.isEmpty())
            bebe.setPicture(foto);
        else {

        }
        long millis_fecha_nac = dp_fecha_nacimiento.getCalendarView().getDate();
        Date date_fecha_nac = new Date(millis_fecha_nac);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        fecha_nac = formatter.format(date_fecha_nac);
        bebe.setFecha_nacimiento(fecha_nac);
        if (isValidBaby) {


            if(img == null)
            {}
            else {
                FileOutputStream out = null;
                String filename = "" + bebe.getId_baby();
                (new File(getFilesDir().getPath() + "/babytip")).mkdirs();
                File file = new File(getFilesDir().getPath() + "/babytip", "" + bebe.getId_baby());
                try {
                    out = new FileOutputStream(file);
                    img.compress(Bitmap.CompressFormat.JPEG, 50, out); // bmp is your Bitmap instance
                    foto = file.getAbsolutePath();
                    bebe.setPicture(foto);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }


            data.updateBaby(bebe);
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(BabyTipContract.BabysEntry._ID, bebe.getId_baby());
            intent.putExtra("selected_index",selected_index);
            startActivity(intent);
            finish();
        }
        else
            showMessage(message);

    }
    private static int RESULT_LOAD_IMAGE = 1;
    private void selecionarFoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImageUri = data.getData();
            String picturePath = getPath(selectedImageUri);


            try {
                // Bitmap bitmap =BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                ImageView imgView = (ImageView) findViewById(R.id.image_toolbar);
                // Set the Image in ImageView after decoding the String
                img = BitmapFactory.decodeFile(picturePath);



                if (img.getWidth() >= img.getHeight()){

                    img = Bitmap.createBitmap(
                            img ,
                            img .getWidth()/2 - img .getHeight()/2,
                            0,
                            img .getHeight(),
                            img .getHeight()
                    );

                }else{

                    img  = Bitmap.createBitmap(
                            img ,
                            0,
                            img .getHeight()/2 - img .getWidth()/2,
                            img .getWidth(),
                            img .getWidth()
                    );
                }

                img = Bitmap.createScaledBitmap(img, BitmapEditor.IMAGE_COMPLETE_WIDTH, BitmapEditor.getCompleteHeight(img.getWidth(),img.getHeight()),false);
                imgView.setImageBitmap(img);

                // PNG is a lossless format, the compression factor (100) is ignored



            }
            catch (OutOfMemoryError e){
                e.printStackTrace();
                showMessage("La imagen es muy grande, selecciona una imagen mas pequeña");

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public boolean isValidNumber(String string){
        if(string == null || string.isEmpty())
            return false;
        int i = 0;
        for (; i< string.length(); i++)
            if (!Character.isDigit(string.charAt(i)))
                return false;
        return true;
    }
    public boolean isValidName(String name){
        if (name == null || name.isEmpty())
            return false;
        else
            return true;
    }
    public void showMessage(String message){
        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(ProfileActivity.this);


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

    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

}

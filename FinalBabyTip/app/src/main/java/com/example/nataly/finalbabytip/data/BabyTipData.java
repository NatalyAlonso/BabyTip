package com.example.nataly.finalbabytip.data;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import model.Baby;

/**
 * Created by Nataly on 17/11/2015.
 */
public class BabyTipData {
    private Context context;
    public BabyTipData (Context context){
        this.context= context;
    }
    public int addBaby (Baby baby){
        ContentValues values = new ContentValues();
        values.put(BabyTipContract.BabysEntry.KEY_NAME,baby.getName());
        values.put(BabyTipContract.BabysEntry.KEY_PICTURE,baby.getPicture());
        values.put(BabyTipContract.BabysEntry.KEY_AGE,baby.getAge());
        values.put(BabyTipContract.BabysEntry.KEY_PESO,baby.getPeso());
        values.put(BabyTipContract.BabysEntry.KEY_ESTATURA,baby.getEstatura());
        values.put(BabyTipContract.BabysEntry.KEY_FECHA_NAC,baby.getFecha_nacimiento());
        Uri b = context.getContentResolver().insert(BabyTipContract.BabysEntry.CONTENT_URI, values);
        List<String> path = b.getPathSegments();
        int _id = Integer.parseInt(path.get(path.size()-1));
        return _id;
    }
    public List<Baby> getAllBabys(){
        List<Baby>bebes  = new ArrayList<Baby>();
        Cursor cursor = context.getContentResolver().query(BabyTipContract.BabysEntry.CONTENT_URI,
                BabyTipContract.BabysEntry.BABY_COLUMNS, null, null, null);
        Baby baby;
        if (cursor.moveToFirst()) {
            do {
                baby = new Baby();
                baby.setId_baby(Integer.parseInt(cursor.getString(0)));
                baby.setName(cursor.getString(1));
                baby.setPicture(cursor.getString(2));
                baby.setAge(Integer.parseInt(cursor.getString(3)));
                baby.setPeso(Integer.parseInt(cursor.getString(4)));
                baby.setEstatura(Integer.parseInt(cursor.getString(5)));
                baby.setFecha_nacimiento(cursor.getString(6));
                bebes.add(baby);
            } while (cursor.moveToNext());
        }

        return bebes;
    }


    public Baby getBabyById(int id_baby) {
        Baby baby = null;
        Cursor cursor = context.getContentResolver().query(BabyTipContract.BabysEntry.CONTENT_URI,
                BabyTipContract.BabysEntry.BABY_COLUMNS, BabyTipContract.BabysEntry._ID + " = ?", new String[]{id_baby+""}, null);

        if (cursor.moveToFirst()) {
            baby = new Baby();
            baby.setId_baby(Integer.parseInt(cursor.getString(0)));
            baby.setName(cursor.getString(1));
            baby.setPicture(cursor.getString(2));
            baby.setAge(Integer.parseInt(cursor.getString(3)));
            baby.setPeso(Integer.parseInt(cursor.getString(4)));
            baby.setEstatura(Integer.parseInt(cursor.getString(5)));
            baby.setFecha_nacimiento(cursor.getString(6));
        }
        return baby;
    }
    public int updateBaby (Baby baby){
        ContentValues values = new ContentValues();
        values.put(BabyTipContract.BabysEntry._ID,baby.getId_baby());
        values.put(BabyTipContract.BabysEntry.KEY_NAME,baby.getName());
        values.put(BabyTipContract.BabysEntry.KEY_PICTURE,baby.getPicture());
        values.put(BabyTipContract.BabysEntry.KEY_AGE,baby.getAge());
        values.put(BabyTipContract.BabysEntry.KEY_PESO,baby.getPeso());
        values.put(BabyTipContract.BabysEntry.KEY_ESTATURA,baby.getEstatura());
        values.put(BabyTipContract.BabysEntry.KEY_FECHA_NAC,baby.getFecha_nacimiento());
        int result = context.getContentResolver().update(BabyTipContract.BabysEntry.CONTENT_URI, values, BabyTipContract.BabysEntry._ID + " = ?", new String[]{baby.getId_baby() + ""});
        return result;

    }

}

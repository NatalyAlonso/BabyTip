package com.example.nataly.finalbabytip.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.BassBoost;

import java.util.concurrent.BrokenBarrierException;

/**
 * Created by Nataly on 15/11/2015.
 */
public class BabyTipDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BabyTipDB.db";

    public BabyTipDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BABY_TABLE = "CREATE TABLE "+BabyTipContract.BabysEntry.TABLE_NAME+" ( " +
                BabyTipContract.BabysEntry._ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BabyTipContract.BabysEntry.KEY_NAME      + " TEXT, "+
                BabyTipContract.BabysEntry.KEY_PICTURE   + " TEXT, " +
                BabyTipContract.BabysEntry.KEY_AGE       + " INTEGER, " +
                BabyTipContract.BabysEntry.KEY_PESO      + " INTEGER, " +
                BabyTipContract.BabysEntry.KEY_ESTATURA  + " INTEGER, " +
                BabyTipContract.BabysEntry.KEY_FECHA_NAC + " TEXT)";

        String CREATE_TEST_TABLE = "CREATE TABLE "+ BabyTipContract.TestEntry.TABLE_NAME +" ( " +
                BabyTipContract.TestEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BabyTipContract.TestEntry.KEY_ID_GUIDE + " INTEGER, "+
                BabyTipContract.TestEntry.KEY_NAME     + " TEXT, "+"status TEXT, " +
                BabyTipContract.TestEntry.KEY_CONTENT  + " TEXT)";

        String CREATE_GUIDE_TABLE = "CREATE TABLE "+BabyTipContract.GuideEntry.TABLE_NAME+" ( " +
                BabyTipContract.GuideEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BabyTipContract.GuideEntry.KEY_NAME     + " TEXT)";

        String CREATE_BABY_TEST_TABLE = "CREATE TABLE "+BabyTipContract.BabyTestEntry.TABLE_NAME +" ( " +
                BabyTipContract.BabyTestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BabyTipContract.BabyTestEntry.KEY_ID_BABY      + " INTEGER, "+
                BabyTipContract.BabyTestEntry.KEY_ID_TEST      + " INTEGER, "+
                BabyTipContract.BabyTestEntry.KEY_GRADE        + " INTEFER )";

        // create books table
        db.execSQL(CREATE_BABY_TABLE);
        db.execSQL(CREATE_TEST_TABLE);
        db.execSQL(CREATE_GUIDE_TABLE);
        db.execSQL(CREATE_BABY_TEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS "+BabyTipContract.BabysEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+BabyTipContract.TestEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+BabyTipContract.GuideEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+BabyTipContract.BabyTestEntry.TABLE_NAME);
        // create fresh books table
        this.onCreate(db);
    }
}

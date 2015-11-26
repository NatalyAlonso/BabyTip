package com.example.nataly.finalbabytip.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Nataly on 15/11/2015.
 */
public class BabyTipProvider extends ContentProvider {

    private BabyTipDbHelper mOpenHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int BABYS = 100;
    private static final int BABY_BY_ID = 101;

    private static final int TEST = 200;
    private static final int GUIDE = 300;
    private static final int BABY_TEST = 400;
    private static final int BABY_TEST_BY_BABY = 401;

    private static final SQLiteQueryBuilder sBabyByIdQueryBuilder;

    static{
        sBabyByIdQueryBuilder = new SQLiteQueryBuilder();
        //This is the table babys
        sBabyByIdQueryBuilder.setTables(
                BabyTipContract.BabysEntry.TABLE_NAME);
    }






    @Override
    public boolean onCreate() {
        mOpenHelper = new BabyTipDbHelper(getContext());
        return false;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = BabyTipContract.CONTENT_AUTHORITY;
        // com.example.nataly.finalbabytip/babytip
        matcher.addURI(authority, BabyTipContract.PATH_BABYS, BABYS);
        matcher.addURI(authority, BabyTipContract.PATH_BABYS + "/#", BABY_BY_ID);
        matcher.addURI(authority, BabyTipContract.PATH_TEST, TEST);
        matcher.addURI(authority, BabyTipContract.PATH_GUIDE, GUIDE);
        matcher.addURI(authority, BabyTipContract.PATH_BABY_TEST, BABY_TEST);
        matcher.addURI(authority, BabyTipContract.PATH_BABY_TEST + "/#", BABY_TEST_BY_BABY);
        return matcher;
    }
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor retCursor;
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        switch (match){
            case BABYS: {
                retCursor = db.query(
                        BabyTipContract.BabysEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: "+uri);
        }

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case BABY_BY_ID:
                return BabyTipContract.BabysEntry.CONTENT_ITEM_TYPE;
            case BABY_TEST_BY_BABY:
                return BabyTipContract.BabyTestEntry.CONTENT_TYPE;
            case BABYS:
                return BabyTipContract.BabysEntry.CONTENT_TYPE;
            case TEST:
                return BabyTipContract.TestEntry.CONTENT_TYPE;
            case GUIDE:
                return BabyTipContract.GuideEntry.CONTENT_TYPE;
            case BABY_TEST:
                return BabyTipContract.BabyTestEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case BABYS: {
                long _id = db.insert(BabyTipContract.BabysEntry.TABLE_NAME, null, values);
                if ( _id > 0 ) {
                    returnUri = BabyTipContract.BabysEntry.buildBabysUri(_id);
                    returnUri.buildUpon().appendQueryParameter(BabyTipContract.BabysEntry._ID,_id+"");
                }
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case TEST: {
                long _id = db.insert(BabyTipContract.TestEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = BabyTipContract.TestEntry.buildTestUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case GUIDE: {
                long _id = db.insert(BabyTipContract.GuideEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = BabyTipContract.GuideEntry.buildGuidesUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case BABY_TEST: {
                long _id = db.insert(BabyTipContract.BabyTestEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = BabyTipContract.BabyTestEntry.buildBabysTestUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int matcher = sUriMatcher.match(uri);
        int rows = 0;
        switch (matcher) {
            case BABYS:
                rows = db.update(BabyTipContract.BabysEntry.TABLE_NAME,values, selection, selectionArgs);
                if (rows > 0) {

                } else
                    throw new android.database.SQLException("Failed to update row into " + uri);
                break;
        }
        return rows;

    }
}

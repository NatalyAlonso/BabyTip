package com.example.nataly.finalbabytip.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Nataly on 15/11/2015.
 */
public class BabyTipContract {
    public static final String CONTENT_AUTHORITY = "com.example.nataly.finalbabytip";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BABYS= "babys";
    public static final String PATH_TEST= "test";
    public static final String PATH_GUIDE= "guide";
    public static final String PATH_BABY_TEST= "baby_test";


    public static final class BabysEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BABYS).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BABYS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BABYS;

        //table
        public static final String TABLE_NAME = "babys";

        /*** COLUMN NAMES ***/
        public static final String _ID = "id_baby";
        public static final String KEY_NAME = "name";
        public static final String KEY_PICTURE = "picture";
        public static final String KEY_AGE = "age";
        public static final String KEY_PESO = "peso";
        public static final String KEY_ESTATURA= "estatura";
        public static final String KEY_FECHA_NAC = "fecha_nacimiento";

        /*** COLUMN ARRAYS **/
        public static final String[] BABY_COLUMNS = {_ID,KEY_NAME,KEY_PICTURE,KEY_AGE,KEY_PESO,KEY_ESTATURA,KEY_FECHA_NAC};

        public static Uri buildBabysUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static Uri buildBabyByIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static String getBabyIdFromUri(Uri uri) {
            return uri.getPathSegments().get(0);
        }


    }

    public static final class TestEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEST).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TEST;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TEST;
        //table
        public static final String TABLE_NAME = "test";

        /** COLUMN NAMES **/
        public static final String _ID = "id_test";
        public static final String KEY_NAME = "name";
        public static final String KEY_ID_GUIDE = "id_guide";
        public static final String KEY_CONTENT = "content";

        // columns
        public static final String[] TEST_COLUMNS = {_ID,KEY_ID_GUIDE,KEY_NAME,KEY_CONTENT};
        public static Uri buildTestUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }

    public static final class GuideEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUIDE).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDE;
        //table
        public static final String TABLE_NAME = "guide";

        /** COLUMNS NAME **/
        public static final String _ID = "id_guide";
        public static final String KEY_NAME = "name";

        // columns
        public static final String[] GUIDE_COLUMNS = {_ID,KEY_NAME};
        public static Uri buildGuidesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
    public static final class BabyTestEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BABY_TEST).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BABY_TEST;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BABY_TEST;

        //table
        public static final String TABLE_NAME = "baby_test";

        //columns name
        public static final String _ID = "id_baby_test";
        public static final String KEY_ID_BABY = "id_baby";
        public static final String KEY_ID_TEST = "id_test";
        public static final String KEY_GRADE = "grade";

        // columns
        public static final String[] BABY_TEST_COLUMNS = {_ID,KEY_ID_BABY,KEY_ID_TEST,KEY_GRADE};
        public static Uri buildBabysTestUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}

package strathmore.com.sqlitelab;

import android.provider.BaseColumns;

/**
 * Created by Briege on 11/1/2017.
 */

public final class Contract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactsManager";


    private Contract() {
    }

    public static abstract class contact implements BaseColumns {
        public static final String TABLE_CONTACTS = "contact";
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_PH_NO = "phone_number";

        public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_CONTACTS + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT " + " ) ";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_CONTACTS;
    }

    public static abstract class department implements BaseColumns {
        public static final String TABLE_DEPARTMENT = "department";
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_MANAGER = "manager";

        public static final String CREATE_TABLE1 = " CREATE TABLE " + TABLE_DEPARTMENT + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_MANAGER + " TEXT " + " ) ";
        public static final String DELETE_TABLE1 = "DROP TABLE IF EXISTS " + TABLE_DEPARTMENT;
    }
}
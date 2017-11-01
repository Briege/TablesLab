package strathmore.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static strathmore.com.sqlitelab.Contract.contact.CREATE_TABLE;
import static strathmore.com.sqlitelab.Contract.contact.DELETE_TABLE;
import static strathmore.com.sqlitelab.Contract.contact.KEY_ID;
import static strathmore.com.sqlitelab.Contract.contact.KEY_NAME;
import static strathmore.com.sqlitelab.Contract.contact.KEY_PH_NO;
import static strathmore.com.sqlitelab.Contract.contact.TABLE_CONTACTS;
import static strathmore.com.sqlitelab.Contract.department.KEY_MANAGER;
import static strathmore.com.sqlitelab.Contract.department.TABLE_DEPARTMENT;

/**
 * Created by Briege on 10/17/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context){
        super(context, Contract.DATABASE_NAME, null, Contract.DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contract.contact.CREATE_TABLE);
        db.execSQL(Contract.department.CREATE_TABLE1);

    }

    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if it existed
        db.execSQL(Contract.contact.DELETE_TABLE);
        db.execSQL(Contract.department.DELETE_TABLE1);

        //Create tables again
        onCreate(db);

    }


    //Adding new contact
    public void addContact (Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); //Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number

        db.insert(TABLE_CONTACTS, null, values );
        db.close();

    }

    public void addDepartment (Department department) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, department.getName()); //Contact Name
        values.put(KEY_MANAGER, department.getManager()); //Contact Phone Number

        db.insert(TABLE_DEPARTMENT, null, values );
        db.close();

    }




    //Getting all contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
    //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        //return contact list
        return contactList;
    }

    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_DEPARTMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Department department = new Department();
                department.setID(Integer.parseInt(cursor.getString(0)));
                department.setName(cursor.getString(1));
                department.setManager(cursor.getString(2));
                //Adding contact to list
                departmentList.add(department);
            } while (cursor.moveToNext());
        }
        //return contact list
        return departmentList;
    }

    //Getting Contacts count
    public int getContactsCount(){
        String countQuery = " SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    public int getDepartmentsCount(){
        String countQuery = " SELECT * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] {String.valueOf(contact.getID()) });
    }

    public int updateDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, department.getName());
        values.put(KEY_PH_NO, department.getManager());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] {String.valueOf(department.getID()) });
    }

    //Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[] {String.valueOf(contact.getID()) });
        db.close();
    }

    public void deleteDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENT, KEY_ID + " = ?", new String[] {String.valueOf(department.getID()) });
        db.close();
    }

    //Inserting row


    //Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return contact;
    }
    public Department getDepartment(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEPARTMENT, new String[] { KEY_ID, KEY_NAME, KEY_MANAGER }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Department department = new Department(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return department;
    }

}


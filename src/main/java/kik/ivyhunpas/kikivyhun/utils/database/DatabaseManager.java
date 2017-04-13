package kik.ivyhunpas.kikivyhun.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import kik.ivyhunpas.kikivyhun.entities.Address;
import kik.ivyhunpas.kikivyhun.entities.User;
import kik.ivyhunpas.kikivyhun.entities.base.EntityBase;

/**
 * Created by Blaise on 13/04/2017.
 */

public class DatabaseManager {

    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public DatabaseManager(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public long insertAddressData(Address address) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Address.AddressEntry.COLUMN_NAME_NUM_WAY, address.getNum_way());
        values.put(Address.AddressEntry.COLUMN_NAME_NAME_WAY, address.getName_way());
        values.put(Address.AddressEntry.COLUMN_NAME_POSTAL_CODE, address.getPostal_code());
        values.put(Address.AddressEntry.COLUMN_NAME_CITY, address.getCity());
        values.put(Address.AddressEntry.COLUMN_NAME_LAT, address.getLat());
        values.put(Address.AddressEntry.COLUMN_NAME_LNG, address.getLng());

        long newRowId = db.insert(Address.AddressEntry.TABLE_NAME, null, values);
        address.setId(newRowId);

        return newRowId;
    }

    public List<Address> getAddressList(){
        db = dbHelper.getReadableDatabase();
        List<Address> addressList = new ArrayList<>();


        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                Address.AddressEntry.COLUMN_NAME_NUM_WAY,
                Address.AddressEntry.COLUMN_NAME_NAME_WAY,
                Address.AddressEntry.COLUMN_NAME_POSTAL_CODE,
                Address.AddressEntry.COLUMN_NAME_CITY,
                Address.AddressEntry.COLUMN_NAME_LAT,
                Address.AddressEntry.COLUMN_NAME_LNG
        };

        Cursor cursor = db.query(
                Address.AddressEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            Address address = new Address();
            address.setNum_way(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_NUM_WAY)));
            address.setName_way(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_NAME_WAY)));
            address.setPostal_code(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_POSTAL_CODE)));
            address.setCity(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_CITY)));
            address.setLng(cursor.getDouble(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_LNG)));
            address.setLat(cursor.getDouble(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_LAT)));
            addressList.add(address);
        }

        cursor.close();
        return addressList;
    }

    public Address getAddressById(long id) {
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                Address.AddressEntry.COLUMN_NAME_NUM_WAY,
                Address.AddressEntry.COLUMN_NAME_NAME_WAY,
                Address.AddressEntry.COLUMN_NAME_POSTAL_CODE,
                Address.AddressEntry.COLUMN_NAME_CITY,
                Address.AddressEntry.COLUMN_NAME_LAT,
                Address.AddressEntry.COLUMN_NAME_LNG
        };

        // Filter results WHERE clause
        String selection = EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        //How you want the results sorted in the resulting Cursor
        //String sortOrder =

        Cursor cursor = db.query(
                Address.AddressEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToNext();
        Address address = new Address();
        address.setNum_way(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_NUM_WAY)));
        address.setName_way(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_NAME_WAY)));
        address.setPostal_code(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_POSTAL_CODE)));
        address.setCity(cursor.getString(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_CITY)));
        address.setLng(cursor.getDouble(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_LNG)));
        address.setLat(cursor.getDouble(cursor.getColumnIndexOrThrow(Address.AddressEntry.COLUMN_NAME_LAT)));

        cursor.close();
        return address;
    }

    public long insertUserData(User user) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.UserEntry.COLUMN_NAME_FIRSTNAME, user.getFirstname());
        values.put(User.UserEntry.COLUMN_NAME_LASTNAME, user.getLastname());
        values.put(User.UserEntry.COLUMN_NAME_LOGIN, user.getLogin());
        values.put(User.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(User.UserEntry.COLUMN_NAME_LAT, user.getLat());
        values.put(User.UserEntry.COLUMN_NAME_LONG, user.getLng());

        long newRowId = db.insert(User.UserEntry.TABLE_NAME, null, values);
        user.setId(newRowId);

        return newRowId;
    }

    public List<User> getUserList(){
        db = dbHelper.getReadableDatabase();
        List<User> userList = new ArrayList<>();


        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                User.UserEntry.COLUMN_NAME_FIRSTNAME,
                User.UserEntry.COLUMN_NAME_LASTNAME,
                User.UserEntry.COLUMN_NAME_LOGIN,
                User.UserEntry.COLUMN_NAME_LAT,
                User.UserEntry.COLUMN_NAME_LONG
        };

        Cursor cursor = db.query(
                User.UserEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            User user = new User();
            user.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_FIRSTNAME)));
            user.setLastname(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LASTNAME)));
            user.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LOGIN)));
            user.setLng(cursor.getDouble(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LONG)));
            user.setLat(cursor.getDouble(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LAT)));
            userList.add(user);
        }

        cursor.close();
        return userList;
    }

    public User getUserById(long id) {
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                User.UserEntry.COLUMN_NAME_FIRSTNAME,
                User.UserEntry.COLUMN_NAME_LASTNAME,
                User.UserEntry.COLUMN_NAME_LOGIN,
                User.UserEntry.COLUMN_NAME_LAT,
                User.UserEntry.COLUMN_NAME_LONG
        };

        // Filter results WHERE clause
        String selection = EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        //How you want the results sorted in the resulting Cursor
        //String sortOrder =

        Cursor cursor = db.query(
                User.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToNext();
        User user = new User();
        user.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_FIRSTNAME)));
        user.setLastname(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LASTNAME)));
        user.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LOGIN)));
        user.setLng(cursor.getDouble(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LONG)));
        user.setLat(cursor.getDouble(cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_LAT)));

        cursor.close();
        return user;
    }
}

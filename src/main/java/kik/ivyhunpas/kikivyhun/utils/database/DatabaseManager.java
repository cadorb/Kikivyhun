package kik.ivyhunpas.kikivyhun.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import kik.ivyhunpas.kikivyhun.entities.Address;
import kik.ivyhunpas.kikivyhun.entities.User;

/**
 * Created by Blaise on 13/04/2017.
 */

public class DatabaseManager {

    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public DatabaseManager(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public long insertAddressData(Address address){
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

    public long insertUserData(User user){
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
}

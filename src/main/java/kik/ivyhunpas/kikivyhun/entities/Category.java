package kik.ivyhunpas.kikivyhun.entities;

import android.provider.BaseColumns;

import kik.ivyhunpas.kikivyhun.entities.base.EntityBase;

/**
 * Created by Blaise on 11/04/2017.
 */

public class Category extends EntityBase{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + CategoryEntry.TABLE_NAME + " (" +
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        CategoryEntry.COLUMN_NAME_NAME + " TEXT);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME + ";";
    }
}

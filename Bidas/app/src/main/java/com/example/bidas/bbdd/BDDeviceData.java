package com.example.bidas.bbdd;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bidas.columnsName.deviceDataColumns.deviceDataColumn;
import com.example.bidas.entities.deviceData;

public class BDDeviceData {
    DeviceDataOpenHelper deviceDataOH;
    Activity activity;
    SQLiteDatabase dbClientes;


    public BDDeviceData(Activity activity) {
        this.activity = activity;
        deviceDataOH = new DeviceDataOpenHelper(activity);
    }

    public String getDeviceData() {
        dbClientes = deviceDataOH.getReadableDatabase();

        String columns[] = new String[]{deviceDataColumn.ID};
        String limit = "1";
        String id="null";

        Cursor c = dbClientes.query(
                deviceDataColumn.TABLE_NAME,
                columns,
                limit,
                null,
                null,
                null,
                null,
                limit
        );
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            id = c.getString(0);
        }
        c.close();
        dbClientes.close();
        return id;
    }

    public void deleteDeviceData(String id) {
        dbClientes = deviceDataOH.getWritableDatabase();

        String selection = deviceDataColumn.ID + " = ?";
        String[] selectionArgs = {id};

        dbClientes.delete(
                deviceDataColumn.TABLE_NAME,
                selection,
                selectionArgs);
        dbClientes.close();

    }

    public void insertDeviceData(deviceData data) {
        dbClientes = deviceDataOH.getWritableDatabase();
        if (dbClientes != null) {
            ContentValues valores = new ContentValues();
            valores.put(deviceDataColumn.ID, data.getDeviceId());
            valores.put(deviceDataColumn.NAME, data.getDeviceName());
            dbClientes.insert(deviceDataColumn.TABLE_NAME, null, valores);
            dbClientes.close();
        }
    }

    public class DeviceDataOpenHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Bidas.db";

        public DeviceDataOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + deviceDataColumn.TABLE_NAME + " ("
                    + deviceDataColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + deviceDataColumn.ID + " TEXT NOT NULL,"
                    + deviceDataColumn.NAME + " TEXT NOT NULL,"
                    + "UNIQUE (" + deviceDataColumn.ID + "))");
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
    }
}
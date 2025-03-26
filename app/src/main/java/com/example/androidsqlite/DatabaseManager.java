package com.example.androidsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.ArrayList;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
public class DatabaseManager {
    private static final String ROW_ID = "_id";
    private static final String ROW_NAMA = "nama";
    private static final String ROW_NPM = "npm";
    private static final String ROW_TB = "tb";
    private static final String ROW_BB = "bb";
    private static final String ROW_RIWAYAT = "riwayat";
    private static final String NAMA_DB = "database1";
    private static final String NAMA_TABEL = "tblpelanggan";
    private static final int DB_VERSION = 4;
    private static final String CREATE_TABLE = "create table IF NOT EXISTS "+NAMA_TABEL+" ("+ROW_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+ROW_NAMA+" text,"+ROW_NPM+" text,"+ROW_TB+" text, "+ROW_BB+" text, "+ROW_RIWAYAT+" text)";
    private final Context context;
    private DatabaseOpenHelper dbHelper;
    private  SQLiteDatabase db;
    public DatabaseManager(Context ctx) {
        this.context = ctx;
        dbHelper = new DatabaseOpenHelper(context);
        setDb(dbHelper.getWritableDatabase());
    }
    public SQLiteDatabase getDb() {
        return db;
    }
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
    private static class DatabaseOpenHelper extends SQLiteOpenHelper {
        public DatabaseOpenHelper(Context context) {
            super(context, NAMA_DB, null, DB_VERSION);
            //TODO Auto-generated constructor stub 39:
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS "+NAMA_TABEL);
            onCreate(db);
        }
    }
    public void close() {
        dbHelper.close();
    }
    //private String QueryUpdate;
    public void UpdateRecord (int iId, String sName, String snpm, String stb, String sbb, String sriwayat) {
        ContentValues values = new ContentValues();
        values.put(ROW_NAMA, sName);
        values.put(ROW_NPM, snpm);
        values.put(ROW_TB, stb);
        values.put(ROW_BB, sbb);
        values.put(ROW_RIWAYAT, sriwayat);
        try {
            db.update(NAMA_TABEL, values, ROW_ID + "=?", new String[]{String.valueOf(iId)});
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    //menghapus data
    public void DeleteRecord (int Iid)
    {
        ContentValues values = new ContentValues();
        values.put(ROW_ID, Iid);
        try {
            db.delete(NAMA_TABEL, ROW_ID + "=?", new String[]{String.valueOf(Iid)});
            /*sSQLQuery = "DELETE FROM datadiri " +
                "WHERE ROW_ID='" + iId + "';";
            db.execSQL(sSQLQuery);*/
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }
    //menambah data
    public void addRow(String anama, String anpm, String atb, String abb, String ariwayat) {
        ContentValues values = new ContentValues();
        values.put(ROW_NAMA, anama);
        values.put(ROW_NPM, anpm);
        values.put(ROW_TB, atb);
        values.put(ROW_BB, abb);
        values.put(ROW_RIWAYAT, ariwayat);
        try {
            db.insert(NAMA_TABEL, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }
    public Cursor ambilSemuaBarisCursor() {
        return db.query(NAMA_TABEL, new String[]{ROW_ID, ROW_NAMA, ROW_NPM, ROW_TB, ROW_BB, ROW_RIWAYAT},
                null, null, null, null, ROW_ID + " ASC");
    }
    //menampung data hasil select, dan ditampung pada arraylist
    public ArrayList<ArrayList<Object>> ambilSemuaBaris() {
        ArrayList<ArrayList<Object>> dataArray = new ArrayList<>();
        Cursor cur;
        try {
            cur = db.query(NAMA_TABEL,
                    new String[]{ROW_ID, ROW_NAMA, ROW_NPM, ROW_TB, ROW_BB, ROW_RIWAYAT},
                    null, null, null, null, null);
            cur.moveToFirst();
            Log.d("DB", "Jumlah data: " + cur.getCount()); // Tambahkan log ini

            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<>();
                    dataList.add(cur.getLong(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataArray.add(dataList);
                } while (cur.moveToNext());
            }
            cur.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Database ERROR", e.toString());
        }
        return dataArray;
    }

}
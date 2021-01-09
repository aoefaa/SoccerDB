package com.pemsel.aoefaa.soccerdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "TeamDB";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_TEAM_NAME = "teamName";
    public static String ITEM_TEAM_YEAR = "teamYear";
    public static String ITEM_TEAM_DESC = "teamDesc";
    public static String ITEM_TEAM_BADGE = "teamBadge";
    public static String FAVORITE_STATUS = "favStatus";

    private static String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" +
            KEY_ID + " TEXT," +
            ITEM_TEAM_NAME + " TEXT," +
            ITEM_TEAM_YEAR + " TEXT," +
            ITEM_TEAM_DESC + " TEXT," +
            ITEM_TEAM_BADGE + " TEXT," +
            FAVORITE_STATUS + " TEXT)";

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Membuat tabel kosong
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 11; x++) {
            cv.put(KEY_ID, x);
            cv.put(FAVORITE_STATUS, "0");

            db.insert(TABLE_NAME,null, cv);
        }
    }

    // Masukkan data ke database
    public void insertIntoTheDatabase(String id,
                                      String team_name,
                                      String team_year,
                                      String team_desc,
                                      String team_badge,
                                      String fav_status) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, id);
        cv.put(ITEM_TEAM_NAME, team_name);
        cv.put(ITEM_TEAM_YEAR, team_year);
        cv.put(ITEM_TEAM_DESC, team_desc);
        cv.put(ITEM_TEAM_BADGE, team_badge);
        cv.put(FAVORITE_STATUS, fav_status);

        db.insert(TABLE_NAME, null, cv);
        Log.d("FavDB Status", team_name + ", favstatus - " + fav_status + "- . " + cv);
    }

    // Membaca semua data
    public Cursor readAllData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID + "=" + id + "";
        return db.rawQuery(sql, null, null);
    }

    // Menghapus baris dari database
    public void removeFav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET " + FAVORITE_STATUS + " ='0' WHERE " + KEY_ID + "=" + id + "";
        db.execSQL(sql);
        Log.d("Remove", id.toString());
    }

    // Pilih semua favorite list
    public Cursor selectAllFavoriteList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + FAVORITE_STATUS + " = '1'";
        return db.rawQuery(sql, null, null);
    }
}
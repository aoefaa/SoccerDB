package com.pemsel.aoefaa.soccerdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pemsel.aoefaa.soccerdb.data.Favorite;

import java.util.ArrayList;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "TeamDB";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_TEAM_NAME = "teamName";
    public static String ITEM_TEAM_YEAR = "teamYear";
    public static String ITEM_TEAM_DESC = "teamDesc";
    public static String ITEM_TEAM_BADGE = "teamBadge";
    //public static String FAVORITE_STATUS = "favStatus";

    private static String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" +
            KEY_ID + " TEXT," +
            ITEM_TEAM_NAME + " TEXT," +
            ITEM_TEAM_YEAR + " TEXT," +
            ITEM_TEAM_DESC + " TEXT," +
            ITEM_TEAM_BADGE + " TEXT);";
            //FAVORITE_STATUS + " TEXT)";

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(db);
    }

    // Masukkan data ke database
    public long addFavorite(int id,
                            String team_name,
                            String team_year,
                            String team_desc,
                            String team_badge) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, id);
        cv.put(ITEM_TEAM_NAME, team_name);
        cv.put(ITEM_TEAM_YEAR, team_year);
        cv.put(ITEM_TEAM_DESC, team_desc);
        cv.put(ITEM_TEAM_BADGE, team_badge);


        long insert = db.insert(TABLE_NAME, null, cv);

        return insert;
    }

    // Menghapus baris dari database
    public void delFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Pilih semua favorite list
    public ArrayList<Favorite> getFavorite() {
        ArrayList<Favorite> userModeArrayList = new ArrayList<Favorite>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Favorite favorite = new Favorite();
                favorite.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                favorite.setTeam_name(c.getString(c.getColumnIndex(ITEM_TEAM_NAME)));
                favorite.setTeam_year(c.getString(c.getColumnIndex(ITEM_TEAM_YEAR)));
                favorite.setTeam_desc(c.getString(c.getColumnIndex(ITEM_TEAM_DESC)));
                favorite.setTeam_badge(c.getString(c.getColumnIndex(ITEM_TEAM_BADGE)));

                userModeArrayList.add(favorite);
                } while (c.moveToNext());
            }
        return userModeArrayList;
    }

    public boolean isFavorite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
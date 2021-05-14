package com.example.hoangdangkhoi_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteQG extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="quyengop.db";
    private static  final int DATABASE_VERSION=1;
    public SQLiteQG(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table quyengop(id integer primary key autoincrement, hoten text, thanhpho text, ngay text, sotien long)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int themQG(QuyenGop a){
        ContentValues contentValues=new ContentValues();
        contentValues.put("hoten", a.getHoten());
        contentValues.put("thanhpho", a.getThanhpho());
        contentValues.put("ngay", a.getNgay());
        contentValues.put("sotien", a.getSotien());
        SQLiteDatabase st=getWritableDatabase();
        return (int) st.insert("quyengop", null, contentValues);
    }

    public List<QuyenGop> getAllQuyenGop(){
        List<QuyenGop> qg=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        Cursor re=st.query("quyengop", null,null,null,null,null,null);
        while ((re.moveToNext())){
            qg.add(new QuyenGop(re.getInt(0),
                    re.getString(1),
                    re.getString(2),
                    re.getString(3),
                    re.getLong(4)));
        }
        return qg;
    }

    public int xoaQG(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("quyengop",
                whereClause, whereArgs);
    }

    public int capNhatQG(QuyenGop a) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", a.getHoten());
        contentValues.put("thanhpho", a.getThanhpho());
        contentValues.put("ngay", a.getNgay());
        contentValues.put("sotien", a.getSotien());
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(a.getStt())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("quyengop",
                contentValues, whereClause, whereArgs);
    }

    public List<QuyenGop> timKiem(String timkiem){
        List<QuyenGop> qg=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereclause= "hoten LIKE '%" +timkiem+ "%'";

        Cursor re = sqLiteDatabase.query("quyengop", null, whereclause , null,null, null, null);
        while(re.moveToNext()){
            qg.add(new QuyenGop(re.getInt(0),
                    re.getString(1),
                    re.getString(2),
                    re.getString(3),
                    re.getLong(4)));

        }
        return qg;
    }
}

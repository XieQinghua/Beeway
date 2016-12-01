package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class AddTravelDao {
    private DbOpenHelper dbOpenHelper;



    public AddTravelDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);

    }
    /**
     * 添加表数据
     *
     * @param travelText
     */
    public void insert(AddTravelText travelText) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into addtravel_center values(?,?,?,?)", new Object[]{
                travelText.getUserid(), travelText.getFootprintid(), travelText.getContent(),travelText.getListjson()
        });
        db.close();
    }

    /**
     * 查询数据库
     * @param userid
     * @param footprintid
     * @return
     */
    public AddTravelText select(String userid,String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from addtravel_center where userid = ? and footprintid = ? LIMIT 1"
                ,new String[]{userid,footprintid});
        AddTravelText addTravelText = null;
        if (c!=null && c.moveToNext()) {
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content = c.getString(c.getColumnIndex("content"));
            String listjson = c.getString(c.getColumnIndex("listjson"));
            addTravelText = new AddTravelText(userid,footprintids,content,listjson);
        }
        c.close();
        db.close();
        return addTravelText;
    }

    public  void update(String userid,String footprintid){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update addtravel_center set footprintid =? where userid = ? ", new Object[]{footprintid,userid});
        db.close();
    }



}

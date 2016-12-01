package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

/**
 * Created by Administrator on 2015/9/10.
 */
public class FengyouFootprintTextDao {
    private DbOpenHelper dbOpenHelper;

    public FengyouFootprintTextDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
    }


    /**
     * 添加表数据
     *
     * @param footprint
     */
    public void textinsert(FengyouFootprintText footprint) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into et_Footprints values(?,?,?,?,?,?,?)", new Object[]{
                footprint.getUserid(), footprint.getFootprintid(),
                footprint.getContent(), footprint.getPathlist(), footprint.getStatus(),
                footprint.getType(), footprint.getAddtime()
        });
        db.close();
    }


    /**
     * 更改数据库
     */
    public void update(String userid, String footprintid, String filekeys) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update et_Footprints set pathlist= ? where userid = ? and footprintid = ?", new Object[]{filekeys, userid, footprintid});
        db.close();
    }


    public FengyouFootprintText select(String userid, String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        FengyouFootprintText footprintText = null;
        Cursor c = db.rawQuery("select * from et_Footprints where userid = ? and footprintid = ? LIMIT 1"
                , new String[]{userid, footprintid});
        if (c.moveToNext()) {
            String userids = c.getString(c.getColumnIndex("userid"));
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content = c.getString(c.getColumnIndex("content"));
            String pathlist = c.getString(c.getColumnIndex("pathlist"));
            String status = c.getString(c.getColumnIndex("status"));
            String type = c.getString(c.getColumnIndex("type"));
            String addtime = c.getString(c.getColumnIndex("addtime"));
            footprintText = new FengyouFootprintText(userids, footprintids, content, pathlist, status, type, addtime);
        }
        c.close();
        db.close();
        return footprintText;
    }

}

package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

/**
 * Created by Administrator on 2015/9/19.
 */
public class FengYouAddtravelImgeDao {
    private DbOpenHelper dbOpenHelper ;

    public FengYouAddtravelImgeDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
    }

    /**
     * 添加图片表数据
     * @param addtravelImge
     */
    public void imginsert(FengYouAddtravelImge addtravelImge) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into et_issuecutomer_file values(?,?,?,?,?,?)", new Object[]{
                addtravelImge.getUserid(),addtravelImge.getFootprintid(), addtravelImge.getFilePath(),addtravelImge.getContent(),addtravelImge.getStatus()
                ,addtravelImge.getCurrentTime()
        });
        db.close();
    }


    /**
     * 查询数据库
     * @param userid
     * @param footprintid
     * @return
     */
    public FengYouAddtravelImge select(String userid,String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from et_issuecutomer_file where userid = ? and footprintid = ? and status=0 LIMIT 1"
                ,new String[]{userid,footprintid});
        FengYouAddtravelImge footprintImage = null;
        if (c!=null && c.moveToNext()) {
            String status = c.getString(c.getColumnIndex("status"));
            String userids = c.getString(c.getColumnIndex("userid"));
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content  = c.getString(c.getColumnIndex("content"));
            String filePath = c.getString(c.getColumnIndex("filePath"));
            String currentTime = c.getString(c.getColumnIndex("addtime"));
            footprintImage = new FengYouAddtravelImge(userids,footprintids,filePath,content,status,currentTime);
        }
        c.close();
        db.close();
        return footprintImage;
    }

    public  void imgupdate(String userid,String footprintid,String status){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update et_issuecutomer_file set status =? where userid = ? and footprintid = ?", new Object[]{status, userid, footprintid});
        db.close();

    }
}

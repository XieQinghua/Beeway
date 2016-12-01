package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

import java.util.Date;

/**
 * Created by Administrator on 2015/9/11.
 */
public class FengyouFootprintImageDao {

    private DbOpenHelper dbOpenHelper ;

    public FengyouFootprintImageDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
    }

    /**
     * 添加图片表数据
     * @param footprints
     */
    public void imageinsert(FengyouFootprintImage footprints) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into et_Footprint_file values(?,?,?,?,?,?)", new Object[]{
                footprints.getUserid(),footprints.getFootprintid(), footprints.getFilePath(),footprints.getContent(),footprints.getStatus()
                ,footprints.getCurrentTime()
        });
        db.close();
    }


    /**
     * 查询数据库
     * @param userid
     * @param footprintid
     * @return
     */
    public FengyouFootprintImage select(String userid,String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from et_Footprint_file where userid = ? and footprintid = ? and status=0 LIMIT 1"
                ,new String[]{userid,footprintid});
        FengyouFootprintImage footprintImage = null;
        if (c!=null && c.moveToNext()) {
            String status = c.getString(c.getColumnIndex("status"));
            String userids = c.getString(c.getColumnIndex("userid"));
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content  = c.getString(c.getColumnIndex("content"));
            String filePath = c.getString(c.getColumnIndex("filePath"));
            String currentTime = c.getString(c.getColumnIndex("addtime"));
            footprintImage = new FengyouFootprintImage(userids,footprintids,filePath,content,status,currentTime);
        }
        c.close();
        db.close();
        return footprintImage;
    }

    public  void imgupdate(String userid,String filePath,String status){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update et_Footprint_file set status =? where userid = ? and filePath = ?", new Object[]{status, userid, filePath});
        db.close();

    }
}

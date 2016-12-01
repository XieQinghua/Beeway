package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

/**
 * Created by Administrator on 2015/9/19.
 */
public class FengYouIssueCutnmerImgeDao {

    private DbOpenHelper dbOpenHelper ;

    public FengYouIssueCutnmerImgeDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
    }

    /**
     * 添加图片表数据
     * @param cutnmerImge
     */
    public void imginsert(FengYouIssueCutnmerImge cutnmerImge) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into et_travel_file values(?,?,?,?,?,?)", new Object[]{
                cutnmerImge.getUserid(),cutnmerImge.getFootprintid(), cutnmerImge.getFilePath(),cutnmerImge.getContent(),cutnmerImge.getStatus()
                ,cutnmerImge.getCurrentTime()
        });
        db.close();
    }


    /**
     * 查询数据库
     * @param userid
     * @param footprintid
     * @return
     */
    public FengYouIssueCutnmerImge select(String userid,String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from et_travel_file where userid = ? and footprintid = ? and status=0 LIMIT 1"
                ,new String[]{userid,footprintid});
        FengYouIssueCutnmerImge cutnmerImges = null;
        if (c!=null && c.moveToNext()) {
            String status = c.getString(c.getColumnIndex("status"));
            String userids = c.getString(c.getColumnIndex("userid"));
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content  = c.getString(c.getColumnIndex("content"));
            String filePath = c.getString(c.getColumnIndex("filePath"));
            String currentTime = c.getString(c.getColumnIndex("addtime"));
            cutnmerImges = new FengYouIssueCutnmerImge(userids,footprintids,filePath,content,status,currentTime);
        }
        c.close();
        db.close();
        return cutnmerImges;
    }


    public  void imgupdate(String userid,String filePath,String status){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update et_travel_file set status =? where userid = ? and filePath = ?", new Object[]{status, userid, filePath});
        db.close();

    }
}

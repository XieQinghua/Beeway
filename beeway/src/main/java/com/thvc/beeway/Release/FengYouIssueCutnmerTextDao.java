package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

/**
 * Created by Administrator on 2015/9/19.
 */
public class FengYouIssueCutnmerTextDao {

    private DbOpenHelper dbOpenHelper ;

    public FengYouIssueCutnmerTextDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
    }

    /**
     * 添加表数据
     * @param fengYouIssueCutnmerText
     */
    public void textinsert(FengYouIssueCutnmerText fengYouIssueCutnmerText){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into et_issuecutomer values(?,?,?,?,?,?)",new Object[]{
                fengYouIssueCutnmerText.getUserid(),fengYouIssueCutnmerText.getFootprintid(),
                fengYouIssueCutnmerText.getContent(),fengYouIssueCutnmerText.getPathlist(),fengYouIssueCutnmerText.getStatus(),
                fengYouIssueCutnmerText.getCurrentTime()
        });
        db.close();
    }
    public FengYouIssueCutnmerText select (String userid,String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        FengYouIssueCutnmerText cutnmerText = null;
        Cursor c = db.rawQuery("select * from et_issuecutomer where userid = ? and footprintid = ? LIMIT 1"
                ,new String[]{userid,footprintid});
        if (c.moveToNext()){
            String userids = c.getString(c.getColumnIndex("userid"));
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content = c.getString(c.getColumnIndex("content"));
            String pathlist = c.getString(c.getColumnIndex("pathlist"));
            String status  = c.getString(c.getColumnIndex("status"));
            String addtime = c.getString(c.getColumnIndex("addtime"));
            cutnmerText = new FengYouIssueCutnmerText(userids,footprintids,content,pathlist,status,addtime);
        }
        c.close();
        db.close();
        return cutnmerText;
    }
}

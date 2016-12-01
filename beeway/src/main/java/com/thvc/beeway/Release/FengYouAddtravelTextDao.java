package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;

/**
 * Created by Administrator on 2015/9/19.
 */
public class FengYouAddtravelTextDao {
    private DbOpenHelper dbOpenHelper ;

    public FengYouAddtravelTextDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
    }

    /**
     * 添加表数据
     * @param fengYouAddtravelText
     */
    public void textinsert(FengYouAddtravelText fengYouAddtravelText){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into et_travel values(?,?,?,?,?,?)",new Object[]{
                fengYouAddtravelText.getUserid(),fengYouAddtravelText.getFootprintid(),
                fengYouAddtravelText.getContent(),fengYouAddtravelText.getPathlist(),fengYouAddtravelText.getStatus(),
                fengYouAddtravelText.getCurrentTime()
        });
        db.close();
    }

    public FengYouAddtravelText select (String userid,String footprintid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        FengYouAddtravelText footprintText = null;
        Cursor c = db.rawQuery("select * from et_travel where userid = ? and footprintid = ? LIMIT 1"
                ,new String[]{userid,footprintid});
        if (c.moveToNext()){
            String userids = c.getString(c.getColumnIndex("userid"));
            String footprintids = c.getString(c.getColumnIndex("footprintid"));
            String content = c.getString(c.getColumnIndex("content"));
            String pathlist = c.getString(c.getColumnIndex("pathlist"));
            String status  = c.getString(c.getColumnIndex("status"));
            String addtime = c.getString(c.getColumnIndex("addtime"));
            footprintText = new FengYouAddtravelText(userids,footprintids,content,pathlist,status,addtime);
        }
        c.close();
        db.close();
        return footprintText;
    }

}

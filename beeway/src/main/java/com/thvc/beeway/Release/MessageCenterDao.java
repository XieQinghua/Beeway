package com.thvc.beeway.Release;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thvc.beeway.DB.DbOpenHelper;
import com.thvc.beeway.view.MyMessagePerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/21.
 */
public class MessageCenterDao {
    private DbOpenHelper dbOpenHelper;
    private List<MyMessagePerson> persons = null;

    public MessageCenterDao(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);

    }


    /**
     * 添加表数据
     *
     * @param messageCenterText
     */
    public void insert(MessageCenterText messageCenterText) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into message_center values(?,?,?,?,?)", new Object[]{
                messageCenterText.getUserid(), messageCenterText.getFootprintid(), messageCenterText.getContent(), messageCenterText.getStatus(), messageCenterText.getAddtime()
        });
        db.close();
    }

    /**
     * 查询数据库
     *
     * @param userid
     * @return
     */
    public List<MyMessagePerson> select(String userid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from message_center where userid = ?"
                , new String[]{userid});
        if (c != null) {
            persons = new ArrayList<MyMessagePerson>();
            while (c.moveToNext()) {
                MyMessagePerson person = new MyMessagePerson();
                String content = c.getString(c.getColumnIndex("content"));
                String statuss = c.getString(c.getColumnIndex("status"));
                String addtime = c.getString(c.getColumnIndex("addtime"));
                String footprintid = c.getString(c.getColumnIndex("footprintid"));
                person.setContent(content);
                person.setStatuss(statuss);
                person.setAddtime(addtime);
                person.setFootprintid(footprintid);
                persons.add(person);
            }
        }

        c.close();
        db.close();
        return persons;
    }

    public void imgupdate(String userid, String footprintid, String status) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update message_center set status =? where userid = ? and footprintid = ?", new Object[]{status, userid, footprintid});
        db.close();

    }
}

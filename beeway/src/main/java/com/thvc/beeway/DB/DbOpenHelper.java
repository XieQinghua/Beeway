package com.thvc.beeway.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：Beeway
 * 类描述：创建SQLite  缓存发布数据
 * 创建人：颜松梁.
 * 创建时间：2015/9/7 18:10
 * 修改人：Administrator
 * 修改时间：2015/9/7 18:10
 * 修改备注：
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context) {
        // 版本不要为0开始
        super(context, "fengyou.db", null, 1);
    }

    /**
     * 数据库第一次被创建的时候被调用
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象ID
         *  content 缓存对象数据-Json格式
         *  pathlist 对象的七牛图片路劲 多图使用都好拼接
         *  addtime 创建日期
         */
        db.execSQL("create table et_Footprints (userid varchar(32),footprintid varchar(32),content varchar(500),pathlist text,status varchar(5),type varchar(5),addtime date)");
        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象父ID
         *  content   图片7牛地址
         *  filePath  图片本地路径
         *  addtime 创建日期
         */
        //发布本地缓存附件表 status 0 未上传 1 已上传 2 正在上传 content 未七牛返回数据信息
        db.execSQL("create table et_Footprint_file (userid varchar(32),footprintid varchar(32),filePath varchar(300),content text,status varchar(5),addtime date)");

        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象ID
         *  content 缓存对象数据-Json格式
         *  pathlist 对象的七牛图片路劲 多图使用都好拼接
         *  addtime 创建日期
         */
        db.execSQL("create table et_travel(userid varchar(32),footprintid varchar(32),content varchar(500),pathlist text,status varchar(5),addtime date)");

        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象父ID
         *  content   图片7牛地址
         *  filePath  图片本地路径
         *  addtime 创建日期
         */
        //发布本地缓存附件表 status 0 未上传 1 已上传 2 正在上传 content 未七牛返回数据信息
        db.execSQL("create table et_travel_file (userid varchar(32),footprintid varchar(32),filePath varchar(300),content text,status varchar(5),addtime date)");

        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象ID
         *  content 缓存对象数据-Json格式
         *  pathlist 对象的七牛图片路劲 多图使用都好拼接
         *  addtime 创建日期
         */
        db.execSQL("create table et_issuecutomer(userid varchar(32),footprintid varchar(32),content varchar(500),pathlist text,status varchar(5),addtime date)");

        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象父ID
         *  content   图片7牛地址
         *  filePath  图片本地路径
         *  addtime 创建日期
         */
        //发布本地缓存附件表 status 0 未上传 1 已上传 2 正在上传 content 未七牛返回数据信息
        db.execSQL("create table et_issuecutomer_file (userid varchar(32),footprintid varchar(32),filePath varchar(300),content text,status varchar(5),addtime date)");


        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象ID
         *  content 缓存对象数据-Json格式
         *  pathlist 对象的七牛图片路劲 多图使用都好拼接
         *  addtime 创建日期
         */
        db.execSQL("create table et_feedback(userid varchar(32),footprintid varchar(32),content varchar(500),pathlist text,status varchar(5),addtime date)");
        /**
         *  status 0 未发布  1正在发布 2发布失败 3发布成功 4 未开启上传
         *  userid 操作用户ID
         *  footprintid  缓存对象父ID
         *  content   图片7牛地址
         *  filePath  图片本地路径
         *  addtime 创建日期
         */
        //发布本地缓存附件表 status 0 未上传 1 已上传 2 正在上传 content 未七牛返回数据信息
        db.execSQL("create table et_feedback_file (userid varchar(32),footprintid varchar(32),filePath varchar(300),content text,status varchar(5),addtime date)");

        /**
         *content 推送过来的josn数据
         *
         * status 0 未读 1 已读
         *
         *addtime 创建日期
         *
         */
        db.execSQL("create table message_center (userid VARCHAR(32),footprintid varchar(32),content text,status VARCHAR(12),addtime date)");

        //游记不保存
        db.execSQL("create table addtravel_center (userid VARCHAR(32),footprintid varchar(32),content text,listjson texts)");


    }

    /**
     * 数据库版本改变时调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

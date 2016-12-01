/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easemob.chatui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.easemob.applib.controller.HXSDKHelper;


public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static DbOpenHelper instance;
    /**
     * 我的蜂友表
     * xieqinghua
     */
    private static final String FRIEND_TABLE_CREATE = "CREATE TABLE "
            + com.easemob.chatui.db.FriendDao.FRIEND_TABLE_NAME + " ("
            + com.easemob.chatui.db.FriendDao.FRIEND_TITLE + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_SUBTITLECOLOR + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_IMG + " TEXT, "
//              + com.easemob.chatui.db.FriendDao.FRIEND_INDEX + " VARCHAR, "
            + com.easemob.chatui.db.FriendDao.FRIEND_TITLESIZE + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_USERID + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_SUBTITLESIZE + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_PLACEHOLDERIMG + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_TITLECOLOR + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_LETTER + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_SUBTITLE + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_FRIENDID + " TEXT, "
            + com.easemob.chatui.db.FriendDao.FRIEND_ID + " TEXT PRIMARY KEY);";

    private static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
            + com.easemob.chatui.db.UserDao.TABLE_NAME + " ("
            + com.easemob.chatui.db.UserDao.COLUMN_NAME_NICK + " TEXT, "
            + com.easemob.chatui.db.UserDao.COLUMN_NAME_AVATAR + " TEXT, "
            + com.easemob.chatui.db.UserDao.COLUMN_NAME_ID + " TEXT PRIMARY KEY);";

    private static final String INIVTE_MESSAGE_TABLE_CREATE = "CREATE TABLE "
            + com.easemob.chatui.db.InviteMessgeDao.TABLE_NAME + " ("
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_FROM + " TEXT, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_GROUP_ID + " TEXT, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_GROUP_Name + " TEXT, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_REASON + " TEXT, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_STATUS + " INTEGER, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_ISINVITEFROMME + " INTEGER, "
            + com.easemob.chatui.db.InviteMessgeDao.COLUMN_NAME_TIME + " TEXT); ";

    private static final String ROBOT_TABLE_CREATE = "CREATE TABLE "
            + com.easemob.chatui.db.UserDao.ROBOT_TABLE_NAME + " ("
            + com.easemob.chatui.db.UserDao.ROBOT_COLUMN_NAME_ID + " TEXT PRIMARY KEY, "
            + com.easemob.chatui.db.UserDao.ROBOT_COLUMN_NAME_NICK + " TEXT, "
            + com.easemob.chatui.db.UserDao.ROBOT_COLUMN_NAME_AVATAR + " TEXT);";

    private static final String CREATE_PREF_TABLE = "CREATE TABLE "
            + com.easemob.chatui.db.UserDao.PREF_TABLE_NAME + " ("
            + com.easemob.chatui.db.UserDao.COLUMN_NAME_DISABLED_GROUPS + " TEXT, "
            + com.easemob.chatui.db.UserDao.COLUMN_NAME_DISABLED_IDS + " TEXT);";

    private DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
    }

    public static DbOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private static String getUserDatabaseName() {
        return HXSDKHelper.getInstance().getHXId() + "_demo.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERNAME_TABLE_CREATE);
        db.execSQL(INIVTE_MESSAGE_TABLE_CREATE);
        db.execSQL(CREATE_PREF_TABLE);
        db.execSQL(ROBOT_TABLE_CREATE);
        //创建我的蜂友表
        db.execSQL(FRIEND_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + com.easemob.chatui.db.UserDao.TABLE_NAME + " ADD COLUMN " +
                    com.easemob.chatui.db.UserDao.COLUMN_NAME_AVATAR + " TEXT ;");
        }

        if (oldVersion < 3) {
            db.execSQL(CREATE_PREF_TABLE);
        }
        if (oldVersion < 4) {
            db.execSQL(ROBOT_TABLE_CREATE);
        }
    }

    public void closeDB() {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }

}

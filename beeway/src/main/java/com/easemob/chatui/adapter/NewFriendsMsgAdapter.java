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
package com.easemob.chatui.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatui.activity.LoginActivity;
import com.easemob.chatui.db.InviteMessgeDao;
import com.easemob.chatui.domain.InviteMessage;
import com.easemob.chatui.utils.UserUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.bean.Friend;
import com.thvc.beeway.bean.UserDetailBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.List;


public class NewFriendsMsgAdapter extends ArrayAdapter<InviteMessage> {
    private static String TAG = "NewFriendsMsgAdapter";
    private Context context;
    private InviteMessgeDao messgeDao;
    private String iden;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private LoginActivity mLoginActivity;//调用登录界面的getFriend()方法刷新我的蜂友

    public NewFriendsMsgAdapter(Context context, int textViewResourceId, List<InviteMessage> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        messgeDao = new InviteMessgeDao(context);
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.row_invite_msg, null);
            holder.avator = (ImageView) convertView.findViewById(R.id.avatar);
            holder.reason = (TextView) convertView.findViewById(R.id.message);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.status = (Button) convertView.findViewById(R.id.user_state);
            holder.reject = (Button) convertView.findViewById(R.id.user_reject);
            holder.status.setBackgroundResource(R.drawable.button_blue_shape);
            holder.groupContainer = (LinearLayout) convertView.findViewById(R.id.ll_group);
            holder.groupname = (TextView) convertView.findViewById(R.id.tv_groupName);
            // holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String str1 = context.getResources().getString(R.string.Has_agreed_to_your_friend_request);
        String str2 = context.getResources().getString(R.string.agree);
        String str3 = context.getResources().getString(R.string.Request_to_add_you_as_a_friend);
        String str4 = context.getResources().getString(R.string.Apply_to_the_group_of);
        String str5 = context.getResources().getString(R.string.Has_agreed_to);
        final String str6 = context.getResources().getString(R.string.Has_refused_to);
        final InviteMessage msg = getItem(position);
        if (msg != null) {
            if (msg.getGroupId() != null) { // 显示群聊提示
                holder.groupContainer.setVisibility(View.VISIBLE);
                holder.groupname.setText(msg.getGroupName());
            } else {
                holder.groupContainer.setVisibility(View.GONE);
            }
            holder.reason.setText(msg.getReason());

            /**根据传过来的userid访问服务器获得蜂友详情*/
            HttpUtils httpUtils = new HttpUtils();
            MyRequestParams params = new MyRequestParams();
            params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(context));
            params.addQueryStringParameter("friendid", msg.getFrom());
            params.addQueryStringParameter("track", 1 + "");
            String url = params.myRequestParams(params);
            httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.USER_DETAIL + "?" + url, new RequestCallBack<String>() {

                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    UserDetailBean userDetailBean = UserDetailsActivity.paraseUserDetail(result);
                    holder.name.setText(userDetailBean.getData().getFriend().getNickname());
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });
//            holder.name.setText(msg.getFrom());
            // holder.time.setText(DateUtils.getTimestampString(new
            // Date(msg.getTime())));
            if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEAGREED) {
                holder.reject.setVisibility(View.INVISIBLE);
                holder.status.setVisibility(View.INVISIBLE);
                holder.reason.setText(str1);
                mLoginActivity = new LoginActivity();
                mLoginActivity.getFriend();//调用登录界面的getFriend()方法刷新我的蜂友

            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEINVITEED || msg.getStatus() == InviteMessage.InviteMesageStatus.BEAPPLYED) {
                holder.status.setVisibility(View.VISIBLE);
                holder.status.setEnabled(true);
//                holder.status.setBackgroundResource(android.R.drawable.btn_default);
                holder.status.setText(str2);
                if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEINVITEED) {
                    if (msg.getReason() == null) {
                        // 如果没写理由
                        holder.reason.setText(str3);
                    }
                } else { //入群申请
                    if (TextUtils.isEmpty(msg.getReason())) {
                        holder.reason.setText(str4 + msg.getGroupName());
                    }
                }
                /**点击拒绝隐藏两个按钮，赞不做操作*/
                holder.reject.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.reject.setVisibility(View.GONE);
                        holder.status.setText(str6);
                    }
                });
                // 设置点击事件
                holder.status.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // 同意别人发的好友请求
                        acceptInvitation(holder.status, msg);
                        holder.reject.setVisibility(View.GONE);
                        // 调用确认添加蜂友接口通知服务器
                        HttpUtils httpUtils = new HttpUtils();
                        MyRequestParams params1 = new MyRequestParams();
                        params1.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(context));
                        params1.addQueryStringParameter("friendid", msg.getFrom());
//                        params1.addBodyParameter("iden", iden);
                        String url = params1.myRequestParams(params1);
                        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.APPLYCONFIRM + "?" + url, new RequestCallBack<String>() {
                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                String result = responseInfo.result;
//                                Log.e(TAG, result);
                                ApplyConfirmFriend applyConfirmFriend = paraseApplyConfirmFriend(result);
                                if (applyConfirmFriend.getStatus() == 1) {
                                    //存入db
//                                    FriendDao friendDao = new FriendDao(context);
//                                    friendDao.setFriend(applyConfirmFriend.getData().getFriend());
                                    mLoginActivity = new LoginActivity();
                                    mLoginActivity.getFriend();
                                    Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(HttpException e, String s) {

                            }
                        });
                    }
                });
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.AGREED) {
                holder.reject.setVisibility(View.GONE);
                holder.status.setText(str5);
//                holder.status.setBackgroundDrawable(null);
                holder.status.setEnabled(false);
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.REFUSED) {
                holder.status.setText(str6);
//                holder.status.setBackgroundDrawable(null);
                holder.status.setEnabled(false);
            }

            // 设置用户头像
            String urls = UrlPools.QINIU + "avatar/" + msg.getFrom() + ".jpg";
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.head_portraits)         //加载开始默认的图片
                    .showImageForEmptyUri(R.drawable.head_portraits)     //url爲空會显示该图片，自己放在drawable里面的
                    .showImageOnFail(R.drawable.head_portraits)                //加载图片出现问题，会显示该图片
                    .cacheInMemory()                                               //缓存用
                    .cacheOnDisc()                                                    //缓存用
                    .build();
            imageLoader.displayImage(urls, holder.avator, options);

            /** 设置用户头像*/
            UserUtils.setUserAvatar(context, msg.getFrom(), holder.avator);
            /**设置用户头像点击事件，进入用户详情界面*/
            holder.avator.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtra("friendid", msg.getFrom());
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }


    /**
     * 同意好友请求或者群申请
     *
     * @param button
     * @param
     */
    private void acceptInvitation(final Button button, final InviteMessage msg) {
        final ProgressDialog pd = new ProgressDialog(context);
        String str1 = context.getResources().getString(R.string.Are_agree_with);
        final String str2 = context.getResources().getString(R.string.Has_agreed_to);
        final String str3 = context.getResources().getString(R.string.Agree_with_failure);
        pd.setMessage(str1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        new Thread(new Runnable() {
            public void run() {
                // 调用sdk的同意方法
                try {
                    if (msg.getGroupId() == null) //同意好友请求
                        EMChatManager.getInstance().acceptInvitation(msg.getFrom());
                    else //同意加群申请
                        EMGroupManager.getInstance().acceptApplication(msg.getFrom(), msg.getGroupId());
                    ((Activity) context).runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            pd.dismiss();
                            button.setText(str2);
                            msg.setStatus(InviteMessage.InviteMesageStatus.AGREED);
                            // 更新db
                            ContentValues values = new ContentValues();
                            values.put(InviteMessgeDao.COLUMN_NAME_STATUS, msg.getStatus().ordinal());
                            messgeDao.updateMessage(msg.getId(), values);
                            button.setEnabled(false);

                        }
                    });
                } catch (final Exception e) {
                    ((Activity) context).runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(context, str3 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        }).start();
    }

    /**
     * 点击确认返回json字段解析
     *
     * @param result
     * @return
     */
    private ApplyConfirmFriend paraseApplyConfirmFriend(String result) {
        Gson gson = new Gson();
        ApplyConfirmFriend applyConfirmFriend = gson.fromJson(result, ApplyConfirmFriend.class);
        return applyConfirmFriend;
    }

    private class ApplyConfirmFriend {

        /**
         * data : {"subTitleColor":"#666666","img":"http://www.hlfyb.com/Upload/Photo/Headpic/member_pic.jpg","placeholderImg":"widget://Res/avatar.jpg","friendid":"286cc02a8fd6a895","subTitle":"天亮了","titleColor":"#333333","letter":"D","id":"286cc02a8fd6a895","titleSize":"16","title":"D嗒滴","userid":"fdb28e02fb4bf683","subTitleSize":"14"}
         * status : 1
         * info :
         */
        private DataEntity data;
        private int status;
        private String info;

        public void setData(DataEntity data) {
            this.data = data;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public DataEntity getData() {
            return data;
        }

        public int getStatus() {
            return status;
        }

        public String getInfo() {
            return info;
        }

        public class DataEntity {
            private Friend friend;

            public Friend getFriend() {
                return friend;
            }

            public void setFriend(Friend friend) {
                this.friend = friend;
            }
        }
    }

    private static class ViewHolder {
        ImageView avator;
        TextView name;
        TextView reason;
        Button status;
        Button reject;
        LinearLayout groupContainer;
        TextView groupname;
        // TextView time;
    }

}

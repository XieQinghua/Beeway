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

package com.easemob.chatui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupInfo;
import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.UserDetailBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

public class GroupSimpleDetailActivity extends BaseActivity {
    private static final String TAG = "Group";
    private Button btn_add_group;
    private TextView tv_admin;
    private TextView tv_name;
    private TextView tv_introduction;
    private EMGroup group;
    private String groupid;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_simle_details);
        tv_name = (TextView) findViewById(R.id.name);
        tv_admin = (TextView) findViewById(R.id.tv_admin);
        btn_add_group = (Button) findViewById(R.id.btn_add_to_group);
        tv_introduction = (TextView) findViewById(R.id.tv_introduction);
        progressBar = (ProgressBar) findViewById(R.id.loading);

        EMGroupInfo groupInfo = (EMGroupInfo) getIntent().getSerializableExtra("groupinfo");
        String groupname = null;
        if (groupInfo != null) {
            groupname = groupInfo.getGroupName();
            groupid = groupInfo.getGroupId();
        } else {
            group = com.easemob.chatui.activity.PublicGroupsSeachActivity.searchedGroup;
            if (group == null)
                return;
            groupname = group.getGroupName();
            groupid = group.getGroupId();
        }

        tv_name.setText(groupname);


        if (group != null) {
            showGroupDetail();
            return;
        }
        new Thread(new Runnable() {

            public void run() {
                //从服务器获取详情
                try {
                    group = EMGroupManager.getInstance().getGroupFromServer(groupid);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            showGroupDetail();
                        }
                    });
                } catch (final EaseMobException e) {
                    e.printStackTrace();
                    final String st1 = getResources().getString(R.string.Failed_to_get_group_chat_information);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(GroupSimpleDetailActivity.this, st1 + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).start();

    }

    //加入群聊
    public void addToGroup(View view) {
        String st1 = getResources().getString(R.string.Is_sending_a_request);
        final String st2 = getResources().getString(R.string.Request_to_join);
        final String st3 = getResources().getString(R.string.send_the_request_is);
        final String st4 = getResources().getString(R.string.Join_the_group_chat);
        final String st5 = getResources().getString(R.string.Failed_to_join_the_group_chat);
        final ProgressDialog pd = new ProgressDialog(this);
//		getResources().getString(R.string)
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    //如果是membersOnly的群，需要申请加入，不能直接join
                    if (group.isMembersOnly()) {
                        EMGroupManager.getInstance().applyJoinToGroup(groupid, st2);
                    } else {
                        EMGroupManager.getInstance().joinGroup(groupid);
                    }
                    runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            if (group.isMembersOnly()) {
                                Toast.makeText(GroupSimpleDetailActivity.this, st3, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(GroupSimpleDetailActivity.this, st4, Toast.LENGTH_SHORT).show();
                            }
                            btn_add_group.setEnabled(false);
                        }
                    });
                } catch (final EaseMobException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(GroupSimpleDetailActivity.this, st5 + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void showGroupDetail() {
        progressBar.setVisibility(View.INVISIBLE);
        //获取详情成功，并且自己不在群中，才让加入群聊按钮可点击
        if (!group.getMembers().contains(EMChatManager.getInstance().getCurrentUser()))
            btn_add_group.setEnabled(true);
        tv_name.setText(group.getGroupName());
        /**根据userid向服务器请求获得群主名称*/
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(this));
        params.addQueryStringParameter("friendid", group.getOwner());
        params.addQueryStringParameter("track", 1 + "");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.USER_DETAIL + "?" + url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                UserDetailBean userDetailBean = UserDetailsActivity.paraseUserDetail(result);
                tv_admin.setText(userDetailBean.getData().getFriend().getNickname());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.e(s);
                tv_admin.setText("幕后黑手");
            }
        });
//        tv_admin.setText(group.getOwner());
        tv_introduction.setText(group.getDescription());
    }

    public void back(View view) {
        finish();
    }
}

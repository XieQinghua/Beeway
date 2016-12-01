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
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.thvc.beeway.R;
import com.thvc.beeway.Zing.MipcaActivityCapture;
import com.thvc.beeway.Zing.MyErweimaActivity;
import com.thvc.beeway.activity.SeachResultActivity;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.adapter.NearFriendAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.NearFriendBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.List;

/**
 * 添加蜂友
 * xieqinghua
 */

public class AddContactActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "AddContactActivity";
    private EditText editText;
    private TextView tv_change_page;
    private Button bt_search;
    private ImageView iv_other;
    private ListView lv_near_fengyou;
    private NearFriendAdapter mNearFriendAdapter;
    private String toAddUsername;
    private InputMethodManager inputMethodManager;
    private List<NearFriendBean.DataEntity.ListEntity> list;
    private ProgressDialog progressDialog;

    private int totalPage, p = 0;//总共页数、换一批附近的人页码，起始值为0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        editText = (EditText) findViewById(R.id.edit_note);
        iv_other = (ImageView) findViewById(R.id.iv_other);
        tv_change_page = (TextView) findViewById(R.id.tv_change_page);
        tv_change_page.setOnClickListener(this);
        bt_search = (Button) findViewById(R.id.bt_search);
        lv_near_fengyou = (ListView) findViewById(R.id.lv_near_fengyou);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        showNearFriend();
    }

    /**
     * 右上角弹出窗
     *
     * @param view
     */
    public void showPopupwindow(View view) {
        new PopupWindows(AddContactActivity.this, view);
    }

    private void showNearFriend() {
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", new BeewayApplication().getmUserid(AddContactActivity.this));
        params.addQueryStringParameter("longitude", GlobalParams.longitude);
        params.addQueryStringParameter("latitude", GlobalParams.latitude);
        params.addQueryStringParameter("p", p + "");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.NEARBY_FRIEND + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
//                Log.e(TAG, result);
                NearFriendBean myNearFriendBean = paraseNearFriendData(result);
                totalPage = myNearFriendBean.getData().getTotalPage();
                list = myNearFriendBean.getData().getList();
                mNearFriendAdapter = new NearFriendAdapter(list, AddContactActivity.this);
                lv_near_fengyou.setAdapter(mNearFriendAdapter);
                lv_near_fengyou.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /**进入好友详情界面**/
                        Intent intent = new Intent(AddContactActivity.this, UserDetailsActivity.class);
                        intent.putExtra("friendid", list.get(position).getSolevar());
                        startActivity(intent);
                    }
                });
                removeDialog(LOADING_DIALOG);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                LogUtils.i(msg);
            }
        });
    }

    /**
     * 附近的人json字段解析
     *
     * @param result
     * @return
     */
    private NearFriendBean paraseNearFriendData(String result) {
        Gson gson = new Gson();
        NearFriendBean myNearFriendBean = gson.fromJson(result, NearFriendBean.class);
        return myNearFriendBean;
    }

    /**
     * 查找contact
     *
     * @param v
     */
    public void searchContact(View v) {
        final String name = editText.getText().toString();
        String saveText = bt_search.getText().toString();
        if (getString(R.string.button_search).equals(saveText)) {
            toAddUsername = name;
            if (TextUtils.isEmpty(name)) {
                String st = getResources().getString(R.string.Please_enter_a_username);
                startActivity(new Intent(this, AlertDialog.class).putExtra("msg", st));
                return;
            } else {
                //跳转页面到搜索结果页面
                Intent intent = new Intent(AddContactActivity.this, SeachResultActivity.class);
                startActivity(intent.putExtra("msg", editText.getText().toString()));
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_page:
                if (p < totalPage - 1) {
                    p++;
                } else {
                    p = 0;
                }
                showNearFriend();
                break;
        }
    }

    /**
     * 右上角PopupWindows
     */
    public class PopupWindows extends PopupWindow {
        public PopupWindows(Context mContext, View parent) {
            View view = View.inflate(mContext, R.layout.popup_contact, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup_layout = (LinearLayout) view.findViewById(R.id.ll_popup_layout);
            LinearLayout erweima = (LinearLayout) view.findViewById(R.id.item_erweima);
            LinearLayout saoyisao = (LinearLayout) view.findViewById(R.id.item_saoyisao);
            LinearLayout contact = (LinearLayout) view.findViewById(R.id.item_mobile_contact);
            ll_popup_layout.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            //修改高度显示，解决被手机底部虚拟键挡住的问题
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //实例化一个ColorDrawable颜色为半透明
            setBackgroundDrawable(new ColorDrawable(0xb0000000));
            //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int height = view.findViewById(R.id.ll_popup_layout).getTop();
                    int y = (int) motionEvent.getY();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            update();

            erweima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AddContactActivity.this, MyErweimaActivity.class));
                    dismiss();
                }
            });
            saoyisao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AddContactActivity.this, MipcaActivityCapture.class));
                    dismiss();
                }
            });
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO 手机通讯录
                    dismiss();
                }
            });
        }
    }
}

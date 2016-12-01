package com.thvc.beeway.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.easemob.chat.EMContactManager;
import com.easemob.chatui.activity.AlertDialog;
import com.easemob.chatui.activity.ChatActivity;
import com.easemob.chatui.activity.ContactlistFragment;
import com.easemob.chatui.db.FriendDao;
import com.easemob.chatui.db.UserDao;
import com.easemob.chatui.utils.UserUtils;
import com.easemob.exceptions.EaseMobException;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.DelBean;
import com.thvc.beeway.bean.UserDetailBean;
import com.thvc.beeway.fragment.CustomerFragment;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;
import com.thvc.beeway.view.UserDefineScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：会话联系人用户详情页面
 * 创建人：谢庆华.
 * 创建时间：2015/9/8 9:45
 * 修改人：Administrator
 * 修改时间：2015/9/8 9:45
 * 修改备注：
 */
public class UserDetailsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "UserDetailsActivity";
    public static final String IMAGE_JUMP = "image";
    public static final String JUMP = "jump_";
    public static final int GET_VERICATION_MSG = 1;
    public static final int DEL_FENGYOU = 2;
    private RelativeLayout iv_layout, rl_layout_title;
    private Button bt_add_friend, bt_send_msg, bt_del_fengyou, bt_enter_member;
    private CircleImageView iv_user_img;
    private UserDefineScrollView sv_content;
    private ImageLoader circleimageLoader;
    private ImageView iv_cover, iv_track_image1, iv_track_image2, iv_track_image3, iv_track_go;
    private List<ImageView> imageViewList;
    private TextView tv_nick_name, tv_user_sex, tv_user_rank, tv_subtitle, tv_user_area, tv_user_xiangqu, tv_travle_num;
    private String userId, friendId, remark;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private ProgressDialog progressDialog;
    private HttpUtils httpUtils;
    ViewGroup.LayoutParams para;
    private int title_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initView();

        userId = BeewayApplication.getInstance().getmUserid(this);
        /**获得传过来的friendid，访问服务器并解析好友详情数据**/
        friendId = getIntent().getStringExtra("friendid");
        /**监听ScrollView下滑高度*/
        sv_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {//监听到ScrollView滚动事件
                    if (v.getScrollY() >= (para.height - title_height)) {
                        rl_layout_title.setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
                        rl_layout_title.getBackground().setAlpha(178);
                    } else {
                        rl_layout_title.setBackgroundColor(getResources().getColor(R.color.beeway_title_transparent));
                    }
                }
                return false;
            }
        });
        if (friendId.equals("null")) {
            Toast.makeText(UserDetailsActivity.this, "该用户不存在！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            /**设置头像和封面*/
            iv_user_img.setImageUrl(UrlPools.getFriendAvatarUrl(this, friendId), circleimageLoader);
            UserUtils.setUserCoverByday(this, friendId, iv_cover);
            /**访问服务器并解析好友详情数据**/
            getDetail();
        }
        bt_add_friend.setOnClickListener(this);
        bt_send_msg.setOnClickListener(this);
        bt_del_fengyou.setOnClickListener(this);
        bt_enter_member.setOnClickListener(this);
    }

    private void initView() {
        bt_add_friend = (Button) findViewById(R.id.bt_add_friend);
        bt_send_msg = (Button) findViewById(R.id.bt_send_msg);
        bt_del_fengyou = (Button) findViewById(R.id.bt_del_fengyou);
        bt_enter_member = (Button) findViewById(R.id.bt_enter_member);
        tv_nick_name = (TextView) findViewById(R.id.tv_nick_name);
        tv_user_sex = (TextView) findViewById(R.id.tv_user_sex);
        tv_user_rank = (TextView) findViewById(R.id.tv_user_rank);
        tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);
        tv_user_area = (TextView) findViewById(R.id.tv_user_area);
        tv_user_xiangqu = (TextView) findViewById(R.id.tv_user_xiangqu);
        tv_travle_num = (TextView) findViewById(R.id.tv_travle_num);
        iv_user_img = (CircleImageView) findViewById(R.id.iv_user_img);
        iv_cover = (ImageView) findViewById(R.id.iv_cover);
        rl_layout_title = (RelativeLayout) findViewById(R.id.rl_layout_title);
        sv_content = (UserDefineScrollView) findViewById(R.id.sv_content);
        title_height = (int) getResources().getDimension(R.dimen.height_top_bar);
        iv_layout = (RelativeLayout) findViewById(R.id.iv_layout);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        para = iv_layout.getLayoutParams();
        para.height = wm.getDefaultDisplay().getWidth() * 3 / 4;
        para.width = wm.getDefaultDisplay().getWidth();
        iv_layout.setLayoutParams(para);
        iv_track_image1 = (ImageView) findViewById(R.id.iv_track_image1);
        iv_track_image2 = (ImageView) findViewById(R.id.iv_track_image2);
        iv_track_image3 = (ImageView) findViewById(R.id.iv_track_image3);
        iv_track_go = (ImageView) findViewById(R.id.iv_track_go);
        imageViewList = new ArrayList<ImageView>();
        imageViewList.add(iv_track_image1);
        imageViewList.add(iv_track_image2);
        imageViewList.add(iv_track_image3);
        circleimageLoader = VolleyHepler.getInstance().getImageLoader();//加载圆形图片
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();//加载矩形图片
    }

    private void getDetail() {
        showDialog(LOADING_DIALOG);
        httpUtils = new HttpUtils();
        final MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", userId);
        params.addQueryStringParameter("friendid", friendId);
        params.addQueryStringParameter("track", 1 + "");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.USER_DETAIL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
//                        Log.e("TAG", result);
                        final UserDetailBean userDetailBean = paraseUserDetail(result);
                        tv_nick_name.setText(userDetailBean.getData().getFriend().getNickname());
                        if (userDetailBean.getData().getFriend().getSex().equals("1")) {
                            tv_user_sex.setText("男");
                            tv_user_sex.setBackgroundResource(R.drawable.corner_view_left);
                            if ((userDetailBean.getData().getLastWant()) != null) {
                                tv_user_xiangqu.setText("他想去" + userDetailBean.getData().getLastWant().getName());
                            } else {
                                tv_user_xiangqu.setText("他没有想去的地方");
                            }
                        } else {
                            tv_user_sex.setText("女");
                            tv_user_sex.setBackgroundResource(R.drawable.corner_view_left_sex);
                            if ((userDetailBean.getData().getLastWant()) != null) {
                                tv_user_xiangqu.setText("她想去" + userDetailBean.getData().getLastWant().getName());
                            } else {
                                tv_user_xiangqu.setText("她没有想去的地方");
                            }
                        }
                        tv_user_rank.setText(userDetailBean.getData().getLevelArr().getLvName());
                        tv_user_rank.setBackgroundResource(R.drawable.corner_view_right_white);
                        tv_subtitle.setText(userDetailBean.getData().getFriend().getContent());
                        tv_user_area.setText(userDetailBean.getData().getFriend().getCity());

                        tv_user_xiangqu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(UserDetailsActivity.this, CustomerFragment.class);
                                intent.putExtra("userid", friendId);
                                intent.putExtra("label", JUMP);
                                startActivity(intent);
                            }
                        });
                        tv_travle_num.setText(userDetailBean.getData().getTravleNum() + "篇");
                        tv_travle_num.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(UserDetailsActivity.this, MyTravelsActivity.class);
                                intent.putExtra("userid", friendId);
                                intent.putExtra("nickname", userDetailBean.getData().getFriend().getNickname());
                                intent.putExtra("count", JUMP);
                                startActivity(intent);

                            }
                        });
                        if (userDetailBean.getData().getLastTrack() != null) {
                            DisplayImageOptions options = new DisplayImageOptions.Builder()
                                    .cacheInMemory()//缓存用
                                    .cacheOnDisc()//缓存用
                                    .build();
                            final List<UserDetailBean.DataEntity.LastTrackEntity> list = userDetailBean.getData().getLastTrack();
                            for (int i = 0; i < list.size() && i < 3; i++) {
                                String url = UrlPools.QINIU + userDetailBean.getData().getLastTrack().get(i).getSingle().getFileurl() + "-Thumb640";
                                imageLoader.displayImage(url, imageViewList.get(i), options);
                                final String trackid = list.get(i).getId();
                                /**点击图片进入足迹详情*/
                                imageViewList.get(i).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(UserDetailsActivity.this, TrackDetailsActivity.class);
                                        intent.putExtra("id", trackid);
                                        intent.putExtra("userid", userId);
                                        startActivity(intent);
                                    }
                                });
                                /**点击箭头进入蜂友足迹列表*/
                                iv_track_go.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(UserDetailsActivity.this, MyTrackActivity.class);
                                        intent.putExtra("userid", friendId);
                                        intent.putExtra("nickname", userDetailBean.getData().getFriend().getNickname());
                                        intent.putExtra("count", JUMP);
                                        startActivity(intent);
                                    }
                                });

                            }

                        }
                        //判断是否为本人，如果是本人则显示进入个人中心
                        if (userDetailBean.getData().getIsmy() == 1) {
                            bt_enter_member.setVisibility(View.VISIBLE);
                        } else {
                            if (userDetailBean.getData().getIsfrid() == 1) {
                                //如果是蜂友隐藏添加蜂友按钮
                                bt_send_msg.setVisibility(View.VISIBLE);
                                bt_del_fengyou.setVisibility(View.VISIBLE);
                            } else {
                                bt_add_friend.setVisibility(View.VISIBLE);
                            }
                        }
                        removeDialog();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        //访问失败
                        LogUtils.i(s);
                    }
                }

        );

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_friend:
                Intent intent = new Intent(this, AlertDialog.class);
                intent.putExtra("title", "说点啥子吧");
                intent.putExtra("editTextShow", true);
                intent.putExtra("edit_text", "我是" + BeewayApplication.getInstance().getmNickname(this) + ":邀请您加我为好友。");
                startActivityForResult(intent, GET_VERICATION_MSG);
                break;
            case R.id.bt_send_msg:
                startActivity(new Intent(UserDetailsActivity.this, ChatActivity.class).putExtra("userId", friendId));
                finish();
                break;
            case R.id.bt_del_fengyou:
                //弹出提示框，是否继续删除蜂友操作
                intent = new Intent(UserDetailsActivity.this, AlertDialog.class);
                intent.putExtra("msg", "是否继续删除蜂友操作?");
                intent.putExtra("cancel", true);
                intent.putExtra("position", 1);
                startActivityForResult(intent, DEL_FENGYOU);
                break;
            case R.id.bt_enter_member:
                intent = new Intent(UserDetailsActivity.this, MemberCenterActivity.class);
                intent.putExtra("label", IMAGE_JUMP);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_VERICATION_MSG && resultCode == RESULT_OK) {
            remark = data.getStringExtra("edittext");
            if ((!remark.substring(0, 2).equals("我是")) && remark.indexOf(":") == -1) {
                remark = "我是" + BeewayApplication.getInstance().getmNickname(this) + ":邀请您加我为好友。" + data.getStringExtra("edittext");
            }
            applySend();
//            if (!remark.equals("")) {
//                Log.i(TAG, remark);
//                //发送好友申请
//
//            } else {
//                Toast.makeText(UserDetailsActivity.this, "请输入验证信息", Toast.LENGTH_SHORT).show();
//            }
        }
        if (requestCode == DEL_FENGYOU && resultCode == RESULT_OK) {
            try {
                // 删除db和内存中此用户的数据
                EMContactManager.getInstance().deleteContact(friendId);
                //删除环信列表好友
                UserDao dao = new UserDao(UserDetailsActivity.this);
                dao.deleteContact(friendId);
                /**向服务器发送请求解除蜂友关系*/
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(this));
                params.addQueryStringParameter("friendid", friendId);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.DEL_FRIEND + "?" + url, new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        ContactlistFragment contactlistFragment = new ContactlistFragment();
                        DelBean delBean = contactlistFragment.paraseDelData(result);
                        if (delBean.getStatus() == 1) {
                            /**删除本地db蜂友信息*/
                            FriendDao friendDao = new FriendDao(UserDetailsActivity.this);
                            friendDao.deleteFriend(friendId);
                            Toast.makeText(UserDetailsActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        LogUtils.e(s);
                    }
                });
            } catch (EaseMobException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 好友详情json解析
     *
     * @param result
     * @return
     */
    public static UserDetailBean paraseUserDetail(String result) {
        Gson gson = new Gson();
        UserDetailBean userDetailBean = gson.fromJson(result, UserDetailBean.class);
        return userDetailBean;
    }

    /**
     * 发送好友申请到服务器
     */
    private void applySend() {
        httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", new BeewayApplication().getmUserid(this));
        params.addQueryStringParameter("friendid", friendId);
        params.addQueryStringParameter("remark", remark);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.APPLYSEND + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ApplySendBean applySendBean = paraseapplySendData(result);
                if (applySendBean.getStatus() == 1) {
//                    Toast.makeText(UserDetailsActivity.this, "发送好友申请成功", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "发送好友申请到服务器成功");
                    addContact();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.i(s);
            }
        });
    }

    /**
     * 添加环信联系人操作
     */
    private void addContact() {

        if (BeewayApplication.getInstance().getContactList().containsKey(friendId)) {
            //提示已在好友列表中，无需添加
            if (EMContactManager.getInstance().getBlackListUsernames().contains(friendId)) {
                startActivity(new Intent(this, AlertDialog.class).putExtra("msg", "此用户已是你好友(被拉黑状态)，从黑名单列表中移出即可"));
                return;
            }
            String strin = getString(R.string.This_user_is_already_your_friend);
            startActivity(new Intent(this, AlertDialog.class).putExtra("msg", strin));
            return;
        }
        progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {

                try {
                    /**添加环信好友，并发送添加好友备注*/
                    EMContactManager.getInstance().addContact(friendId, remark);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 好友申请发送返回bean
     */
    public class ApplySendBean {

        /**
         * data : 82
         * status : 1
         * info :
         */
        private int data;
        private int status;
        private String info;

        public void setData(int data) {
            this.data = data;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getData() {
            return data;
        }

        public int getStatus() {
            return status;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 好友申请发送json字段的解析
     *
     * @param result
     */
    private ApplySendBean paraseapplySendData(String result) {
        Gson gson = new Gson();
        ApplySendBean applySendBean = gson.fromJson(result, ApplySendBean.class);
        LogUtils.i(applySendBean.getStatus() + "");
        return applySendBean;
    }
}

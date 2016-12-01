package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.easemob.chatui.utils.UserUtils;
import com.google.gson.Gson;
import com.thvc.beeway.R;
import com.thvc.beeway.Release.MessageCenterDao;
import com.thvc.beeway.Release.MessageCenterText;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.MessageCenterBean;
import com.thvc.beeway.bean.MyMessage;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;
import com.thvc.beeway.view.ListItemDelete;
import com.thvc.beeway.view.MyMessagePerson;
import com.thvc.beeway.view.SildeDelListvew;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目名称：Beeway
 * 类描述：我的消息中心
 * 创建人：颜松梁.
 * 创建时间：2015/10/9 16:40
 * 修改人：Administrator
 * 修改时间：2015/10/9 16:40
 * 修改备注：
 */
public class MyMessageActivity extends BaseActivity {

    private ArrayList<MyMessage> list;
    private ImageView iv_back;
    private SildeDelListvew activity_mymessage_listview;
    private String userid, addtime, status;
    private String id;//足迹游记 景点 众筹ID
    private String content;//推送内容
    private String type;//推送类型
    private String title;//标题
    private String fromuserid;//用户id
    private String fileurl;//图片地址
    private String image;// 里面包含 fileurl图片地址
    private String url;//网络图片地址
    private String appurlandroid;//Android市场地址
    private String httpurl;//网页地址
    private String vtitle;//网页标题
    private MyMessage myMessage;
    private MyMessageAdapter messageAdapter;
    private String footprintid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        list = new ArrayList<MyMessage>();//初始化list集合
        userid = BeewayApplication.getInstance().getmUserid(this);//拿到当前用户的用户名
        init();
    }

    /***********
     * 初始化控件
     ***********/
    public void init() {
        iv_back = (ImageView) this.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMessageActivity.this.finish();//返回按钮
            }
        });
        activity_mymessage_listview = (SildeDelListvew) this.findViewById(R.id.activity_mymessage_listview);//显示消息的list
        activity_mymessage_listview.setOnItemClickListener(new MyOnItemClickListener());//给消息设置点击事件
        select();
        messageAdapter = new MyMessageAdapter(list, MyMessageActivity.this, addtime);
        activity_mymessage_listview.setAdapter(messageAdapter);//给listview设置Adapter
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /******************************************
     * 查询数据库得到数据并解析放到集合对象中
     * ****************************************
     */
    public void select() {
        MessageCenterDao messageCenterDao = new MessageCenterDao(MyMessageActivity.this);
        List<MyMessagePerson> persons = messageCenterDao.select(userid);
        for (MyMessagePerson person : persons) {
            String contents = person.getContent();//拿到数据库里的josn字符串
            status = person.getStatuss();//拿到数据库里的标示
            addtime = person.getAddtime();//拿到数据库里的创建时间
            footprintid = person.getFootprintid();
            if (persons.size() > 0&&!status.equals("2")) {
                //解析得到的数据
                MessageCenterBean messageCenterBean = paraseAddTrackDate(contents);
                id = messageCenterBean.getId();//足迹游记 景点 众筹ID
                if (id == null) {
                    id = "";
                }
                content = messageCenterBean.getContent();//推送内容
                if (content == null) {
                    content = "";
                }
                type = messageCenterBean.getType();//推送类型
                if (type == null) {
                    type = "";
                }
                title = messageCenterBean.getTitle();//标题
                if (title == null) {
                    title = "";
                }
                fromuserid = messageCenterBean.getFromuserid();//用户id
                if (fromuserid == null) {
                    fromuserid = "";
                }
                fileurl = messageCenterBean.getFileurl();//图片地址
                if (fileurl == null) {
                    fileurl = "";
                }
                image = messageCenterBean.getImage();// 里面包含 fileurl图片地址
                if (image == null) {
                    image = "";
                }
                url = messageCenterBean.getUrl();//网络图片地址
                if (url == null) {
                    url = "";
                }
                appurlandroid = messageCenterBean.getAppurlandroid();//Android市场地址
                if (appurlandroid == null) {
                    appurlandroid = "";
                }
                httpurl = messageCenterBean.getHttpurl();//网页地址
                if (httpurl == null) {
                    httpurl = "";
                }
                vtitle = messageCenterBean.getVtitle();//网页标题
                if (vtitle == null) {
                    vtitle = "";
                }
                myMessage = new MyMessage(footprintid, id, content, type, title, fromuserid, fileurl, image, url, appurlandroid, httpurl, vtitle);
                list.add(myMessage);//得到数据放对象里
            }
        }
    }

    /**
     * 消息json字段的解析
     *
     * @param result
     * @return
     */
    private MessageCenterBean paraseAddTrackDate(String result) {
        Gson gson = new Gson();
        MessageCenterBean messageCenterBean = gson.fromJson(result, MessageCenterBean.class);
        return messageCenterBean;
    }

    /**
     * 消息的点击事件
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if ("TrackNote".equals(type)) {
                //足迹
                String printid = list.get(position).getFootprintid();
                startActivity(new Intent(MyMessageActivity.this, TrackDetailsActivity.class).putExtra("id", id).putExtra("userid", fromuserid));
                MessageCenterDao messageCenterDao = new MessageCenterDao(MyMessageActivity.this);
                messageCenterDao.imgupdate(userid, printid, "1");
            } else if ("TrackTravel".equals(type)) {
                //游记
                String printid = list.get(position).getFootprintid();
                startActivity(new Intent(MyMessageActivity.this, TravelsDetailsActivity.class).putExtra("id", id).putExtra("userid", fromuserid));
                MessageCenterDao messageCenterDao = new MessageCenterDao(MyMessageActivity.this);
                messageCenterDao.imgupdate(userid, printid, "1");
            } else if ("WantNote".equals(type)) {
                //众筹
                String printid = list.get(position).getFootprintid();
                startActivity(new Intent(MyMessageActivity.this, CustomerDetailsActivity.class).putExtra("id", id).putExtra("userid", fromuserid));
                MessageCenterDao messageCenterDao = new MessageCenterDao(MyMessageActivity.this);
                messageCenterDao.imgupdate(userid, printid, "1");
            } else if ("Scenic".equals(type)) {
                //景点
                String printid = list.get(position).getFootprintid();
                startActivity(new Intent(MyMessageActivity.this, ScenicDetailsActivity.class).putExtra("id", id).putExtra("userid", fromuserid));
                MessageCenterDao messageCenterDao = new MessageCenterDao(MyMessageActivity.this);
                messageCenterDao.imgupdate(userid, printid, "1");
            } else if ("huodong".equals(type)) {
                //其他
            }
        }
    }

    class MyMessageAdapter extends BaseAdapter {
        private ArrayList<MyMessage> list = new ArrayList<MyMessage>();
        private Context context;
        private String addtime;
        private ImageLoader imageLoader;

        public MyMessageAdapter(ArrayList<MyMessage> list, Context context, String addtime) {
            this.list = list;
            this.context = context;
            this.addtime = addtime;
            imageLoader = VolleyHepler.getInstance().getImageLoader();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                 vh = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.mymessageitem, null);
                vh.tv_messag_content = (TextView) convertView.findViewById(R.id.tv_messag_content);
                vh.tv_messag_time = (TextView) convertView.findViewById(R.id.tv_messag_time);
                vh.im_messag = (CircleImageView) convertView.findViewById(R.id.im_messag);
                vh.btn_del = (Button) convertView.findViewById(R.id.btn_del);
                vh.bankCard_item_layout=(ListItemDelete)convertView.findViewById(R.id.bankCard_item_layout);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv_messag_content.setText(list.get(position).getContent());
            vh.tv_messag_time.setText(addtime);
            String fromuserid = list.get(position).getFromuserid();
            String image = list.get(position).getImage();
            String fileurl = list.get(position).getFileurl();
            String url = list.get(position).getUrl();
            if (!"".equals(fileurl)) {

            } else if (!"".equals(image)) {
            } else if (!"".equals(fromuserid)) {
                vh.im_messag.setImageUrl(UrlPools.getFriendAvatarUrl(context, fromuserid), imageLoader);
            } else if (!"".equals(url)) {

            } else {
                vh.im_messag.setImageUrl(UrlPools.getAvatarUrl(context), VolleyHepler.getInstance().getImageLoader());
                UserUtils.setUserCover(context, BeewayApplication.getInstance().getmUserid(context), vh.im_messag);
            }
            vh.btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String printid = list.get(position).getFootprintid();
                    int num = position;
                    list.remove(num);
                    vh.bankCard_item_layout.setback();
                    MessageCenterDao messageCenterDao = new MessageCenterDao(MyMessageActivity.this);
                    messageCenterDao.imgupdate(userid, printid, "2");
                    messageAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_messag_content, tv_messag_time;
            CircleImageView im_messag;
            Button btn_del;
            ListItemDelete bankCard_item_layout;
        }
    }


}

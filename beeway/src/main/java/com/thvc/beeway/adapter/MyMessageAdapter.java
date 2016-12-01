package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.easemob.chatui.utils.UserUtils;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.bean.MyMessage;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/21.
 */
public class MyMessageAdapter extends BaseAdapter {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.mymessageitem, null);
            vh.tv_messag_content = (TextView) convertView.findViewById(R.id.tv_messag_content);
            vh.tv_messag_time = (TextView) convertView.findViewById(R.id.tv_messag_time);
            vh.im_messag = (CircleImageView) convertView.findViewById(R.id.im_messag);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_messag_content.setText(list.get(position).getContent());
        vh.tv_messag_time.setText(addtime);
       String  fromuserid=list.get(position).getFromuserid();
        String image =list.get(position).getImage();
        String fileurl= list.get(position).getFileurl();
        String url= list.get(position).getUrl();
        if (!"".equals(fileurl)) {

        } else if (!"".equals(image)) {
        } else if (!"".equals(fromuserid)) {
            vh.im_messag.setImageUrl(UrlPools.getFriendAvatarUrl(context, fromuserid), imageLoader);
        } else if (!"".equals(url)) {

        } else {
            vh.im_messag.setImageUrl(UrlPools.getAvatarUrl(context), VolleyHepler.getInstance().getImageLoader());
            UserUtils.setUserCover(context, BeewayApplication.getInstance().getmUserid(context), vh.im_messag);
        }
        return convertView;
    }


    class ViewHolder {
        TextView tv_messag_content, tv_messag_time;
        CircleImageView im_messag;

    }
}

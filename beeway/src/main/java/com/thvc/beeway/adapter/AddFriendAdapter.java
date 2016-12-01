package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lidroid.xutils.BitmapUtils;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.AddFriendBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：搜索添加蜂友的Adapter
 * 创建人：谢庆华.
 * 创建时间：2015/9/11 17:40
 * 修改人：Administrator
 * 修改时间：2015/9/11 17:40
 * 修改备注：
 */
public class AddFriendAdapter extends BaseAdapter {
    private List<AddFriendBean.DataEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private Context context;

    public AddFriendAdapter(List<AddFriendBean.DataEntity.ListEntity> list, Context context) {
        this.list = list;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_friend, null);
            vh.tv_user_name = (TextView) convertView.findViewById(R.id.tv_user_name);
            vh.tv_user_sex = (TextView) convertView.findViewById(R.id.tv_user_sex);
            vh.tv_user_rank = (TextView) convertView.findViewById(R.id.tv_user_rank);
            vh.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            vh.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.iv_friend_image = (com.thvc.beeway.view.CircleImageView) convertView.findViewById(R.id.iv_friend_image);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_user_name.setText(list.get(position).getNickname());
        if (list.get(position).getSex().equals("1")) {
            vh.tv_user_sex.setText("男");
            vh.tv_user_sex.setBackgroundResource(R.drawable.corner_view_left);
            vh.tv_user_rank.setBackgroundResource(R.drawable.corner_view_right);
        } else {
            vh.tv_user_sex.setText("女");
            vh.tv_user_sex.setBackgroundResource(R.drawable.corner_view_left_sex);
            vh.tv_user_rank.setBackgroundResource(R.drawable.corner_view_right_sex);
        }
        if (list.get(position).getLevel().equals("lv1") || list.get(position).getLevel().equals("lv2") || list.get(position).getLevel().equals("lv3")) {
            vh.tv_user_rank.setText("宅巢蜂");
        } else if (list.get(position).getLevel().equals("lv4") || list.get(position).getLevel().equals("lv5") || list.get(position).getLevel().equals("lv6")) {
            vh.tv_user_rank.setText("小蜜蜂");
        } else if (list.get(position).getLevel().equals("lv7") || list.get(position).getLevel().equals("lv8") || list.get(position).getLevel().equals("lv9")) {
            vh.tv_user_rank.setText("大黄蜂");
        } else if (list.get(position).getLevel().equals("lv10") || list.get(position).getLevel().equals("lv11") || list.get(position).getLevel().equals("lv12")) {
            vh.tv_user_rank.setText("巨人蜂");
        } else if (list.get(position).getLevel().equals("lv13") || list.get(position).getLevel().equals("lv14") || list.get(position).getLevel().equals("lv15")) {
            vh.tv_user_rank.setText("霸王蜂");
        } else if (list.get(position).getLevel().equals("lv16") || list.get(position).getLevel().equals("lv17") || list.get(position).getLevel().equals("lv18")) {
            vh.tv_user_rank.setText("超级蜂");
        } else if (list.get(position).getLevel().equals("lv19") || list.get(position).getLevel().equals("lv20") || list.get(position).getLevel().equals("lv21")) {
            vh.tv_user_rank.setText("至尊蜂");
        }
        if (list.get(position).getDistance() < 100) {
            vh.tv_distance.setText("少于100m");
        } else if (list.get(position).getDistance() < 1000) {
            vh.tv_distance.setText("附近" + list.get(position).getDistance() + "m");
        } else {
            double distance = (double) list.get(position).getDistance() / 1000;
            vh.tv_distance.setText(String.format("%.2f", distance) + "km");
        }
        if (list.get(position).getContent().equals("")) {
            vh.tv_content.setText("这家伙很懒，什么也没留下");
        } else {
            vh.tv_content.setText(list.get(position).getContent());
        }
        vh.iv_friend_image.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getSolevar()), imageLoader);
        return convertView;
    }

    class ViewHolder {
        TextView tv_user_name, tv_user_sex, tv_user_rank, tv_distance, tv_content;
        com.thvc.beeway.view.CircleImageView iv_friend_image;

    }
}

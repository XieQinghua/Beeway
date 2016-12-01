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
import com.thvc.beeway.bean.NearFriendBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：附近的人适配器
 * 创建人：谢庆华.
 * 创建时间：2015/9/18 10:29
 * 修改人：Administrator
 * 修改时间：2015/9/18 10:29
 * 修改备注：
 */
public class NearFriendAdapter extends BaseAdapter {
    private List<NearFriendBean.DataEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private Context context;

    public NearFriendAdapter(List<NearFriendBean.DataEntity.ListEntity> list, Context context) {
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
        vh.tv_user_rank.setText(list.get(position).getLvName());
        if (list.get(position).getDistance() < 100) {
            vh.tv_distance.setText("少于100m");
        } else if (list.get(position).getDistance() < 1000) {
            vh.tv_distance.setText("附近" + list.get(position).getDistance() + "m");
        } else {
            double distance = (double) list.get(position).getDistance() / 1000;
            vh.tv_distance.setText(String.format("%.2f", distance) + "km");
        }
        vh.tv_content.setText(list.get(position).getContent());

        vh.iv_friend_image.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getSolevar()), imageLoader);
        return convertView;
    }

    class ViewHolder {
        TextView tv_user_name, tv_user_sex, tv_user_rank, tv_distance, tv_content;
        com.thvc.beeway.view.CircleImageView iv_friend_image;

    }
}

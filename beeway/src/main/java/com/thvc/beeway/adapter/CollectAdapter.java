package com.thvc.beeway.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.CollectBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/10/12.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CollectAdapter extends BaseAdapter{
    private List<CollectBean.DataEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;
    private String id;
    public CollectAdapter(List<CollectBean.DataEntity.ListEntity> list,Context
            context) {
        this.list = list;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
        imageLoader = ImageLoader.getInstance().getInstance();
        imageLoader2 = VolleyHepler.getInstance().getImageLoader();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.activity_collect_item, null);
            vh.collect_nickname = (TextView) convertView.findViewById(R.id.collect_nickname);
            vh.collect_content = (TextView) convertView.findViewById(R.id.collect_content);
            vh.collect_addtime = (TextView) convertView.findViewById(R.id.collect_addtime);
            vh.collect_user_img = (CircleImageView) convertView.findViewById(R.id.collect_user_img);
            vh.collect_layout = (LinearLayout) convertView.findViewById(R.id.collect_layout);
            vh.collect_fileurl = (ImageView) convertView.findViewById(R.id.collect_fileurl);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.collect_addtime.setText(getStringTime(list.get(position).getAddtime()));
        vh.collect_content.setText(list.get(position).getContent());
        if ("TrackNote".equals(list.get(position).getTableid())) {
            vh.collect_nickname.setText(list.get(position).getUser().getNickname());
        } else if ("TrackTravel".equals(list.get(position).getTableid())) {
            vh.collect_nickname.setText(list.get(position).getUser().getNickname());
        } else if ("Scenic".equals(list.get(position).getTableid())) {
            vh.collect_nickname.setText(list.get(position).getData().getTitle());
        } else if ("WantNote".equals(list.get(position).getTableid())) {
            vh.collect_nickname.setText(list.get(position).getUser().getNickname());
        }

        vh.collect_user_img.setImageUrl(UrlPools.QINIU+ "avatar/" + list.get(position).getData().getUserid()+".jpg", imageLoader2);

        String url= UrlPools.QINIU + list.get(position).getImage().getFileurl()+"-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader.displayImage(url, vh.collect_fileurl,options);
        vh.collect_user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("friendid", list.get(position).getUserid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView collect_nickname, collect_content, collect_addtime;
        CircleImageView collect_user_img;
        LinearLayout collect_layout;
        ImageView collect_fileurl;
    }
    public String getStringTime(String cc_time) {
        String re_Strtime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));

        return re_Strtime;
    }
}

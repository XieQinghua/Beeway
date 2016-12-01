package com.thvc.beeway.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.MyTrackActivity;
import com.thvc.beeway.activity.TrackDetailsActivity;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.HomeBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/9/22.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class HomeTrackAdapter extends BaseAdapter {
    private List<HomeBean.DataEntity.TrackEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;

    public HomeTrackAdapter(List<HomeBean.DataEntity.TrackEntity> list, Context
            context) {
        this.list = list;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
        imageLoader = ImageLoader.getInstance();
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
                    R.layout.hometrack_listview_item, null);
            vh.hometrack_title = (TextView) convertView.findViewById(R.id.hometrack_title);
            vh.hometrack_more = (TextView) convertView.findViewById(R.id.hometrack_more);
            vh.hometrack_realname = (TextView) convertView.findViewById(R.id.hometrack_realname);
            vh.hometrack_realname2 = (TextView) convertView.findViewById(R.id.hometrack_realname2);
            vh.hometrack_top = (TextView) convertView.findViewById(R.id.hometrack_top);
            vh.hometrack_top2 = (TextView) convertView.findViewById(R.id.hometrack_top2);
            vh.hometrack_ImageView = (ImageView) convertView.findViewById(R.id.hometrack_ImageView);
            vh.hometrack_ImageView2 = (ImageView) convertView.findViewById(R.id.hometrack_ImageView2);
            vh.headpic = (CircleImageView) convertView.findViewById(R.id.hometrack_headpic);
            vh.headpic2 = (CircleImageView) convertView.findViewById(R.id.hometrack_headpic2);
            vh.hometrack_layout1 = (RelativeLayout) convertView.findViewById(R.id.hometrack_layout1);
            vh.hometrack_layout2 = (RelativeLayout) convertView.findViewById(R.id.hometrack_layout2);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .displayer(new RoundedBitmapDisplayer(2))       //图片圆角显示，值为整数
                .build();
        vh.hometrack_title.setText(list.get(position).getTitle());
        if (list.get(position).getList().size() >= 1) {
            vh.hometrack_realname.setText(list.get(position).getList().get(0).getNickname());
            vh.hometrack_top.setText(list.get(position).getList().get(0).getTop());
            String url = UrlPools.QINIU + list.get(position).getList().get(0).getAtlas().getData().get(0).getFileurl() + "-Thumb640";
            imageLoader.displayImage(url, vh.hometrack_ImageView, options);
            vh.headpic.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getList().get(0).getUserid()), imageLoader2);
            vh.headpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtra("friendid", list.get(position).getList().get(0).getUserid());
                    context.startActivity(intent);
                }
            });
            vh.hometrack_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent8 = new Intent(context, TrackDetailsActivity.class);
                    intent8.putExtra("id", list.get(position).getList().get(0).getId());
                    intent8.putExtra("userid", list.get(position).getList().get(0).getUserid());
                    context.startActivity(intent8);
                }
            });
            if (list.get(position).getList().size() >= 2) {
                vh.hometrack_realname2.setText(list.get(position).getList().get(1).getNickname());
                vh.hometrack_top2.setText(list.get(position).getList().get(1).getTop());
                String url1 = UrlPools.QINIU + list.get(position).getList().get(1).getAtlas().getData().get(0).getFileurl() + "-Thumb640";
                imageLoader.displayImage(url1, vh.hometrack_ImageView2, options);
                vh.headpic2.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getList().get(1).getUserid()), imageLoader2);
                vh.headpic2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, UserDetailsActivity.class);
                        intent.putExtra("friendid", list.get(position).getList().get(1).getUserid());
                        context.startActivity(intent);
                    }
                });
                vh.hometrack_ImageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent8 = new Intent(context, TrackDetailsActivity.class);
                        intent8.putExtra("id", list.get(position).getList().get(1).getId());
                        intent8.putExtra("userid", list.get(position).getList().get(1).getUserid());
                        context.startActivity(intent8);
                    }
                });
            } else if (list.get(position).getList().size() == 1) {
                vh.hometrack_layout2.setVisibility(View.GONE);
            }
        } else if (list.get(position).getList().size() <= 0) {
            vh.hometrack_layout1.setVisibility(View.GONE);
            vh.hometrack_layout2.setVisibility(View.GONE);
        }
        vh.hometrack_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyTrackActivity.class);
                intent.putExtra("Homeid", list.get(position).getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView hometrack_title, hometrack_more, hometrack_realname, hometrack_realname2, hometrack_top, hometrack_top2;
        ImageView hometrack_ImageView, hometrack_ImageView2;
        CircleImageView headpic, headpic2;
        RelativeLayout hometrack_layout1, hometrack_layout2;
    }
}

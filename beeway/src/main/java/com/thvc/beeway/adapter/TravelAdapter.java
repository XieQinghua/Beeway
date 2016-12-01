package com.thvc.beeway.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.easemob.chatui.utils.SmileUtils;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/8/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TravelAdapter extends BaseAdapter {
    private List<TravelBean.DataEntity.ListEntity2> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private Context context;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;

    public TravelAdapter(List<TravelBean.DataEntity.ListEntity2> list, Context
            context) {
        this.list = list;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
        imageLoader = VolleyHepler.getInstance().getImageLoader();
        imageLoader2 = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
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
                    R.layout.track_fragment_item_item, null);
            vh.tv_user_sex = (TextView) convertView.findViewById(R.id.tv_user_sex);
            vh.tv_user_rank = (TextView) convertView.findViewById(R.id.tv_user_rank);
            vh.name = (TextView) convertView.findViewById(R.id.travel_fragment_item_name);
            vh.comment = (TextView) convertView.findViewById(R.id.travel_fragment_item_commend);
            vh.content = (TextView) convertView.findViewById(R.id.travel_fragment_item_content);
            vh.good = (TextView) convertView.findViewById(R.id.travel_fragment_item_good);
            vh.share = (TextView) convertView.findViewById(R.id.travel_fragment_item_share);
            vh.time = (TextView) convertView.findViewById(R.id.travel_fragment_item_time);
            vh.imageView = (CircleImageView) convertView.findViewById(R.id.travel_fragment_item_image);
            vh.largeimage = (ImageView) convertView.findViewById(R.id.travel_fragment_item_largeimage);
            vh.collect = (TextView) convertView.findViewById(R.id.travel_fragment_item_collection);
            vh.relayout = (RelativeLayout) convertView.findViewById(R.id.track_fragment_item_Relayout);
            vh.relayout2 = (RelativeLayout) convertView.findViewById(R.id.travel_fragment_item_Relayout);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.relayout.setVisibility(View.GONE);
        vh.relayout2.setVisibility(View.VISIBLE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams para;
        para = vh.largeimage.getLayoutParams();
        para.height = wm.getDefaultDisplay().getWidth()*3/4;
        para.width = wm.getDefaultDisplay().getWidth();
        vh.largeimage.setLayoutParams(para);

        if ("1".equals(list.get(position).getSex())) {
            vh.tv_user_sex.setText("男");
            vh.tv_user_sex.setBackgroundResource(R.drawable.corner_view_left);
            vh.tv_user_rank.setBackgroundResource(R.drawable.corner_view_right);
        } else if ("2".equals(list.get(position).getSex())) {
            vh.tv_user_sex.setText("女");
            vh.tv_user_sex.setBackgroundResource(R.drawable.corner_view_left_sex);
            vh.tv_user_rank.setBackgroundResource(R.drawable.corner_view_right_sex);
        }
        vh.tv_user_rank.setText(list.get(position).getLevel().getLvName());
        vh.name.setText(list.get(position).getNickname());
        vh.share.setText(list.get(position).getShare());
        vh.good.setText(list.get(position).getPraise());
        vh.share.setText(list.get(position).getShare());

        vh.content.setText(SmileUtils.getSmiledText(context, list.get(position).getTitle()));
        vh.comment.setText(list.get(position).getComment());
        vh.time.setText(getStringTime(list.get(position).getModtime()));

        vh.collect.setText(list.get(position).getCollect());
        vh.imageView.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getUserid()), imageLoader);

        String url = UrlPools.QINIU + list.get(position).getImage().getSingle().getFileurl() + "-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader2.displayImage(url, vh.largeimage, options);
        vh.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("friendid", list.get(position).getUserid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public String getStringTime(String cc_time) {
        String re_Strtime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));

        return re_Strtime;
    }

    class ViewHolder {
        TextView name, time, content, share, good, comment, collect, tv_user_sex, tv_user_rank;
        CircleImageView imageView;
        ImageView largeimage;
        RelativeLayout relayout, relayout2;
    }
}

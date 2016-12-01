package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.AddTraveLlistJsonBean;
import com.thvc.beeway.utils.MyStatic;
import com.thvc.beeway.utils.VolleyHepler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2015/10/27.
 */
public class AddTravelListJsonAdapter extends BaseAdapter{
    private ArrayList<AddTraveLlistJsonBean.ListEntity> list;
    private Context context;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;

    public AddTravelListJsonAdapter(ArrayList<AddTraveLlistJsonBean.ListEntity> list, Context context) {
        this.list = list;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
        imageLoader = VolleyHepler.getInstance().getImageLoader();
        imageLoader2 = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
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
                    R.layout.addfootprintbean, null);
            vh.tv_content_time = (TextView) convertView.findViewById(R.id.tv_content_time);
            vh.tv_contents = (TextView) convertView.findViewById(R.id.tv_contents);
            vh.tv_times = (TextView) convertView.findViewById(R.id.tv_times);
            vh.im_foot = (ImageView) convertView.findViewById(R.id.im_foot);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_content_time.setText(getStringTime(list.get(position).getTime()));
        String url = list.get(position).getUrl();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader2.displayImage(url, vh.im_foot, options);
        vh.tv_contents.setText(list.get(position).getContent());
        vh.tv_times.setText(getStringTimes(list.get(position).getTime()));
        String trackid = list.get(position).getResumeId();
        String trackids = "";
        trackids += trackids + trackid + ",";
        MyStatic.trackid = trackids;

        return convertView;
    }

    class ViewHolder {
        TextView tv_content_time, tv_contents, tv_times;
        ImageView im_foot;
    }

    public String getStringTime(String cc_time) {
        String re_Strtime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy_MM-dd");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));
        return re_Strtime;
    }

    public String getStringTimes(String cc_time) {
        String re_Strtime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));
        return re_Strtime;
    }
}

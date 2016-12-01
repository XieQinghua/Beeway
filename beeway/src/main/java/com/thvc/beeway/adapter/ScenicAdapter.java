package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.ScenicBean;
import com.thvc.beeway.http.UrlPools;

import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/8/31.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ScenicAdapter extends BaseAdapter {
    private List<ScenicBean.DataEntity.DatasEntity> list;
    private ImageLoader imageLoader;
    private Context context;
    public ScenicAdapter(List<ScenicBean.DataEntity.DatasEntity> list,Context
            context) {
        this.list = list;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.activity_scenic_item, null);
            vh.name = (TextView) convertView.findViewById(R.id.tv_scenic_name);
            vh.address = (TextView) convertView.findViewById(R.id.tv_scenic_address);
            vh.content = (TextView) convertView.findViewById(R.id.tv_scenic_content);
            vh.imageView = (ImageView) convertView.findViewById(R.id.tv_scenic_image);
            vh.ratingBar = (RatingBar) convertView.findViewById(R.id.tv_scenic_rating);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.name.setText(list.get(position).getTitle());
        vh.address.setText(list.get(position).getAddress());
        vh.content.setText(list.get(position).getFeature());
        vh.ratingBar.setRating(Float.parseFloat(list.get(position).getScore()));
        String url = UrlPools.QINIU + list.get(position).getSingle().getFileurl()+"-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader.displayImage(url, vh.imageView,options);
        return convertView;
    }

    class ViewHolder {
        TextView name, address, content;
        ImageView imageView;
        RatingBar ratingBar;
    }
}

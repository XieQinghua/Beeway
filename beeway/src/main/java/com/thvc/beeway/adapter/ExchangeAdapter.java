package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.ExChangeRecordBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyStatic;

import java.util.List;

/**
 * Created by Administrator on 2015/9/30.
 */
public class ExchangeAdapter extends BaseAdapter {
    private List<ExChangeRecordBean.DataEntity.ListEntity> list;
    private Context context;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;


    public  ExchangeAdapter(List<ExChangeRecordBean.DataEntity.ListEntity> list,Context context){
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.exchange_item, null);
            vh.exchange_image = (ImageView) convertView.findViewById(R.id.exchange_image);
            vh.exchange_content = (TextView) convertView.findViewById(R.id.exchange_content);
            vh.tv_exchange = (TextView) convertView.findViewById(R.id.tv_exchange);
            vh.exchange_number = (TextView) convertView.findViewById(R.id.exchange_number);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.exchange_content.setText(list.get(position).getTitle());
        vh.tv_exchange.setText(list.get(position).getCredit()+"蜂蜜");

        String issend = list.get(position).getIssend();
        String express = list.get(position).getExpress();
        String billno  = list.get(position).getBillno();
        String pid = list.get(position).getPid();
        String logid = list.get(position).getId();
        MyStatic.pid=pid;
        MyStatic.logid=logid;

        if (issend.equals("1")){
            vh.exchange_number.setText("状态："+"审核中");
        }else if (issend.equals("2")){
            vh.exchange_number.setText("状态："+"未发货");
        }else if (issend.equals("3")){
            vh.exchange_number.setText("快递："+express+"/n"+"单号："+billno+"/n"+"已发送");
        }else {
        }
        String url = UrlPools.QINIU +list.get(position).getImage().get(0).getFileurl()+"-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .displayer(new RoundedBitmapDisplayer(5))       //图片圆角显示，值为整数
                .build();
        imageLoader2.displayImage(url, vh.exchange_image, options);

        return convertView;
    }
    class ViewHolder {
        TextView exchange_content, tv_exchange,exchange_number;
        ImageView exchange_image;
    }

}

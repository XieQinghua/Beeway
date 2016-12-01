package com.thvc.beeway.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.ExchangeActivity;
import com.thvc.beeway.bean.IntegralBean;
import com.thvc.beeway.http.UrlPools;

import java.util.List;

/**
 * Created by Administrator on 2015/9/28.
 */
public class IntegralAdapter extends BaseAdapter {
    private List<IntegralBean.DataEntity.ListEntity> list;
    private Context context;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;

    public IntegralAdapter(List<IntegralBean.DataEntity.ListEntity> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.integral_item, null);
            vh.integeral_image = (ImageView) convertView.findViewById(R.id.integeral_image);
            vh.integeral_content = (TextView) convertView.findViewById(R.id.integeral_content);
            vh.tv_integeral = (TextView) convertView.findViewById(R.id.tv_integeral);
            vh.integeral_number = (TextView) convertView.findViewById(R.id.integeral_number);
            vh.integeral_has = (TextView) convertView.findViewById(R.id.integeral_has);
            vh.integeral_exchange = (Button) convertView.findViewById(R.id.integeral_exchange);
            vh.integeral_exchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = list.get(position).getId();
                    Intent intet = new Intent(context, ExchangeActivity.class);
                    intet.putExtra("id", id);
                    context.startActivity(intet);
                }
            });

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.integeral_content.setText(list.get(position).getTitle());
        vh.tv_integeral.setText(list.get(position).getCredit() + "蜂蜜");
        vh.integeral_number.setText("数量：" + list.get(position).getTotal());
        vh.integeral_has.setText("已兑：" + list.get(position).getBuynum());
        String url = UrlPools.QINIU + list.get(position).getImage().get(0).getFileurl() + "-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .displayer(new RoundedBitmapDisplayer(5))       //图片圆角显示，值为整数
                .build();
        imageLoader2.displayImage(url, vh.integeral_image, options);
        return convertView;
    }


    class ViewHolder {
        TextView tv_integeral, integeral_content, integeral_number, integeral_has;
        ImageView integeral_image;
        Button integeral_exchange;
    }
}

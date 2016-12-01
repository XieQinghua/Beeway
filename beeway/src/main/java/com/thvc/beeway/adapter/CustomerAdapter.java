package com.thvc.beeway.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.chatui.utils.SmileUtils;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.CustomerBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/9/1.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CustomerAdapter extends BaseAdapter {
    private List<CustomerBean.DataEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;

    public CustomerAdapter(List<CustomerBean.DataEntity.ListEntity> list, Context
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
                    R.layout.fragment_customer_item, null);
            vh.content = (TextView) convertView.findViewById(R.id.customer_item_content);
            vh.nickname = (TextView) convertView.findViewById(R.id.customer_item_nickname);
            vh.customer = (TextView) convertView.findViewById(R.id.customer_item_customer);
            vh.largeimage = (ImageView) convertView.findViewById(R.id.customer_item_largeimage);
            vh.head = (CircleImageView) convertView.findViewById(R.id.customer_item_head);
            vh.progressBar = (ProgressBar) convertView.findViewById(R.id.customer_item_progressbar);
            vh.time = (TextView) convertView.findViewById(R.id.customer_item_time);
            vh.t1 = (TextView) convertView.findViewById(R.id.customer_item_t1);
            vh.t2 = (TextView) convertView.findViewById(R.id.customer_item_t2);

            vh.relative = (RelativeLayout) convertView.findViewById(R.id.customer_item_relative);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.content.setText(SmileUtils.getSmiledText(context, list.get(position).getContent()));
        vh.nickname.setText(list.get(position).getNickname());
        vh.time.setText(getStringTime(list.get(position).getAddtime()));
        vh.head.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getUserid()), imageLoader2);
        String url = UrlPools.QINIU + list.get(position).getSingle().getFileurl() + "-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader.displayImage(url, vh.largeimage, options);

        vh.t1.setText(list.get(position).getSprice());
        vh.t2.setText(list.get(position).getMprice());
        String mprice = list.get(position).getMprice().substring(0, list.get(position).getMprice().indexOf("."));
        String sprice = list.get(position).getSprice().substring(0, list.get(position).getSprice().indexOf("."));
        if (!"0".equals(mprice) && !"0".equals(sprice)) {
            int dl = (Integer.parseInt(mprice) * 100) / Integer.parseInt(sprice);
            vh.progressBar.setProgress(dl);
        } else {
            vh.progressBar.setProgress(0);
        }
        if ("1".equals(list.get(position).getStatus())) {
            vh.customer.setText("筹集完成");
            vh.relative.setBackgroundResource(R.drawable.customer_stop);
        } else {
            vh.customer.setText("筹集中");
            vh.relative.setBackgroundResource(R.drawable.customer_start);

        }
        vh.head.setOnClickListener(new View.OnClickListener() {
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
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));
        return re_Strtime;
    }


    class ViewHolder {
        TextView content, nickname, customer, time, t1, t2;
        CircleImageView head;
        ImageView largeimage;
        ProgressBar progressBar;
        RelativeLayout relative;
    }
}

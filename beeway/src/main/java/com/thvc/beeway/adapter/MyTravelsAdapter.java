package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/26.
 */
public class MyTravelsAdapter extends BaseAdapter {
    private ArrayList<TravelBean.DataEntity.ListEntity2> lists = new ArrayList<>();
    private Context context;
    private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;

    public MyTravelsAdapter(ArrayList<TravelBean.DataEntity.ListEntity2> lists, Context context) {
        this.lists = lists;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
        imageLoader = VolleyHepler.getInstance().getImageLoader();
        imageLoader2 = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
    }

    public Map<Integer, Boolean> getCheckMap() {
        return this.isCheckMap;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
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
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.mytravels_adapter, null);
            vh.tv_mytravel_time = (TextView) convertView.findViewById(R.id.tv_mytravel_time);
            vh.tv_mytravel_con = (TextView)convertView.findViewById(R.id.tv_mytravel_con);
            vh.tv_mytravel_address = (TextView)convertView.findViewById(R.id.tv_mytravel_address);
            vh.mytravel_chec_box = (CheckBox)convertView.findViewById(R.id.mytravel_chec_box);
            vh.im_mytravel_image = (ImageView)convertView.findViewById(R.id.im_mytravel_image);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_mytravel_time.setText(lists.get(position).getAddtime());
        vh.tv_mytravel_con.setText(lists.get(position).getTitle());
        vh.tv_mytravel_address.setText(lists.get(position).getDescription());
        String url = UrlPools.QINIU + lists.get(position).getImage().getSingle().getFileurl() + "-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader2.displayImage(url, vh.im_mytravel_image, options);

        vh.mytravel_chec_box
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView, boolean isChecked) {
                                /*
								 * 将选择项加载到map里面寄存
								 */
                        isCheckMap.put(position, isChecked);
                    }
                });

        return convertView;
    }


    public String getStringTimes(String cc_time) {
        String re_Strtime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(" MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));
        return re_Strtime;
    }


    class ViewHolder {
        TextView tv_mytravel_time, tv_mytravel_con, tv_mytravel_address;
        ImageView im_mytravel_image;
        CheckBox mytravel_chec_box;
    }
}

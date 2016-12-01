package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.MyWalletBean;
import com.thvc.beeway.utils.VolleyHepler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/9/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MyWalletAdapter extends BaseAdapter{
    private List<MyWalletBean.DataEntity.MoneyListEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;
    public MyWalletAdapter(List<MyWalletBean.DataEntity.MoneyListEntity.ListEntity> list,Context
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.mywallet_listview_item, null);
            vh.myWallet_item_remark = (TextView) convertView.findViewById(R.id.myWallet_item_remark);
            vh.myWallet_item_val = (TextView) convertView.findViewById(R.id.myWallet_item_val);
            vh.myWallet_item_addtime = (TextView) convertView.findViewById(R.id.myWallet_item_addtime);
            vh.myWallet_item_status = (TextView) convertView.findViewById(R.id.myWallet_item_status);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.myWallet_item_addtime.setText(getStringTime(list.get(position).getAddtime()));
        vh.myWallet_item_remark.setText(list.get(position).getRemark());
        if ("1".equals(list.get(position).getAct())) {
            vh.myWallet_item_val.setText("+"+list.get(position).getVal());
        }else if ("2".equals(list.get(position).getAct())){
            vh.myWallet_item_val.setText("-"+list.get(position).getVal());
        }
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
        TextView myWallet_item_remark, myWallet_item_val, myWallet_item_addtime, myWallet_item_status;
    }
}

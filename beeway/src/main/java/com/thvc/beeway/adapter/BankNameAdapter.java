package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thvc.beeway.R;
import com.thvc.beeway.bean.BankCardBean;
import com.thvc.beeway.utils.VolleyHepler;

import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/9/29.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class BankNameAdapter extends BaseAdapter{
    private List<BankCardBean.DataEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;
    public BankNameAdapter(List<BankCardBean.DataEntity> list,Context
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
            vh.bankName_item_remark = (TextView) convertView.findViewById(R.id.bankName_item_remark);
            vh.bankName_item_layout = (RelativeLayout) convertView.findViewById(R.id.bankName_item_layout);
            vh.myWallet_item_layout = (RelativeLayout) convertView.findViewById(R.id.myWallet_item_layout);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.bankName_item_layout.setVisibility(View.VISIBLE);
        vh.myWallet_item_layout.setVisibility(View.GONE);
        vh.bankName_item_remark.setText(list.get(position).getBankname()+"("+list.get(position).getCardnumber().substring(list.get(position).getCardnumber().length()-5,list.get(position).getCardnumber().length()-1)+")");
        return convertView;
    }

    class ViewHolder {
        TextView bankName_item_remark;
        RelativeLayout bankName_item_layout,myWallet_item_layout;
    }
}

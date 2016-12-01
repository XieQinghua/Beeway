package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.bean.PickRecordBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/9/29.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class PickRecordAdapter extends BaseAdapter{
    private List<PickRecordBean.DataEntity.ListEntity> list;
    private Context context;
    public PickRecordAdapter(List<PickRecordBean.DataEntity.ListEntity> list,Context
            context) {
        this.list = list;
        this.context = context;
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
            vh.pick_item_remark = (TextView) convertView.findViewById(R.id.pick_item_remark);
            vh.pick_item_val = (TextView) convertView.findViewById(R.id.pick_item_val);
            vh.pick_item_addtime = (TextView) convertView.findViewById(R.id.pick_item_addtime);
            vh.pick_item_status = (TextView) convertView.findViewById(R.id.pick_item_status);
            vh.myWallet_item_layout = (RelativeLayout) convertView.findViewById(R.id.myWallet_item_layout);
            vh.pick_item_layout = (RelativeLayout) convertView.findViewById(R.id.pick_item_layout);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.pick_item_layout.setVisibility(View.VISIBLE);
        vh.myWallet_item_layout.setVisibility(View.GONE);
        vh.pick_item_addtime.setText(getStringTime(list.get(position).getAddtime()));
        vh.pick_item_remark.setText(list.get(position).getBankname()+"("+list.get(position).getCardnumber()+")("+list.get(position).getOwners()+")");
        vh.pick_item_val.setText(list.get(position).getPickfee());
        if ("1".equals(list.get(position).getStatus())) {
            vh.pick_item_status.setText("已打款");
        }else if ("2".equals(list.get(position).getStatus())){
            vh.pick_item_status.setText("正在进行中");
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
        TextView pick_item_remark, pick_item_val, pick_item_addtime, pick_item_status;
        RelativeLayout myWallet_item_layout,pick_item_layout;
    }
}

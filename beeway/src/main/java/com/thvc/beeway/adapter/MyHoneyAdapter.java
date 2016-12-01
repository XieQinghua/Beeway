package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.thvc.beeway.R;
import com.thvc.beeway.bean.MyHoneyBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/9/29.
 */
public class MyHoneyAdapter  extends BaseAdapter{

    private List<MyHoneyBean.DataEntity.ListEntity> list;
    private Context context;

    public  MyHoneyAdapter(List<MyHoneyBean.DataEntity.ListEntity> list,Context context){
        this.list = list;
        this.context = context;

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
                    R.layout.myhoner_item, null);
            vh.tv_content_a = (TextView)convertView.findViewById(R.id.tv_content_a);
            vh.tv_content_times = (TextView)convertView.findViewById(R.id.tv_content_times);
            vh.tv_jifen = (TextView)convertView.findViewById(R.id.tv_jifen);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_content_a.setText(list.get(position).getInfo());
        vh.tv_content_times.setText(getStringTime(list.get(position).getAddtime()));
        String scoreType = list.get(position).getScoreType();
        if (scoreType.equals("2")){
            String scoreNum = list.get(position).getScoreNum();
            vh.tv_jifen.setText("-"+scoreNum);
            vh.tv_jifen.setTextColor(context.getResources().getColor(R.color.red));
        }else if(scoreType.equals("1")){
            vh.tv_jifen.setText("+"+list.get(position).getScoreNum());
            vh.tv_jifen.setTextColor(context.getResources().getColor(R.color.black));
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

    class ViewHolder{
     TextView tv_content_a,tv_content_times,tv_jifen;
    }
}

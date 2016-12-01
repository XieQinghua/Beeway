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
import com.thvc.beeway.bean.TreasureBean;
import com.thvc.beeway.utils.VolleyHepler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/10/15.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TreasureAdapter extends BaseAdapter{
    private List<TreasureBean.DataEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;
    private String id;
    public TreasureAdapter(List<TreasureBean.DataEntity.ListEntity> list,Context
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
                    R.layout.treasure_listview_item, null);
            vh.lv_addtime = (TextView) convertView.findViewById(R.id.lv_addtime);
            vh.lv_prompt = (TextView) convertView.findViewById(R.id.lv_prompt);
            vh.lv_content = (TextView) convertView.findViewById(R.id.lv_content);
            vh.lv_finds = (TextView) convertView.findViewById(R.id.lv_finds);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.lv_addtime.setText(getStringTime(list.get(position).getAddtime())+" 共有"+list.get(position).getFinds()+"人寻过此宝藏");
        vh.lv_prompt.setText("      宝贝提示："+list.get(position).getPrompt());
        vh.lv_content.setText("      宝贝内容："+list.get(position).getContent());
        if ("0".equals(list.get(position).getFinds())) {
            vh.lv_finds.setText("挖到宝藏的人：您埋的宝藏太深了，没被找着！！！");
        }else {
            vh.lv_finds.setText("挖到宝藏的人：" + list.get(position).getFinds());
        }
        return convertView;
    }

    class ViewHolder {
        TextView lv_addtime, lv_prompt, lv_content,lv_finds;
    }
    public String getStringTime(String cc_time) {
        String re_Strtime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_Strtime = sdf.format(new Date(lcc_time * 1000L));

        return re_Strtime;
    }
}

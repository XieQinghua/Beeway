package com.thvc.beeway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.bean.BankCardBean;
import com.thvc.beeway.bean.IsCollectDataBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.ListItemDelete;

import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/9/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class BankCardAdapter extends BaseAdapter {
    private List<BankCardBean.DataEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.android.volley.toolbox.ImageLoader imageLoader2;
    private Context context;
    private String id;

    public BankCardAdapter(List<BankCardBean.DataEntity> list, Context
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
            vh.bankCard_item_owners = (TextView) convertView.findViewById(R.id.bankCard_item_owners);
            vh.bankCard_item_bankname = (TextView) convertView.findViewById(R.id.bankCard_item_bankname);
            vh.bankCard_item_cardnumber = (TextView) convertView.findViewById(R.id.bankCard_item_cardnumber);
            vh.bankCard_item_layout = (ListItemDelete) convertView.findViewById(R.id.bankCard_item_layout);
            vh.myWallet_item_layout = (RelativeLayout) convertView.findViewById(R.id.myWallet_item_layout);
            vh.btndel = (Button) convertView.findViewById(R.id.btndel);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.bankCard_item_layout.setVisibility(View.VISIBLE);
        vh.myWallet_item_layout.setVisibility(View.GONE);
        vh.bankCard_item_owners.setText(list.get(position).getOwners());
        vh.bankCard_item_bankname.setText(list.get(position).getBankname());
        vh.bankCard_item_cardnumber.setText(list.get(position).getCardnumber());
        id = list.get(position).getId();
        vh.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankDel();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView bankCard_item_owners, bankCard_item_bankname, bankCard_item_cardnumber;
        RelativeLayout myWallet_item_layout;
        ListItemDelete bankCard_item_layout;
        Button btndel;
    }

    public void bankDel() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("id", id);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.BANKDEL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("  bankDel result" + result);
//                        IsCollectDataBean bankAdd = bankDel(result);
//                        if (bankAdd.getStatus()==1){
//                            Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(context,BankCardActivity.class);
//                            intent.putExtra("TYPE","ADD");
//                            context.startActivity(intent);
//                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    public IsCollectDataBean bankDel(String result) {
        Gson gson = new Gson();
        IsCollectDataBean bankAdd = gson.fromJson(result, IsCollectDataBean.class);
        return bankAdd;
    }
}

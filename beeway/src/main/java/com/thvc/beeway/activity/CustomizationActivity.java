package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/10/12.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CustomizationActivity extends BaseActivity {
    private Context context = this;
    private TextView head2_title, head2_address;
    private ImageView head2_back;
    private Button customization_bt1, customization_bt2, customization_bt3, customization_bt4, customization_bt5, customization_bt6,
            money_bt1, money_bt2, money_bt3, money_bt4, money_bt5;
    private String keywords = "", days = "", money = "",type="";
    private ListView listView;
    private String[] DAYS = {"6-10天", "10-15天", "15-20天", "20-25天", "25-30天", "不限"};
    private String[] MONEY = {"0.5-1万", "1-2万", "2-3万", "3-5万", "5-10万", "不限"};
    private UserAdapter adapter;
    private List<String> list = new ArrayList<>();
    private EditText customization_et;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_customization);
        init();

    }

    public void init() {
        listView = (ListView) findViewById(R.id.customization_listView);
        customization_et = (EditText) findViewById(R.id.customization_et);
        customization_bt1 = (Button) findViewById(R.id.customization_bt1);
        customization_bt2 = (Button) findViewById(R.id.customization_bt2);
        customization_bt3 = (Button) findViewById(R.id.customization_bt3);
        customization_bt4 = (Button) findViewById(R.id.customization_bt4);
        customization_bt5 = (Button) findViewById(R.id.customization_bt5);
        customization_bt6 = (Button) findViewById(R.id.customization_bt6);
        money_bt1 = (Button) findViewById(R.id.money_bt1);
        money_bt2 = (Button) findViewById(R.id.money_bt2);
        money_bt3 = (Button) findViewById(R.id.money_bt3);
        money_bt4 = (Button) findViewById(R.id.money_bt4);
        money_bt5 = (Button) findViewById(R.id.money_bt5);
        head2_title = (TextView) findViewById(R.id.head2_title);
        head2_address = (TextView) findViewById(R.id.head2_address);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        head2_address.setText("");
        head2_title.setText("订制旅程");
        head2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] DAYS = {"6,10", "10,15", "15,20", "20,25", "25,30", ""};
                String[] MONEY = {"5000,10000", "10000,20000", "20000,30000", "30000,50000", "50000,100000", ""};
                if ("days".equals(type)){
                    days = DAYS[i];
                }else  if ("money".equals(type)){
                    money = MONEY[i];
                }
                listView.setVisibility(View.GONE);
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customization_bt1:
                days = "1";
                listView.setVisibility(View.GONE);
                customization_bt1.setBackgroundResource(R.drawable.bt_blue_shape);
                customization_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt6.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.customization_bt2:
                days = "2";
                listView.setVisibility(View.GONE);
                customization_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt2.setBackgroundResource(R.drawable.bt_blue_shape);
                customization_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt6.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.customization_bt3:
                days = "3";
                listView.setVisibility(View.GONE);
                customization_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt3.setBackgroundResource(R.drawable.bt_blue_shape);
                customization_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt6.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.customization_bt4:
                days = "4";
                listView.setVisibility(View.GONE);
                customization_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt4.setBackgroundResource(R.drawable.bt_blue_shape);
                customization_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt6.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.customization_bt5:
                days = "5";
                listView.setVisibility(View.GONE);
                customization_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt5.setBackgroundResource(R.drawable.bt_blue_shape);
                customization_bt6.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.customization_bt6:
                list.clear();
                for (int i=0;i<DAYS.length;i++) {
                    list.add(DAYS[i]);
                }
                type = "days";
                adapter = new UserAdapter(list);
                listView.setAdapter(adapter);
                listView.setVisibility(View.VISIBLE);
                customization_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                customization_bt6.setBackgroundResource(R.drawable.bt_blue_shape);
                break;
            case R.id.money_bt1:
                money = "300,500";
                listView.setVisibility(View.GONE);
                money_bt1.setBackgroundResource(R.drawable.bt_blue_shape);
                money_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);

                break;
            case R.id.money_bt2:
                money = "500,1000";
                listView.setVisibility(View.GONE);
                money_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt2.setBackgroundResource(R.drawable.bt_blue_shape);
                money_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.money_bt3:
                money = "1000,2000";
                listView.setVisibility(View.GONE);
                money_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt3.setBackgroundResource(R.drawable.bt_blue_shape);
                money_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.money_bt4:
                money = "2000,5000";
                listView.setVisibility(View.GONE);
                money_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt4.setBackgroundResource(R.drawable.bt_blue_shape);
                money_bt5.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.money_bt5:
                list.clear();
                for (int i=0;i<MONEY.length;i++) {
                    list.add(MONEY[i]);
                }
                type = "money";
                adapter = new UserAdapter(list);
                listView.setAdapter(adapter);
                listView.setVisibility(View.VISIBLE);
                money_bt1.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt2.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt3.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt4.setBackgroundResource(R.drawable.background_view_rounded_while);
                money_bt5.setBackgroundResource(R.drawable.bt_blue_shape);
                break;
            case R.id.money_OK:
                keywords = customization_et.getText().toString().trim();
                Intent intent = new Intent(context,MyTravelsActivity.class);
                intent.putExtra("count","customization");
                intent.putExtra("keywords",keywords);
                intent.putExtra("days",days);
                intent.putExtra("money",money);
                startActivity(intent);
                break;
        }
    }

    class UserAdapter extends BaseAdapter {
        private List<String>  list;
        public UserAdapter(List<String> list) {
            this.list = list;
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
                        R.layout.customization_item, null);
                vh.item_text = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.item_text.setText(list.get(position));
            return convertView;
        }

        class ViewHolder {
            TextView item_text;
        }
    }
}

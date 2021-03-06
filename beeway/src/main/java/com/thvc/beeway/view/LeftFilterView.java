package com.thvc.beeway.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.thvc.beeway.R;

public class LeftFilterView extends RelativeLayout implements ViewBaseAction {

	private ListView mListView;
	private String[] items = null;// 显示字段
	private OnSelectListener mOnSelectListener;
	private TextAdapter adapter;
	private String showText = "item1";
	private String type;

	public String getShowText() {
		return showText;
	}

	public LeftFilterView(Context context, String items[],String type) {
		super(context);
		this.items = items;
		this.type = type;
		init(context);
	}

	public LeftFilterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public LeftFilterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_distance, this, true);
		if ("left".equals(type)) {
			setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_left));
		}else if("middle".equals(type)){
			setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_mid));
		}
		mListView = (ListView) findViewById(R.id.listView);
		adapter = new TextAdapter(context, items, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
		adapter.setTextSize(15);

		mListView.setAdapter(adapter);
		adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {

				if (mOnSelectListener != null) {
					showText = items[position];
					mOnSelectListener.getValue(items[position], items[position]);
				}
			}
		});
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String distance, String showText);
	}

	@Override
	public void hideMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub

	}

}

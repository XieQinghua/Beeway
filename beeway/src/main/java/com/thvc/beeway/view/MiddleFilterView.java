package com.thvc.beeway.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.activity.MyTrackActivity;
import com.thvc.beeway.activity.ScenicActivity;
import com.thvc.beeway.bean.ScenicAreaBean;

import java.util.ArrayList;
import java.util.LinkedList;

public class MiddleFilterView extends LinearLayout implements ViewBaseAction, View.OnClickListener {

	private ListView regionListView;
	private ListView plateListView;

	private TextView tvLeftView;
	private TextView tvRightView;

	private ArrayList<String> groups = new ArrayList<String>();
	private LinkedList<String> childrenItem = new LinkedList<String>();
	private SparseArray<LinkedList<String>> children = new SparseArray<LinkedList<String>>();
	private TextAdapter plateListViewAdapter;
	private TextAdapter earaListViewAdapter;
	private OnItemSelectListener mOnSelectListener;
	private int tEaraPosition = 0;
	private int tBlockPosition = 0;
	private String showString = "不限",type;

	private enum showSubwayOrBcd {
		SUBWAY, BCD
	}

	private showSubwayOrBcd displayItem = showSubwayOrBcd.BCD;

	public MiddleFilterView(Context context,String type) {
		super(context);
		this.type = type;
		init(context);
	}

	public MiddleFilterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void updateShowText(String showArea, String showBlock) {
		if (showArea == null || showBlock == null) {
			return;
		}
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).equals(showArea)) {
				earaListViewAdapter.setSelectedPosition(i);
				childrenItem.clear();
				if (i < children.size()) {
					childrenItem.addAll(children.get(i));
				}
				tEaraPosition = i;
				break;
			}
		}
		for (int j = 0; j < childrenItem.size(); j++) {
			if (childrenItem.get(j).replace("不限", "").equals(showBlock.trim())) {
				plateListViewAdapter.setSelectedPosition(j);
				tBlockPosition = j;
				break;
			}
		}
		setDefaultSelect();
	}

	private void init(final Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_region, this, true);
		regionListView = (ListView) findViewById(R.id.listView);
		plateListView = (ListView) findViewById(R.id.listView2);

		tvLeftView = (TextView) findViewById(R.id.left_tv);
		tvLeftView.setOnClickListener(this);
		tvRightView = (TextView) findViewById(R.id.right_tv);
		tvRightView.setOnClickListener(this);

		setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_left));
//		setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_mid));
		ArrayList<ScenicAreaBean.DataEntity.ProvinceEntity> list = null;
		if (type.equals("scenic")) {
			list = ScenicActivity.createPricegroupsItems();

		}else if (type.equals("track")){
			list = MyTrackActivity.createPricegroupsItems();
		}
		if (list != null && !"".equals(list)) {
			for (int i = 0; i < list.size(); i++) {
				groups.add(list.get(i).getTitle());
				LinkedList<String> tItem = new LinkedList<String>();
				for (int j = 0; j < list.get(i).getCitys().size(); j++) {
					tItem.add(list.get(i).getCitys().get(j).getTitle());
				}
				children.put(i, tItem);
			}
		}
		earaListViewAdapter = new TextAdapter(context, groups, R.drawable.choose_item_selected, R.drawable.choose_eara_item_selector);
		earaListViewAdapter.setTextSize(15);
		earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
		regionListView.setAdapter(earaListViewAdapter);
		earaListViewAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {
				if (position < children.size()) {
					childrenItem.clear();
					childrenItem.addAll(children.get(position));
					plateListViewAdapter.notifyDataSetChanged();
				}
			}
		});
		if (tEaraPosition < children.size())
			childrenItem.addAll(children.get(tEaraPosition));
		plateListViewAdapter = new TextAdapter(context, childrenItem, R.drawable.choose_item_right, R.drawable.choose_plate_item_selector);
		plateListViewAdapter.setTextSize(15);
		plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
		plateListView.setAdapter(plateListViewAdapter);
		plateListViewAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, final int position) {

				showString = childrenItem.get(position);
				if (mOnSelectListener != null) {

					mOnSelectListener.getValue(showString);
				}

			}
		});
		if (tBlockPosition < childrenItem.size())
			showString = childrenItem.get(tBlockPosition);
		if (showString.contains("不限")) {
			showString = showString.replace("不限", "");
		}
		setDefaultSelect();

	}

	public void setDefaultSelect() {
		regionListView.setSelection(tEaraPosition);
		plateListView.setSelection(tBlockPosition);
	}

	public String getShowText() {
		return showString;
	}

	public void setOnSelectListener(OnItemSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnItemSelectListener {
		public void getValue(String showText);
	}

	@Override
	public void hideMenu() {

	}

	@Override
	public void showMenu() {

	}

	@Override
	public void onClick(View v) {
		if (v == tvLeftView) {
			if (displayItem == showSubwayOrBcd.BCD) {

			} else {

			}
		} else if (v == tvRightView) {
			earaListViewAdapter = new TextAdapter(getContext(), groups, R.drawable.choose_item_selected, R.drawable.choose_eara_item_selector);
			earaListViewAdapter.setTextSize(15);
			earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
			regionListView.setAdapter(earaListViewAdapter);
			earaListViewAdapter.notifyDataSetInvalidated();
			earaListViewAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

				@Override
				public void onItemClick(View view, int position) {
					if (position < children.size()) {
						childrenItem.clear();
						childrenItem.addAll(children.get(position));
						plateListViewAdapter.notifyDataSetChanged();
					}
				}
			});
			if (tEaraPosition < children.size())
				childrenItem.addAll(children.get(tEaraPosition));
			plateListViewAdapter = new TextAdapter(getContext(), childrenItem, R.drawable.choose_item_right, R.drawable.choose_plate_item_selector);
			plateListViewAdapter.setTextSize(15);
			plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
			plateListView.setAdapter(plateListViewAdapter);
			plateListViewAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

				@Override
				public void onItemClick(View view, final int position) {

					showString = childrenItem.get(position);
					if (mOnSelectListener != null) {

						mOnSelectListener.getValue(showString);
					}

				}
			});
			if (tBlockPosition < childrenItem.size())
				showString = childrenItem.get(tBlockPosition);
			if (showString.contains("不限")) {
				showString = showString.replace("不限", "");
			}
			setDefaultSelect();
		}
	}

}

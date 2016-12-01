package com.thvc.beeway.view;

import com.thvc.beeway.base.BaseActivity;

public class FilterDataSource extends BaseActivity{

	public static final int FILTER_DATA_TYPE_LOCATION = 1;
	public static final int FILTER_DATA_TYPE_DISTANCE = 2;
	public static final int FILTER_DATA_TYPE_PRICE = 3;
	public static final int FILTER_DATA_TYPE_SORT = 4;

	public static String[] createPriceFilterItems() {
		String[] names = {"全部", "吃货", "风景", "购物", "约伴", "活动", "吐槽", "摄影","感慨","其他"};
		return names;
	}

	public static String[] createSortFilterItems() {
		String names[] = new String[] { "全部", "我的", "精品" };
		return names;
	}
	public static String[] createSortFilterItems2() {
		String names[] = new String[] {"全部", "他的","我的", "精品"};;
		return names;
	}
	public static String[] travlecreateSortFilterItems() {
		String names[] = new String[] { "全部",  "我的" };
		return names;
	}
	public static String[] travlecreateSortFilterItems2() {
		String names[] = new String[]  {"全部", "他的", "我的"};
		return names;
	}
	public static String[] travleleftFilterItems() {
		String names[] = new String[] { "全部", "实用", "美图", "典藏" , "精华"};
		return names;
	}
	public static String[] createSortItems() {
		String names[] = new String[] { "默认排序", "关注优先", "人气优先", "最新发布" };
		return names;
	}

	public static String getDataTypeName(int dataType) {
		if (FILTER_DATA_TYPE_LOCATION == dataType) {
			return "位置";
		} else if (FILTER_DATA_TYPE_DISTANCE == dataType) {
			return "距离";
		} else if (FILTER_DATA_TYPE_PRICE == dataType) {
			return "价格";
		} else if (FILTER_DATA_TYPE_SORT == dataType) {
			return "排序";
		}
		return "";
	}
}

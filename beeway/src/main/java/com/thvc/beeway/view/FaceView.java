package com.thvc.beeway.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.thvc.beeway.R;




/**
 * 项目名称：Beeway
 * 类描述：表情工具类
 * 创建人：颜松梁
 * 创建时间：2015/9/6 10:20
 * 修改人：Administrator
 * 修改时间：2015/9/6 10:20
 * 修改备注：
 */
public class FaceView extends LinearLayout {

	Context context;
	ViewPager vp_content; //
	LinearLayout ll_navigate;

	// 表情对应的文字
	static String[] all_faces_to_string = { "[闭嘴]", "[呲牙]", "[调皮]",
			"[流汗]", "[偷笑]", "[再见]", "[敲打]", "[擦汗]", "[猪头]", "[玫瑰]", "[流泪]",
			"[大哭]", "[嘘]", "[酷]", "[抓狂]", "[委屈]", "[便便]","[炸弹]", "[菜刀]",
			"[愉快]", "[色]", "[害羞]", "[害羞]", "[吐]", "[微笑]", "[发怒]", "[尴尬]",
			"[惊恐]", "[冷汗]", "[爱心]", "[嘴唇]", "[白眼]", "[傲慢]", "[难过]", "[惊讶]",
			"[疑问]", "[睡]", "[亲亲]", "[憨笑]", "[爱情]", "[衰]", "[撇嘴]", "[阴险]",
			"[奋斗]", "[发呆]", "[右哼哼]", "[拥抱]", "[坏笑]", "[飞吻]", "[鄙视]","[晕]",
			"[悠闲]", "[可伶]", "[强]", "[弱]", "[握手]", "[胜利]","[握拳]","[凋谢]","[米饭]","[生日快乐]",
			"[西瓜]","[啤酒]","[瓢虫]","[勾引]","[OK]","[爱你]","[咖啡]","[月亮]","[刀]","[发抖]","[差劲]","[拳头]",
			"[心碎]","[阳光]","[礼物]","[足球]","[骷髅]","[投降]","[闪电]","[饥饿]","[困]","[咒骂]","[疯了]",
			"[扣鼻]","[鼓掌]","[糗大了]","[左哼哼]","[哈欠]","[快哭了]","[吓]","[篮球]","[乒乓球]","[NO]",
			"[跳跳]","[怄火]","[转圈]","[磕头]","[回头]","[跳绳]","[蹦蹦跳跳]","[加油]","[观察]","[左太极]","[右太极]"};
	// 表情总数
	static int[] all_faces = {
			R.drawable.e_1, R.drawable.e_2,
			R.drawable.e_3, R.drawable.e_4, R.drawable.e_5,
			R.drawable.e_6, R.drawable.e_7, R.drawable.e_8,
			R.drawable.e_9, R.drawable.e_10, R.drawable.e_11,
			R.drawable.e_12, R.drawable.e_13, R.drawable.e_14,
			R.drawable.e_15, R.drawable.e_16, R.drawable.e_17,
			R.drawable.e_18, R.drawable.e_19, R.drawable.e_20,
			R.drawable.e_21, R.drawable.e_22, R.drawable.e_23,
			R.drawable.e_24, R.drawable.e_25, R.drawable.e_26,
			R.drawable.e_27, R.drawable.e_28, R.drawable.e_29,
			R.drawable.e_30, R.drawable.e_31, R.drawable.e_32,
			R.drawable.e_33, R.drawable.e_34, R.drawable.e_35,
			R.drawable.e_36, R.drawable.e_37, R.drawable.e_38,
			R.drawable.e_39, R.drawable.e_40, R.drawable.e_41,
			R.drawable.e_42, R.drawable.e_43, R.drawable.e_44,
			R.drawable.e_45, R.drawable.e_46, R.drawable.e_47,
			R.drawable.e_48, R.drawable.e_49, R.drawable.e_50,
			R.drawable.e_51, R.drawable.e_52, R.drawable.e_53,
			R.drawable.e_54, R.drawable.e_55, R.drawable.e_56,
			R.drawable.e_57, R.drawable.e_58 , R.drawable.e_59
			, R.drawable.e_60 , R.drawable.e_61 , R.drawable.e_62
			, R.drawable.e_63 , R.drawable.e_64 , R.drawable.e_65
			, R.drawable.e_66 , R.drawable.e_67 , R.drawable.e_68
			, R.drawable.e_69 , R.drawable.e_70 , R.drawable.e_71
			, R.drawable.e_72 , R.drawable.e_73 , R.drawable.e_74
			, R.drawable.e_75 , R.drawable.e_76 , R.drawable.e_77
			, R.drawable.e_78 , R.drawable.e_79 , R.drawable.e_80
			, R.drawable.e_81 , R.drawable.e_82 , R.drawable.e_83
			, R.drawable.e_84 , R.drawable.e_85 , R.drawable.e_86
			, R.drawable.e_87, R.drawable.e_88 , R.drawable.e_89
			, R.drawable.e_90 , R.drawable.e_91 , R.drawable.e_92
			, R.drawable.e_93 , R.drawable.e_94 , R.drawable.e_95
			, R.drawable.e_96 , R.drawable.e_97 , R.drawable.e_98
			, R.drawable.e_99 , R.drawable.e_100 , R.drawable.e_101 , R.drawable.e_102 , R.drawable.e_103 , R.drawable.e_104 , R.drawable.e_105 };
	// 一页表情数（固定）
	static int one_faces_sum = 21;
	// 页数（表情总数除一页表情数）
	int index_sum = all_faces.length / one_faces_sum ;

	int[] one_faces; // 单页表情

	ArrayList<GridView> gridViews; //
	Work work;

	public FaceView(Context context, AttributeSet attrs,Work work) {
		super(context, attrs);
		this.context = context;
		this.work = work;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_faceview, this);
		vp_content = (ViewPager) findViewById(R.id.vp_content);
		ll_navigate = (LinearLayout) findViewById(R.id.ll_navigate);

		initViewPager();
	}

	private void initViewPager() {

		LayoutInflater inflater = LayoutInflater.from(context);

		gridViews = new ArrayList<GridView>();
		GridView gView;

		// 生成表情
		for (int i = 0; i < index_sum; i++) {
			gView = (GridView) inflater.inflate(R.layout.layout_faceview_gridview, null);

			List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
			final List<String> listItems_str = new ArrayList<String>();
			final List<Integer> listItems_id = new ArrayList<Integer>();

			// 生成一页表情
			for (int j = i * one_faces_sum; j < (i + 1) * one_faces_sum; j++) {
				if (j >= all_faces.length) { // 超出表情总数时，终止
					break;
				}
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("image", all_faces[j]);
				listItems.add(listItem);

				listItems_str.add(all_faces_to_string[j]);
				listItems_id.add(all_faces[j]);
			}
			SimpleAdapter simpleAdapter = new SimpleAdapter(context, listItems,
					R.layout.item_faceview_gridview, new String[] { "image" },
					new int[] { R.id.iv_face });
			gView.setAdapter(simpleAdapter);
			gView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
										int arg2, long arg3) {
					work.onClick(listItems_id.get(arg2),listItems_str.get(arg2));
				}
			});

			gridViews.add(gView);
			// 每产生一页表情，增加一个导航点
			ImageView view = new ImageView(context);
			view.setBackgroundResource(R.drawable.insoft_point_selector);
			view.setEnabled(false);
			ll_navigate.addView(view);
			if (i == 0) { // 第一个为选中
				view.setEnabled(true);
			}
		}

		// 填充ViewPager的数据适配器
		PagerAdapter pagerAdapter = new PagerAdapter() {
			@Override
			public int getCount() {
				return gridViews.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
									Object object) {
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = gridViews.get(position);
				container.addView(view);
				return view;
			}
		};

		vp_content.setAdapter(pagerAdapter);
		vp_content
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub
						for (int i = 0; i < ll_navigate.getChildCount(); i++) {
							ImageView view = (ImageView) ll_navigate
									.getChildAt(i);
							if (i == arg0) {
								view.setEnabled(true);
								continue;
							}
							view.setEnabled(false);
						}
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	public interface Work {
		public void onClick(int id, String item_str);
	};
}

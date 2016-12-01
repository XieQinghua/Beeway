package com.thvc.beeway.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.easemob.chatui.utils.SmileUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.IsCollectDataBean;
import com.thvc.beeway.bean.IsGoodDataBean;
import com.thvc.beeway.bean.TrackFragmengBean;
import com.thvc.beeway.cn.sharesdk.onekeyshare.OnekeyShare;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/8/22.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TrackAdapter extends BaseAdapter {
    private List<TrackFragmengBean.DataEntity.ListEntity> list;
    private BitmapUtils bitmapUtils;
    private ImageLoader imageLoader;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;
    private Context context;
    private int bl=1;

    public TrackAdapter(List<TrackFragmengBean.DataEntity.ListEntity> list, Context
            context) {
        this.list = list;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
        imageLoader = VolleyHepler.getInstance().getImageLoader();
        imageLoader2 = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
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
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.track_fragment_item_item, null);
            vh.tv_track_rank = (TextView) convertView.findViewById(R.id.tv_track_rank);
            vh.track_user_sex = (TextView) convertView.findViewById(R.id.track_user_sex);
            vh.name = (TextView) convertView.findViewById(R.id.track_fragment_item_name);
            vh.track_fragment_item_type = (TextView) convertView.findViewById(R.id.track_fragment_item_type);
            vh.comment = (TextView) convertView.findViewById(R.id.track_fragment_item_commend);
            vh.content = (TextView) convertView.findViewById(R.id.track_fragment_item_content);
            vh.good = (TextView) convertView.findViewById(R.id.track_fragment_item_good);
            vh.share = (TextView) convertView.findViewById(R.id.track_fragment_item_share);
            vh.time = (TextView) convertView.findViewById(R.id.track_fragment_item_time);
            vh.imageView = (CircleImageView) convertView.findViewById(R.id.track_fragment_item_image);
            vh.largeimage = (ImageView) convertView.findViewById(R.id.track_fragment_item_largeimage);
            vh.share_image = (ImageView) convertView.findViewById(R.id.track_share_image);
            vh.collect_image = (ImageView) convertView.findViewById(R.id.track_collection_image);
            vh.good_image = (ImageView) convertView.findViewById(R.id.track_good_image);
            vh.address = (TextView) convertView.findViewById(R.id.track_fragment_item_address);
            vh.share_layout = (LinearLayout) convertView.findViewById(R.id.track_item_onclick_share);
            vh.commed_layout = (LinearLayout) convertView.findViewById(R.id.track_item_onclick_commed);
            vh.collect_layout = (LinearLayout) convertView.findViewById(R.id.track_item_onclick_collect);
            vh.isgood_layout = (LinearLayout) convertView.findViewById(R.id.track_item_onclick_isgood);
            vh.collect = (TextView) convertView.findViewById(R.id.track_fragment_item_collection);
            vh.relayout = (RelativeLayout) convertView.findViewById(R.id.track_fragment_item_Relayout);
            vh.relayout2 = (RelativeLayout) convertView.findViewById(R.id.travel_fragment_item_Relayout);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.relayout.setVisibility(View.VISIBLE);
        vh.relayout2.setVisibility(View.GONE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams para;
        para = vh.largeimage.getLayoutParams();
        para.height = wm.getDefaultDisplay().getWidth()*3/4;
        para.width = wm.getDefaultDisplay().getWidth();
        vh.largeimage.setLayoutParams(para);
        if ("1".equals(list.get(position).getSex())) {
            vh.track_user_sex.setText("男");
            vh.track_user_sex.setBackgroundResource(R.drawable.corner_view_left);
            vh.tv_track_rank.setBackgroundResource(R.drawable.corner_view_right);
        } else if ("2".equals(list.get(position).getSex())) {
            vh.track_user_sex.setText("女");
            vh.track_user_sex.setBackgroundResource(R.drawable.corner_view_left_sex);
            vh.tv_track_rank.setBackgroundResource(R.drawable.corner_view_right_sex);
        }
        vh.tv_track_rank.setText(list.get(position).getLvName());
        vh.name.setText(list.get(position).getNickname());
        vh.share.setText(list.get(position).getShare());
        vh.good.setText(list.get(position).getGood());
        vh.content.setText(SmileUtils.getSmiledText(context, list.get(position).getContent()));
        vh.comment.setText(list.get(position).getComment());
        vh.time.setText(getStringTime(list.get(position).getAddtime()));
        vh.address.setText(list.get(position).getAddress());
        vh.collect.setText(list.get(position).getCollect());
        vh.track_fragment_item_type.setText(list.get(position).getTitle());
        vh.imageView.setImageUrl(UrlPools.getFriendAvatarUrl(context, list.get(position).getUserid()), imageLoader);
        String url = UrlPools.QINIU + list.get(position).getAtlas().getData().get(0).getFileurl() + "-Thumb640";
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                .cacheInMemory()                                               //缓存用
                .cacheOnDisc()                                                    //缓存用
                .build();
        imageLoader2.displayImage(url, vh.largeimage, options);


        if (list.get(position).getIsgood() == 1) {
            vh.good_image.setImageResource(R.drawable.track_good_yes);
        }
        if (list.get(position).getCollection() == 1) {
            vh.collect_image.setImageResource(R.drawable.track_collection_yes);
        }
        vh.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("friendid", list.get(position).getUserid());
                context.startActivity(intent);
            }
        });
        vh.share_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare(position);
            }
        });
        vh.collect_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getCollection() != 1) {
                    HttpUtils httpUtils = new HttpUtils();
                    MyRequestParams params = new MyRequestParams();
                    params.addQueryStringParameter("dataid", list.get(position).getId());
                    params.addQueryStringParameter("userid", GlobalParams.loginUserId);
                    params.addQueryStringParameter("tableid", "TrackNote");
                    params.addQueryStringParameter("status", "2");
                    String url = params.myRequestParams(params);
                    httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.COLLECTION + "?" + url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            //获得访问解析出来的json字符串
                            String result = responseInfo.result;
                            System.out.println("收藏    result====" + result);
                            IsCollectDataBean isCollectDataBean = isCollectDataBean(result);
                            if (isCollectDataBean.getStatus() == 1) {
                                Toast.makeText(context, isCollectDataBean.getData().getMsg(), Toast.LENGTH_SHORT).show();
                                if (isCollectDataBean.getData().getData() == 1) {
                                    vh.collect_image.setImageResource(R.drawable.track_collection_yes);
                                    vh.collect.setText(String.valueOf(Integer.parseInt(list.get(position).getCollect()) + 1));
                                    vh.collect.setTextColor(Color.RED);
                                } else if (isCollectDataBean.getData().getData() == 0) {
                                    vh.collect_image.setImageResource(R.drawable.track_collection_no);
                                    vh.collect.setText(list.get(position).getCollect());
                                    vh.collect.setTextColor(context.getResources().getColor(R.color.textcolor));
                                }
                            } else {
                                Toast.makeText(context, isCollectDataBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(context, "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        vh.isgood_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getIsgood() != 1) {
                    HttpUtils httpUtils = new HttpUtils();
                    MyRequestParams params = new MyRequestParams();
                    params.addQueryStringParameter("noteid", list.get(position).getId());
                    params.addQueryStringParameter("userid", GlobalParams.loginUserId);
                    params.addQueryStringParameter("good", "1");
                    String url = params.myRequestParams(params);
                    System.out.println("  UrlPools.ISGOOD + url=  " + UrlPools.ISGOOD + "?" + url);
                    httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.ISGOOD + "?" + url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            //获得访问解析出来的json字符串
                            String result = responseInfo.result;
                            IsGoodDataBean isGoodDataBean = isGoodDataBean(result);
                            if (isGoodDataBean.getStatus() == 1) {
                                System.out.println("点赞 json====" + isGoodDataBean.getData());
                                vh.good_image.setImageResource(R.drawable.track_good_yes);
                                vh.good.setText(String.valueOf(Integer.parseInt(list.get(position).getGood()) + 1));
                                vh.good.setTextColor(Color.RED);
                            } else {
                                Toast.makeText(context, isGoodDataBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {

                        }
                    });
                }
            }
        });
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
        TextView name, time, content, share, good, comment, collect, address, track_fragment_item_type, track_user_sex, tv_track_rank;
        CircleImageView imageView;
        ImageView largeimage, share_image, collect_image, good_image;
        RelativeLayout relayout, relayout2;
        LinearLayout share_layout, commed_layout, collect_layout, isgood_layout;
    }

    /**
     * 收藏数据解析
     */
    public IsCollectDataBean isCollectDataBean(String result) {
        Gson gson = new Gson();
        IsCollectDataBean isCollectDataBean = gson.fromJson(result, IsCollectDataBean.class);
        return isCollectDataBean;
    }
    /**
     * 点赞网络获取数据解析
     */
    public IsGoodDataBean isGoodDataBean(String result) {
        Gson gson = new Gson();
        IsGoodDataBean isGoodDataBean = gson.fromJson(result, IsGoodDataBean.class);
        return isGoodDataBean;
    }

    public void showShare(int position) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();

        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(list.get(position).getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.hlfyb.com/wechat.php/Share/track.html?id=" + list.get(position).getId() + "&&userid=" + list.get(position).getUserid());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(list.get(position).getContent());
        //网络获取图片
        oks.setImageUrl(UrlPools.QINIU + list.get(position).getAtlas().getData().get(0).getFileurl() + "-Thumb100");
        // imagePath是图片的本地路径，Linked-In以外的a平台都支持此参数
        //oks.setImagePath("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.hlfyb.com/wechat.php/Share/track.html?id=" + list.get(position).getId() + "&&userid=" + list.get(position).getUserid());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(list.get(position).getContent());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Beeway");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.hlfyb.com/wechat.php/Share/track.html?id=" + list.get(position).getId() + "&&userid=" + list.get(position).getUserid());

        // 启动分享GUI
        oks.show(context);

    }

}

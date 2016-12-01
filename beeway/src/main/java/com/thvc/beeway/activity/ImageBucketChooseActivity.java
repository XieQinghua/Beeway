package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.ImageBucketAdapter;
import com.thvc.beeway.bean.ImageBucket;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.ImageFetcher;
import com.thvc.beeway.utils.IntentConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 选择相册
 */

public class ImageBucketChooseActivity extends BaseActivity {
    private ImageFetcher mHelper;
    private List<ImageBucket> mDataList = new ArrayList<ImageBucket>();
    private ListView mListView;
    private ImageBucketAdapter mAdapter;
    private int availableSize;
    private String tv_track, tv__time, tv__place, et_text, make;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_bucket_choose);

        mHelper = ImageFetcher.getInstance(getApplicationContext());
        initData();
        initView();
        make = getIntent().getStringExtra("make");
        tv_track = getIntent().getStringExtra("tv_track");
        tv__time = getIntent().getStringExtra("tv__time");
        tv__place = getIntent().getStringExtra("tv__place");
        et_text = getIntent().getStringExtra("et_text");
    }

    private void initData() {
        mDataList = mHelper.getImagesBucketList(false);
        availableSize = getIntent().getIntExtra(
                IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                CustomConstants.MAX_IMAGE_SIZE);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ImageBucketAdapter(this, mDataList);
        mListView.setAdapter(mAdapter);
        TextView titleTv = (TextView) findViewById(R.id.title);
        titleTv.setText("相册");
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectOne(position);

                Intent intent = new Intent(ImageBucketChooseActivity.this,
                        ImageChooseActivity.class);
                intent.putExtra(IntentConstants.EXTRA_IMAGE_LIST,
                        (Serializable) mDataList.get(position).imageList);
                intent.putExtra(IntentConstants.EXTRA_BUCKET_NAME,
                        mDataList.get(position).bucketName);
                intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                        availableSize);
                intent.putExtra("make", make);
                intent.putExtra("tv_track", tv_track);
                intent.putExtra("tv__time", tv__time);
                intent.putExtra("tv__place", tv__place);
                intent.putExtra("et_text", et_text);
                startActivity(intent);
                finish();
            }
        });

        TextView cancelTv = (TextView) findViewById(R.id.action);
        cancelTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (make.equals("addtrack")) {
                    Intent intent = new Intent(ImageBucketChooseActivity.this,
                            AddTrackActivity.class);
                    intent.putExtra("tv_track", tv_track);
                    intent.putExtra("tv__time", tv__time);
                    intent.putExtra("tv__place", tv__place);
                    intent.putExtra("et_text", et_text);
                    startActivity(intent);
                finish();
                } else if (make.equals("feedback")) {
                    Intent intent = new Intent(ImageBucketChooseActivity.this,
                            FeedbackActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    private void selectOne(int position) {
        int size = mDataList.size();
        for (int i = 0; i != size; i++) {
            if (i == position) mDataList.get(i).selected = true;
            else {
                mDataList.get(i).selected = false;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

}

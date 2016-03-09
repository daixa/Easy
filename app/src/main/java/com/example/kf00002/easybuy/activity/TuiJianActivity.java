package com.example.kf00002.easybuy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kf00002.easybuy.mlib.ADInfo;
import com.example.kf00002.easybuy.mlib.CycleViewPager;
import com.example.kf00002.easybuy.mlib.ImageFactory;
import com.example.kf00002.easybuy.R;

import java.util.ArrayList;
import java.util.List;

public class TuiJianActivity extends Activity {
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5;
    private LinearLayout douyou;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;

    private String[] imageUrls = {"http://abc.2008php.com/2013_Website_appreciate/2013-02-25/20130225010550.jpg",
            "http://abc.2008php.com/2013_Website_appreciate/2013-02-25/20130225010623.jpg",
            "http://abc.2008php.com/2013_Website_appreciate/2013-02-25/20130225010606.jpg",
            "http://pic1.win4000.com/wallpaper/1/53bf5cf5628f2.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_jian);
        getId();
        initialize();

    }

    private void getId() {
        mTextView1 = (TextView) findViewById(R.id.tui_jian_most_1);
        mTextView2 = (TextView) findViewById(R.id.tui_jian_most_2);
        mTextView3 = (TextView) findViewById(R.id.tui_jian_most_3);
        mTextView4 = (TextView) findViewById(R.id.tui_jian_most_4);
        mTextView5 = (TextView) findViewById(R.id.tui_jian_most_5);
        douyou = (LinearLayout) findViewById(R.id.dou_you);

        douyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuiJianActivity.this, FinalActivity.class);
                startActivity(intent);
            }
        });

        mTextView1.setText("家用电器首页");
        mTextView3.setText("商城");
        mTextView4.setText("农村集市");
        mTextView5.setText("加盟商城");
    }

    private void initialize() {
        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content_tuijian);

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
//            info.setContent("图片-->" + i);
            infos.add(info);
        }

        //将最后一个ImageView添加进来
        views.add(ImageFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ImageFactory.getImageView(this, infos.get(i).getUrl()));
        }
        //将第一个ImaageView添加进去
        views.add(ImageFactory.getImageView(this, infos.get(0).getUrl()));

        //设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        //加载数据
        cycleViewPager.setData(views, infos, new CycleViewPager.ImageCycleViewListener() {
            @Override
            public void onImageClick(ADInfo info, int postion, View imageView) {
                if (cycleViewPager.isCycle()) {
                    postion = postion - 1;
//                    Toast.makeText(TuiJianActivity.this, "position-->" + info.getContent(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //设置轮播
        cycleViewPager.setWheel(true);

        //设置轮播时间
        cycleViewPager.setTime(2000);

        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }
}

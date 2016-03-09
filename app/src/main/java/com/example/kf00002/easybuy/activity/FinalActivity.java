package com.example.kf00002.easybuy.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kf00002.easybuy.mlib.ADInfo;
import com.example.kf00002.easybuy.mlib.CycleViewPager;
import com.example.kf00002.easybuy.mlib.ImageFactory;
import com.example.kf00002.easybuy.R;

import java.util.ArrayList;
import java.util.List;

public class FinalActivity extends Activity {

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;
    private Button f_back_btn;
    private String[] imageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        imageUrls=getIntent().getStringArrayExtra("key_to");
        initialize();
        f_back_btn = (Button) findViewById(R.id.f_back_btn);
        f_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initialize() {
        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content_final);

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
//                    Toast.makeText(FinalActivity.this, "position-->" + info.getContent(), Toast.LENGTH_SHORT).show();
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

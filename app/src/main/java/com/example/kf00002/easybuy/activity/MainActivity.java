package com.example.kf00002.easybuy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.kf00002.easybuy.mlib.ADInfo;
import com.example.kf00002.easybuy.mlib.CycleViewPager;
import com.example.kf00002.easybuy.mlib.ImageFactory;
import com.example.kf00002.easybuy.R;

import java.util.ArrayList;
import java.util.List;
//import com.example.kf00002.easybuy.Utils.RollAd;


public class MainActivity extends Activity implements View.OnClickListener, MediaPlayer.OnErrorListener {

    private TextView my_add_store, my_farm_store, my_store, my_gou_store, my_tui_jian;
    private ImageView my_iv1, my_iv2, my_iv3, my_iv4, m_sc_iv1, m_sc_iv2, m_sc_iv3, m_sc_iv4,
            m_nc_iv1, m_nc_iv2, m_nc_iv3, m_nc_iv4,
            m_addstore_iv1, m_addstore_iv2, m_addstore_iv3, m_addstore_iv4;
    private EditText my_et;

    private Intent intent;
    private Button my_video_play, my_search_btn;
    private ViewPager viewPager;
    private LinearLayout mLinearLayout, show_action_top, show_action_bottom;
    private RelativeLayout touch_rl;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;

    public static final String TAG = "VideoPlayer";
    private VideoView mVideoView1, mVideoView2, mVideoView3, mVideoView4;
    private Uri mUri;
    private int mPositionWhenPaused = -1;

    private MediaController mMediaController1,mMediaController2,mMediaController3,mMediaController4;

    private boolean touchIsShowing = false;


    private String[] imageUrls = {"http://abc.2008php.com/2013_Website_appreciate/2013-02-25/20130225010550.jpg",
            "http://abc.2008php.com/2013_Website_appreciate/2013-02-25/20130225010623.jpg",
            "http://abc.2008php.com/2013_Website_appreciate/2013-02-25/20130225010606.jpg",
            "http://pic1.win4000.com/wallpaper/1/53bf5cf5628f2.jpg"};

    private String[] liUrls = {"https://img.alicdn.com/imgextra/i2/2217550171/TB2hJKudXXXXXbKXpXXXXXXXXXX_!!2217550171.jpg",
    "https://img.alicdn.com/imgextra/i3/2217550171/TB2XxQYipXXXXcWXXXXXXXXXXXX_!!2217550171.jpg",
   "https://img.alicdn.com/imgextra/i3/2217550171/TB2G_jripXXXXbeXXXXXXXXXXXX_!!2217550171.jpg"};

    private String[] yeUrls = {"https://img.alicdn.com/imgextra/i1/1795616675/TB2RR.KdpXXXXbJXpXXXXXXXXXX_!!1795616675.jpg",
            "https://img.alicdn.com/imgextra/i2/1795616675/TB2HExedFXXXXXsXXXXXXXXXXXX_!!1795616675.jpg" ,
            "https://img.alicdn.com/imgextra/i2/1795616675/TB2FVM2dpXXXXc0XXXXXXXXXXXX_!!1795616675.jpg"};

    private String[] boUrls = {"https://img.alicdn.com/imgextra/i2/2217550171/TB2eUG8fXXXXXcGXXXXXXXXXXXX_!!2217550171.jpg",
            "https://img.alicdn.com/imgextra/i3/2217550171/TB2fg_dfXXXXXa3XXXXXXXXXXXX_!!2217550171.jpg" ,
            "https://img.alicdn.com/imgextra/i1/2217550171/TB2T5vbfXXXXXblXXXXXXXXXXXX_!!2217550171.jpg"};

    private String[] niuUrls = {"https://img.alicdn.com/imgextra/i2/883232218/TB2M2BkgXXXXXcwXXXXXXXXXXXX_!!883232218.jpg" ,
            "https://img.alicdn.com/imgextra/i2/883232218/TB2418qgXXXXXatXXXXXXXXXXXX_!!883232218.jpg" ,
            "https://img.alicdn.com/imgextra/i4/883232218/TB2xJU4fVXXXXcsXpXXXXXXXXXX_!!883232218.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getId();
        initialize();

    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        System.exit(0);
                    }
                });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }


    //捕捉按键事件。并调用相应处理方法。不需要再在onCreate()方法里注册监听之类的。
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
            return false;
        }
        return false;
    }

    private void getId() {
        my_tui_jian = (TextView) findViewById(R.id.my_tui_jian);
        my_gou_store = (TextView) findViewById(R.id.my_gou_store);
        my_store = (TextView) findViewById(R.id.my_store);
        my_farm_store = (TextView) findViewById(R.id.my_farm_store);
        my_add_store = (TextView) findViewById(R.id.my_add_store);
        my_search_btn = (Button) findViewById(R.id.my_search_btn);

        my_et = (EditText) findViewById(R.id.my_et);
        my_iv1 = (ImageView) findViewById(R.id.m_iv1);
        my_iv2 = (ImageView) findViewById(R.id.m_iv2);
        my_iv3 = (ImageView) findViewById(R.id.m_iv3);
        my_iv4 = (ImageView) findViewById(R.id.m_iv4);

        my_iv1.setImageResource(R.drawable.sx1);
        my_iv2.setImageResource(R.drawable.sx2);
        my_iv3.setImageResource(R.drawable.sx3);
        my_iv4.setImageResource(R.drawable.sx4);

        my_iv1.setOnClickListener(MainActivity.this);
        my_iv2.setOnClickListener(MainActivity.this);
        my_iv3.setOnClickListener(MainActivity.this);
        my_iv4.setOnClickListener(MainActivity.this);



        m_sc_iv1 = (ImageView) findViewById(R.id.m_sc_iv1);
        m_sc_iv2 = (ImageView) findViewById(R.id.m_sc_iv2);
        m_sc_iv3 = (ImageView) findViewById(R.id.m_sc_iv3);
        m_sc_iv4 = (ImageView) findViewById(R.id.m_sc_iv4);

        m_sc_iv2.setImageResource(R.drawable.iv2);
        m_sc_iv3.setImageResource(R.drawable.iv3);
        m_sc_iv4.setImageResource(R.drawable.iv4);

        m_nc_iv1 = (ImageView) findViewById(R.id.m_nc_iv1);
        m_nc_iv2 = (ImageView) findViewById(R.id.m_nc_iv2);
        m_nc_iv3 = (ImageView) findViewById(R.id.m_nc_iv3);
//        m_nc_iv4 = (ImageView) findViewById(R.id.m_nc_iv4);

        m_nc_iv1.setImageResource(R.drawable.nc1);
        m_nc_iv2.setImageResource(R.drawable.nc2);
        m_nc_iv3.setImageResource(R.drawable.nc3);
//        m_sc_iv1.setImageResource(R.drawable.sx4);

        m_addstore_iv1 = (ImageView) findViewById(R.id.m_addstore_iv1);
        m_addstore_iv2 = (ImageView) findViewById(R.id.m_addstore_iv2);
        m_addstore_iv3 = (ImageView) findViewById(R.id.m_addstore_iv3);
//        m_addstore_iv4 = (ImageView) findViewById(R.id.m_addstore_iv4);

        m_addstore_iv1.setImageResource(R.drawable.add1);
        m_addstore_iv2.setImageResource(R.drawable.add2);
        m_addstore_iv3.setImageResource(R.drawable.add3);


        mVideoView1 = (VideoView) findViewById(R.id.my_video1);
        mVideoView2 = (VideoView) findViewById(R.id.my_video2);
        mVideoView3 = (VideoView) findViewById(R.id.my_video3);
        mVideoView4 = (VideoView) findViewById(R.id.my_video4);
        //Video file
        mUri = Uri.parse(Environment.getExternalStorageDirectory() + "/001 Welcome.mp4");

        //Create media controller，组件可以控制视频的播放，暂停，回复，seek等操作，不需要你实现
        mMediaController1 = new MediaController(this);
        mMediaController2 = new MediaController(this);
        mMediaController3 = new MediaController(this);
        mMediaController4 = new MediaController(this);
        
        mVideoView1.setMediaController(mMediaController1);
        mVideoView2.setMediaController(mMediaController2);
        mVideoView3.setMediaController(mMediaController3);
        mVideoView4.setMediaController(mMediaController4);

        my_tui_jian.setOnClickListener(MainActivity.this);
        my_gou_store.setOnClickListener(MainActivity.this);
        my_store.setOnClickListener(MainActivity.this);
        my_farm_store.setOnClickListener(MainActivity.this);
        my_add_store.setOnClickListener(MainActivity.this);
        my_et.setOnClickListener(MainActivity.this);
        my_search_btn.setOnClickListener(MainActivity.this);
    }

    private void initialize() {
        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);

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
//                    Toast.makeText(MainActivity.this, "position-->" + info.getContent(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {

        {
            switch (v.getId()) {
                case R.id.my_tui_jian:
                    intent = new Intent(MainActivity.this, TuiJianActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_gou_store:
                    intent = new Intent(MainActivity.this, GouStoreActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_store:
                    intent = new Intent(MainActivity.this, StoreActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_farm_store:
                    intent = new Intent(MainActivity.this, FarmStoreActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_add_store:
                    intent = new Intent(MainActivity.this, AddStroeActivity.class);
                    startActivity(intent);
                    break;

                case R.id.my_search_btn:
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    break;

                case R.id.m_iv1:
                    intent = new Intent(MainActivity.this, FinalActivity.class);
                    intent.putExtra("key_to",liUrls);
                    startActivity(intent);
                    break;
                case R.id.m_iv2:
                    intent = new Intent(MainActivity.this, FinalActivity.class);
                    intent.putExtra("key_to",yeUrls);
                    startActivity(intent);
                    break;

                case R.id.m_iv3:
                    intent = new Intent(MainActivity.this, FinalActivity.class);
                    intent.putExtra("key_to",boUrls);
                    startActivity(intent);
                    break;

                case R.id.m_iv4:
                    intent = new Intent(MainActivity.this, FinalActivity.class);
                    intent.putExtra("key_to",niuUrls);
                    startActivity(intent);
                    break;



            }
        }

    }

    public void onStart() {
        // Play Video
        mVideoView1.setVideoURI(mUri);
        mVideoView1.start();
        mVideoView1.stopPlayback();

        mVideoView2.setVideoURI(mUri);
        mVideoView2.start();
        mVideoView2.stopPlayback();

        mVideoView3.setVideoURI(mUri);
        mVideoView3.start();
        mVideoView3.stopPlayback();

        mVideoView4.setVideoURI(mUri);
        mVideoView4.start();
        mVideoView4.stopPlayback();

        super.onStart();
    }

    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView1.getCurrentPosition();
        mVideoView1.stopPlayback();

        mPositionWhenPaused = mVideoView2.getCurrentPosition();
        mVideoView2.stopPlayback();

        mPositionWhenPaused = mVideoView3.getCurrentPosition();
        mVideoView3.stopPlayback();

        mPositionWhenPaused = mVideoView4.getCurrentPosition();
        mVideoView4.stopPlayback();


        super.onPause();
    }

    public void onResume() {
        // Resume video player
        if (mPositionWhenPaused >= 0) {
            mVideoView1.seekTo(mPositionWhenPaused);

            mVideoView2.seekTo(mPositionWhenPaused);

            mVideoView3.seekTo(mPositionWhenPaused);

            mVideoView4.seekTo(mPositionWhenPaused);

            mPositionWhenPaused = -1;
        }

        super.onResume();
    }

    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

    public void onCompletion(MediaPlayer mp) {
        this.finish();
    }
}

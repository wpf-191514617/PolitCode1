package com.zodiac.polit.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.minilive.library.widget.SplashView;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.zodiac.polit.BDLocationUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.ui.activity.WelcomeActivity;
import com.zodiac.polit.util.PreferenceUtils;

import java.util.List;

/**
 * Created by john on 2018/9/23.
 */

public class SplashActivity extends BaseActivity{
    private SplashView mSplashView;

    private boolean isClicked = false;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLocation();
            }
        }, 2000);*/

        hideBottomUIMenu();

        startLocation();
    }

    private void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19){
            View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19){
            View decorView = getWindow().getDecorView();
            int ioOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(ioOptions);
        }
    }


    private void startLocation(){

        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).build(), new AcpListener() {
            @Override
            public void onGranted() {
                //toGo();
                BDLocationUtils bdLocationUtils = new BDLocationUtils(SplashActivity.this);
                bdLocationUtils.doLocation();
                bdLocationUtils.mLocationClient.start();
                showSplash();
            }

            @Override
            public void onDenied(List<String> permissions) {
                Toast.makeText(SplashActivity.this, permissions + "权限拒绝", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void showSplash() {
        mSplashView = new SplashView(this);
        mSplashView.showSplashView(this, 3, R.drawable.splash, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {

            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                if (isClicked) return;
                toGo();
            }
        });
    }


    private void toGo(){
        isClicked = true;
        boolean isFirst = PreferenceUtils.getInstance().getBooleanValue("isFirstIn", true);
        if (isFirst) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
            return;
        }
        jumpToThenKill(MainActivity.class);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }
}

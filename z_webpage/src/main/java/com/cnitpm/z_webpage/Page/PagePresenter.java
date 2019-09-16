package com.cnitpm.z_webpage.Page;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_webpage.R;

public class PagePresenter extends BasePresenter<PageView> implements View.OnClickListener {
    @Override
    public void init() {
        setView();
        click();
        setWeb();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("");
    }

    private void setWeb(){
        mvpView.getPage_WebView().loadUrl(mvpView.getUrl());
        WebSettings webSettings= mvpView.getPage_WebView().getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mvpView.getPage_WebView().getSettings().setUseWideViewPort(true);

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//不使用缓存，只从网络获取数据.
        //支持屏幕缩放
        webSettings.setSupportZoom(false);
        // 是否可用Javascript(window.open)打开窗口，默认值 false
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setPluginState(WebSettings.PluginState.ON);
        mvpView.getPage_WebView().setWebChromeClient(new WebChromeClient());
        mvpView.getPage_WebView().setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                try {
                    mvpView.getPage_WebView().loadUrl(url);
                }catch (Exception E){
                    //
                }
                return true;// true表示此事件在此处被处理，不需要再广播
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }
            @Override
            public void onPageFinished(WebView view, String url) {
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
        });
    }

    @Override
    public void CloseRequest() {

    }

    private void click(){
        mvpView.getInclude_Title_Close().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Include_Title_Close) {
            mvpView.getThisActivity().finish();
        }
    }
}

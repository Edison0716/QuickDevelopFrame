package com.junlong.framecorelibrary.acp;

import android.content.Context;

/**
 * Created by hupei on 2016/4/26.
 */
/*
*使用方法
*Acp.getInstance(this).request(new AcpOptions.Builder()
        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
        ,Manifest.permission.READ_PHONE_STATE
        ,Manifest.permission.SEND_SMS)
        //以下为自定义提示语、按钮文字
        .setDeniedMessage()
        .setDeniedCloseBtn()
        .setDeniedSettingBtn()
        .setRationalMessage()
        .setRationalBtn()
        .build(),
        new AcpListener(){

@Override
public void onGranted(){
        writeSD();
        getIMEI();
        }

@Override
public void onDenied(List<String> permissions){
        makeText(permissions.toString()+"权限拒绝");
        }
        });
*/
public class Acp {

    private static Acp mInstance;
    private AcpManager mAcpManager;

    public static Acp getInstance(Context context) {
        if (mInstance == null)
            synchronized (Acp.class) {
                if (mInstance == null) {
                    mInstance = new Acp(context);
                }
            }
        return mInstance;
    }

    private Acp(Context context) {
        mAcpManager = new AcpManager(context.getApplicationContext());
    }

    /**
     * 开始请求
     *
     * @param options
     * @param acpListener
     */
    public void request(AcpOptions options, AcpListener acpListener) {
        if (options == null) new NullPointerException("AcpOptions is null...");
        if (acpListener == null) new NullPointerException("AcpListener is null...");
        mAcpManager.request(options, acpListener);
    }

    AcpManager getAcpManager() {
        return mAcpManager;
    }
}

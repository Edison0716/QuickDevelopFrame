package com.junlong.framecorelibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/13.
 * 自动安装工具类
 */
public class InstallUtils {
    /**
     * @param fileUrl      文件路径
     * @param activity     上下文
     * @param mAuthorities fileProvide Authorities
     */
    public static void startInstall(String fileUrl, Activity activity, String mAuthorities) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File futureStudioIconFile = new File(fileUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri apkUri = FileProvider.getUriForFile(activity, mAuthorities, futureStudioIconFile);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            install.setDataAndType(Uri.fromFile(futureStudioIconFile), "application/vnd.android.package-archive");
        }
        activity.startActivity(install);
    }

    /**
     *
     * @param fileUrl      文件路径
     * @param activity     上下文
     * @param mAuthorities fileProvide Authorities
     * @param flag         forResult 标记
     */
    public static void startInstall(String fileUrl, Activity activity, String mAuthorities,int flag) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File futureStudioIconFile = new File(fileUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri apkUri = FileProvider.getUriForFile(activity, mAuthorities, futureStudioIconFile);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            install.setDataAndType(Uri.fromFile(futureStudioIconFile), "application/vnd.android.package-archive");
        }
        activity.startActivityForResult(install,flag);
    }
}

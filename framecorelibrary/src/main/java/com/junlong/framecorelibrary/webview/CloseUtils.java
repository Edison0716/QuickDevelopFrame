package com.junlong.framecorelibrary.webview;

import java.io.Closeable;

/**
 * Created by cenxiaozhong on 2017/5/24.
 */

class CloseUtils {


    public static void closeIO(Closeable closeable){
        try {

            if(closeable!=null)
                closeable.close();
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}

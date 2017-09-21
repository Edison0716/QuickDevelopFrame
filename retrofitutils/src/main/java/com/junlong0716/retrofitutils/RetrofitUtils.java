package com.junlong0716.retrofitutils;

import com.junlong0716.retrofitutils.download.DownLoadService;
import com.junlong0716.retrofitutils.download.DownLoadTransformer;
import com.junlong0716.retrofitutils.exception.ServerException;
import com.junlong0716.retrofitutils.log.LogInterceptor;
import com.junlong0716.retrofitutils.upload.UploadOnSubscribe;
import com.junlong0716.retrofitutils.upload.UploadRequestBody;
import com.junlong0716.retrofitutils.utils.FileUtils;
import org.reactivestreams.Publisher;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/20.
 */

public class RetrofitUtils {

    private static BaseRetrofitClient mRetrofitClient;

    public static void setRetrofitClient(BaseRetrofitClient retrofitClient) {
        mRetrofitClient = retrofitClient;
    }

    /**
     * create service
     */
    public static <T> T createService(final Class<T> service) {
        OkHttpClient client = mRetrofitClient.getOkHttpClient().newBuilder().addInterceptor(new LogInterceptor()).build();
        return mRetrofitClient.getRetrofitClient().newBuilder().client(client).build().create(service);
    }

    /**
     * create download service
     */
    public static <T> T createDownLoadService(final Class<T> service) {
        return mRetrofitClient.getRetrofitClient().create(service);
    }

    /**
     * 上传单个文件
     */
    public static <T> Flowable<Object> uploadFile(File file, Class<T> uploadServiceClass, String uploadFucntionName, Object... params) {
//      进度Observable
        UploadOnSubscribe uploadOnSubscribe = new UploadOnSubscribe(file.length());
        Flowable<Integer> progressObservale = Flowable.create(uploadOnSubscribe, BackpressureStrategy.BUFFER);

        UploadRequestBody uploadRequestBody = new UploadRequestBody(file);
//      设置进度监听
        uploadRequestBody.setUploadOnSubscribe(uploadOnSubscribe);


//      创建表单主体
        MultipartBody.Part filePart =
                MultipartBody.Part.createFormData("files", file.getName(), uploadRequestBody);


//      上传
        T service = RetrofitUtils.createService(uploadServiceClass);


        try {
//            获得上传方法的参数类型   和参数
            Class[] paramClasses = new Class[params.length + 1];
            Object[] uploadParams = new Object[params.length + 1];
            paramClasses[params.length] = MultipartBody.Part.class;
            uploadParams[params.length] = filePart;
            for (int i = 0; i < params.length; i++) {
                paramClasses[i] = params[i].getClass();
                uploadParams[i] = params[i];
            }

//            获得上传方法
            Method uploadMethod = uploadServiceClass.getMethod(uploadFucntionName, paramClasses);

//            运行上传方法
            Object o = uploadMethod.invoke(service, uploadParams);

            if (o instanceof Flowable) {
                Flowable uploadFlowable = (Flowable) o;

//              合并Observable
                return Flowable.merge(progressObservale, uploadFlowable)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Flowable.error(e);
        }
        return Flowable.error(new ServerException("no upload method found or api service error", ServerException.ERROR_OTHER));
    }

    /**
     * upload more
     */
    public static <T> Flowable<Object> uploadFiles(ArrayList<File> files, Class<T> uploadsServiceClass, String uploadFucntionName, Object... params) {
//        总长度
        long sumLength = 0l;
        for (File file : files) {
            sumLength += file.length();
        }

//      进度Observable
        UploadOnSubscribe uploadOnSubscribe = new UploadOnSubscribe(sumLength);
        Flowable<Integer> progressObservale = Flowable.create(uploadOnSubscribe, BackpressureStrategy.BUFFER);

        ArrayList<MultipartBody.Part> fileParts = new ArrayList<>();

        for (File file : files) {

            UploadRequestBody uploadRequestBody = new UploadRequestBody(file);
//          设置进度监听
            uploadRequestBody.setUploadOnSubscribe(uploadOnSubscribe);

            fileParts.add(MultipartBody.Part.createFormData("files", file.getName(), uploadRequestBody));
        }

//      上传
        T service = RetrofitUtils.createService(uploadsServiceClass);

        try {
//            获得上传方法的参数类型   和参数
            Class[] paramClasses = new Class[params.length + 1];
            Object[] uploadParams = new Object[params.length + 1];
            paramClasses[params.length] = ArrayList.class;
            uploadParams[params.length] = fileParts;
            for (int i = 0; i < params.length; i++) {
                paramClasses[i] = params[i].getClass();
                uploadParams[i] = params[i];
            }

//            获得上传方法
            Method uploadMethod = uploadsServiceClass.getMethod(uploadFucntionName, paramClasses);

//            运行上传方法
            Object o = uploadMethod.invoke(service, uploadParams);
            if (o instanceof Flowable) {
                Flowable uploadFlowable = (Flowable) o;

//              合并Observable
                return Flowable.merge(progressObservale, uploadFlowable)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Flowable.error(e);
        }
        return Flowable.error(new ServerException("no upload method found or api service error", ServerException.ERROR_OTHER));
    }

    /**
     * download file
     */
    public static Flowable<Object> downLoadFile(String url) {
        return downLoadFile(url, null, null);
    }

    /**
     * download file
     */
    public static Flowable<Object> downLoadFile(String url, String savePath, String fileName) {

        if (savePath == null || savePath.trim().equals("")) {
            savePath = FileUtils.getDefaultDownLoadPath();
        }
        if (fileName == null || fileName.trim().equals("")) {
            fileName = FileUtils.getDefaultDownLoadFileName(url);
        }

        //download listener
        DownLoadTransformer downLoadTransformer = new DownLoadTransformer(savePath, fileName);

        return Flowable
                .just(url)
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(@NonNull String s) throws Exception {
                        DownLoadService downLoadService = RetrofitUtils.createDownLoadService(DownLoadService.class);
                        return downLoadService.startDownLoad(s);
                    }
                })
                .compose(downLoadTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

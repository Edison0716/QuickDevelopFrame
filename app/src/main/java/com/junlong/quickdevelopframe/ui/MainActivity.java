package com.junlong.quickdevelopframe.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Pair;

import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.framecorelibrary.util.StatusBarUtils;
import com.junlong.quickdevelopframe.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvcActivity {
    private TabLayout mTab;
    private ViewPager mPager;
    private List<Pair<String, Fragment>> items;

    @Override
    protected void initStatusBar() {
        StatusBarUtils.setTranslucentForCoordinatorLayout(this,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected int setScreenOrientation() {
        return 0;
    }

    @Override
    protected void initView() {
        mTab = bindView(R.id.tab);
        mPager = bindView(R.id.viewPager);
        mPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }

    @Override
    protected void initData() {
        items = new ArrayList<>();
        items.add(new Pair<String, Fragment>("Rx", new RxFragment()));
        items.add(new Pair<String, Fragment>("Glide", new RxFragment()));

    }

    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position).second;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).first;
        }
    }
    /*private String url = "http://192.168.1.29:9090/school/shuaixun.do";
    private String fileUrl = null;
    private final int IMAGE_PICKER = 0;
    private List<File> mFileList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button update = (Button) findViewById(R.id.update);
        Button choice = (Button) findViewById(R.id.choice);
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // File file = new File(fileUrl);
                OkGo.<String>post(url)
                        .tag(this)
                      //.params("files",file)
                        .addFileParams("files",mFileList)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.d("Json",response.body());
                                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void uploadProgress(Progress progress) {
                                Log.d("progress", String.valueOf(progress.fraction));
                            }
                        });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (int i = 0; i < images.size(); i++) {
                    String path = images.get(i).path;
                    File file = new File(path);
                    mFileList.add(file);
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}

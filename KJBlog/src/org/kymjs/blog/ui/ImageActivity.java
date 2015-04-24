package org.kymjs.blog.ui;

import org.kymjs.blog.R;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapCallBack;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.ScaleImageView;

import android.view.View;
import android.widget.ProgressBar;

public class ImageActivity extends KJActivity {

    public static String URL_KEY = "ImageActivity_url";

    @BindView(id = R.id.progress)
    private ProgressBar mProgressBar;
    @BindView(id = R.id.images)
    private ScaleImageView mImg;

    private String url;
    private KJBitmap kjb;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_image);
    }

    @Override
    public void initData() {
        super.initData();
        url = getIntent().getStringExtra(URL_KEY);
        kjb = new KJBitmap();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        kjb.display(mImg, url, new BitmapCallBack() {
            @Override
            public void onPreLoad() {
                super.onPreLoad();
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}

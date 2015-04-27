package org.kymjs.blog.adapter;

import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.Active;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.utils.AdapterHolder;
import org.kymjs.kjframe.utils.KJAdapter;

import android.graphics.Bitmap;
import android.widget.AbsListView;

public class ActiveAdapter extends KJAdapter<Active> {
    private final KJBitmap kjb = new KJBitmap();

    public ActiveAdapter(AbsListView view, List<Active> mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
    }

    @Override
    public void convert(AdapterHolder helper, Active item, boolean isScrolling) {
        if (isScrolling) {
            Bitmap cache = kjb.getMemoryCache(item.getCover());
            if (cache == null) {
                helper.setImageResource(R.id.iv_event_img, R.drawable.pic_bg);
            } else {
                helper.setImageBitmap(R.id.iv_event_img, cache);
            }
        } else {
            helper.setImageByUrl(kjb, R.id.iv_event_img, item.getCover());
        }
        helper.setText(R.id.tv_event_date, item.getStartTime());
        helper.setText(R.id.tv_event_title, item.getTitle());
        helper.setText(R.id.tv_event_time, item.getSpot());
    }
}

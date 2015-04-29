package org.kymjs.blog.adapter;

import java.util.Collection;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.CollectData;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import android.widget.AbsListView;

public class CollectAdapter extends KJAdapter<CollectData> {

    public CollectAdapter(AbsListView view, Collection<CollectData> mDatas,
            int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
    }

    @Override
    public void convert(AdapterHolder helper, CollectData item,
            boolean isScrolling) {
        helper.setText(R.id.item_collect_title, item.getName());
        helper.setText(R.id.item_collect_url, item.getUrl());
    }
}

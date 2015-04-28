package org.kymjs.blog.adapter;

import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.BlogAuthor;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import android.view.View;
import android.widget.AbsListView;

/**
 * 大神列表
 * 
 * @author kymjs
 * 
 */
public class BlogAuthorAdapter extends KJAdapter<BlogAuthor> {

    private final KJBitmap kjb = new KJBitmap();

    public BlogAuthorAdapter(AbsListView view, List<BlogAuthor> mDatas,
            int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
    }

    @Override
    public void convert(AdapterHolder helper, BlogAuthor item,
            boolean isScrolling) {
        View view = helper.getView(R.id.item_blogauthor_head);
        kjb.display(view, item.getHead(), 150, 150);
        helper.setText(R.id.item_blogauthor_tv_name, item.getDescription());
        helper.setText(R.id.item_blogauthor_tv_desc, item.getName());

    }
}

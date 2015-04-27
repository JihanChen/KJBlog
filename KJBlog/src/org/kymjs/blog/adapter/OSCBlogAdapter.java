package org.kymjs.blog.adapter;

import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.OSCBlog;
import org.kymjs.blog.utils.TimeUtils;
import org.kymjs.kjframe.utils.AdapterHolder;
import org.kymjs.kjframe.utils.KJAdapter;
import org.kymjs.kjframe.utils.StringUtils;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

public class OSCBlogAdapter extends KJAdapter<OSCBlog> {

    public OSCBlogAdapter(AbsListView view, List<OSCBlog> mDatas,
            int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
    }

    static class ViewHolder {
        ImageView img_tody;
        ImageView img_recommend;
        TextView tv_title;
        TextView tv_description;
        TextView tv_author;
        TextView tv_time;
    }

    @Override
    public void convert(AdapterHolder helper, OSCBlog item, boolean isScrolling) {
        View img_tody = helper.getView(R.id.item_blog_tip_tody);
        View img_recommend = helper.getView(R.id.item_blog_tip_recommend);
        if (TimeUtils.dateIsTody(item.getPubDate())) {
            img_tody.setVisibility(View.VISIBLE);
            img_recommend.setVisibility(View.GONE);
        } else {
            img_tody.setVisibility(View.GONE);
            img_recommend.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.item_blog_tv_author, item.getAuthorname());
        helper.setText(R.id.item_blog_tv_date,
                StringUtils.friendlyTime(item.getPubDate()));

        String content = item.getTitle();
        int boundary = content.indexOf("——");
        if (boundary > 0) {
            helper.setText(R.id.item_blog_tv_title,
                    content.substring(boundary + 2));
            helper.setText(R.id.item_blog_tv_description,
                    content.substring(0, boundary));
        } else {
            helper.setText(R.id.item_blog_tv_title, content);
            helper.setText(R.id.item_blog_tv_description, item.getAuthorname()
                    + "的博客");
        }

    }
}

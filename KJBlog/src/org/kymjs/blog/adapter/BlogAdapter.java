package org.kymjs.blog.adapter;

import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.Blog;
import org.kymjs.blog.utils.UIHelper;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.utils.AdapterHolder;
import org.kymjs.kjframe.utils.KJAdapter;
import org.kymjs.kjframe.utils.StringUtils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 主界面博客模块列表适配器
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class BlogAdapter extends KJAdapter<Blog> {
    private final KJBitmap kjb = new KJBitmap();

    public BlogAdapter(AbsListView view, List<Blog> mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
    }

    static class ViewHolder {
        ImageView img_tip_tody;
        ImageView img_tip_recommend;
        ImageView img_image;
        TextView tv_title;
        TextView tv_description;
        TextView tv_author;
        TextView tv_date;
    }

    @Override
    public void convert(AdapterHolder helper, Blog item, boolean isScrolling) {
        helper.getView(R.id.item_blog_tip_recommend).setVisibility(
                item.getIsRecommend() == 0 ? View.GONE : View.VISIBLE);
        helper.getView(R.id.item_blog_tip_tody).setVisibility(
                item.getIsAuthor() == 0 ? View.GONE : View.VISIBLE);
        ImageView image = helper.getView(R.id.item_blog_img);
        String url = item.getImageUrl();
        if (StringUtils.isEmpty(url)) {
            image.setVisibility(View.GONE);
        } else {
            kjb.display(image, url, 480, 420, R.drawable.pic_bg);
            image.setVisibility(View.VISIBLE);
            onPicClick(image, url);
        }
        helper.setText(R.id.item_blog_tv_title, item.getTitle());
        helper.setText(R.id.item_blog_tv_description, item.getDescription());
        helper.setText(R.id.item_blog_tv_author, "张涛");
        helper.setText(R.id.item_blog_tv_date,
                StringUtils.friendlyTime(item.getDate()));
    }

    private void onPicClick(View view, final String url) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.toGallery(v.getContext(), url);
            }
        });
    }

}

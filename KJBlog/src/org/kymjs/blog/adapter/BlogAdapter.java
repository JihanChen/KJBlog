package org.kymjs.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.Blog;
import org.kymjs.blog.ui.widget.listview.StickyHeadAdapter;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.utils.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * 主界面博客模块列表适配器
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class BlogAdapter extends BaseAdapter implements StickyHeadAdapter,
        SectionIndexer {

    private final Context cxt;
    private List<Blog> datas;
    private final KJBitmap kjb;
    private int[] stickyHeadIndex;

    public BlogAdapter(Context cxt, List<Blog> datas) {
        this.cxt = cxt;
        kjb = KJBitmap.create();
        if (datas == null) {
            datas = new ArrayList<Blog>(1);
        }
        this.datas = datas;
        initMessageDataList();
    }

    /**
     * 初始化与每日推荐相关的数据
     */
    private void initMessageDataList() {
        List<Integer> tempIndex = new ArrayList<Integer>(7);
        String lastDate = datas.get(0).getDate();
        tempIndex.add(0);

        for (int i = 1; i < datas.size(); i++) {
            if (!datas.get(i).getDate().equals(lastDate)) {
                lastDate = datas.get(i).getDate();
                tempIndex.add(i);
            }
        }
        stickyHeadIndex = new int[tempIndex.size()];
        for (int i = 0; i < tempIndex.size(); i++) {
            stickyHeadIndex[i] = tempIndex.get(i);
        }
    }

    public void refresh(List<Blog> datas) {
        if (datas == null) {
            datas = new ArrayList<Blog>(1);
        }
        this.datas = datas;
        initMessageDataList();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Blog data = datas.get(position);
        if (convertView == null) {
            convertView = View.inflate(cxt, R.layout.item_list_blog, null);
            holder = new ViewHolder();
            holder.img_tip_recommend = (ImageView) convertView
                    .findViewById(R.id.item_blog_tip_recommend);
            holder.img_tip_tody = (ImageView) convertView
                    .findViewById(R.id.item_blog_tip_tody);
            holder.img_image = (ImageView) convertView
                    .findViewById(R.id.item_blog_img);
            holder.tv_title = (TextView) convertView
                    .findViewById(R.id.item_blog_tv_title);
            holder.tv_description = (TextView) convertView
                    .findViewById(R.id.item_blog_tv_description);
            holder.tv_author = (TextView) convertView
                    .findViewById(R.id.item_blog_tv_author);
            holder.tv_date = (TextView) convertView
                    .findViewById(R.id.item_blog_tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img_tip_recommend
                .setVisibility(data.getIsRecommend() == 0 ? View.GONE
                        : View.VISIBLE);
        holder.img_tip_tody.setVisibility(data.getIsAuthor() == 0 ? View.GONE
                : View.VISIBLE);
        holder.tv_title.setText(data.getTitle());
        holder.tv_description.setText(data.getDescription());
        holder.tv_author.setText("张涛");
        holder.tv_date.setText(StringUtils.friendlyTime(data.getDate()));
        String url = data.getImageUrl();
        if (StringUtils.isEmpty(url)) {
            holder.img_image.setVisibility(View.GONE);
        } else {
            kjb.display(holder.img_image, url);
            holder.img_image.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    /**
     * 在第几个显示stickyHeadView
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        if (stickyHeadIndex.length == 0) {
            return 0;
        }

        if (sectionIndex >= stickyHeadIndex.length) {
            sectionIndex = stickyHeadIndex.length - 1;
        } else if (sectionIndex < 0) {
            sectionIndex = 0;
        }
        return stickyHeadIndex[sectionIndex];
    }

    /**
     * 在0-position中，显示多少个stickyHeadView，类似getCount()
     */
    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < stickyHeadIndex.length; i++) {
            if (position < stickyHeadIndex[i]) {
                return i - 1;
            }
        }
        return stickyHeadIndex.length - 1;
    }

    static class HeadViewHolder {
        ImageView img_tip_tody;
        TextView tv_title;
        TextView tv_description;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder hesdHolder = null;
        if (convertView == null) {
            convertView = View.inflate(cxt, R.layout.item_list_blog_stackyhead,
                    null);
            hesdHolder = new HeadViewHolder();
            hesdHolder.img_tip_tody = (ImageView) convertView
                    .findViewById(R.id.item_blog_tip_tody);
            convertView.setTag(hesdHolder);
        } else {
            hesdHolder = (HeadViewHolder) convertView.getTag();
        }

        if (position == 0) {
            hesdHolder.img_tip_tody.setVisibility(View.VISIBLE);
        } else {
            hesdHolder.img_tip_tody.setVisibility(View.GONE);
        }
        return convertView;
    }

    /**
     * 类似于getItem()
     */
    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 类似于getItemId()
     */
    @Override
    public String getHeaderId(int position) {
        return datas.get(position).getDate();
    }
}

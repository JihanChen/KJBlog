package org.kymjs.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.OSCBlog;
import org.kymjs.blog.utils.TimeUtils;
import org.kymjs.kjframe.utils.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OSCBlogAdapter extends BaseAdapter {

    private final Context cxt;
    private List<OSCBlog> datas;

    public OSCBlogAdapter(Context cxt, List<OSCBlog> datas) {
        this.cxt = cxt;
        if (datas == null) {
            datas = new ArrayList<OSCBlog>(1);
        }
        this.datas = datas;
    }

    public void refresh(List<OSCBlog> datas) {
        if (datas == null) {
            datas = new ArrayList<OSCBlog>(1);
        }
        this.datas = datas;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
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
        ImageView img_tody;
        ImageView img_recommend;
        TextView tv_title;
        TextView tv_description;
        TextView tv_author;
        TextView tv_time;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        OSCBlog data = datas.get(position);
        if (v == null) {
            v = View.inflate(cxt, R.layout.item_list_blog, null);
            holder = new ViewHolder();
            holder.img_tody = (ImageView) v
                    .findViewById(R.id.item_blog_tip_tody);
            holder.img_recommend = (ImageView) v
                    .findViewById(R.id.item_blog_tip_recommend);
            holder.tv_title = (TextView) v
                    .findViewById(R.id.item_blog_tv_title);
            holder.tv_author = (TextView) v
                    .findViewById(R.id.item_blog_tv_author);
            holder.tv_time = (TextView) v.findViewById(R.id.item_blog_tv_date);
            holder.tv_description = (TextView) v
                    .findViewById(R.id.item_blog_tv_description);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (TimeUtils.dateIsTody(data.getPubDate())) {
            holder.img_tody.setVisibility(View.VISIBLE);
            holder.img_recommend.setVisibility(View.GONE);
        } else {
            holder.img_tody.setVisibility(View.GONE);
            holder.img_recommend.setVisibility(View.VISIBLE);
        }
        holder.tv_author.setText(data.getAuthorname());
        holder.tv_time.setText(StringUtils.friendlyTime(data.getPubDate()));
        String content = data.getTitle();
        int boundary = content.indexOf("——");
        if (boundary > 0) {
            holder.tv_title.setText(content.substring(boundary + 2));
            holder.tv_description.setText(content.substring(0, boundary));
        } else {
            holder.tv_title.setText(content);
            holder.tv_description.setText(data.getAuthorname() + "的博客");
        }
        return v;
    }
}

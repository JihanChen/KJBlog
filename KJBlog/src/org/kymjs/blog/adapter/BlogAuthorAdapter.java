package org.kymjs.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.BlogAuthor;
import org.kymjs.kjframe.KJBitmap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 大神列表
 * 
 * @author kymjs
 * 
 */
public class BlogAuthorAdapter extends BaseAdapter {

    private final Context cxt;
    private List<BlogAuthor> datas;
    private final KJBitmap kjb;

    public BlogAuthorAdapter(Context cxt, List<BlogAuthor> datas) {
        if (datas == null) {
            datas = new ArrayList<BlogAuthor>(0);
        }
        this.cxt = cxt;
        this.datas = datas;
        this.kjb = new KJBitmap();
    }

    public void refresh(List<BlogAuthor> datas) {
        if (datas == null) {
            datas = new ArrayList<BlogAuthor>(0);
        }
        this.datas = datas;
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
        return datas.get(position).getId();
    }

    static class ViewHolder {
        ImageView img_head;
        TextView tv_name;
        TextView tv_description;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        BlogAuthor data = datas.get(position);
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(cxt, R.layout.item_blog_author, null);
            holder.img_head = (ImageView) v
                    .findViewById(R.id.item_blogauthor_head);
            holder.tv_name = (TextView) v
                    .findViewById(R.id.item_blogauthor_tv_name);
            holder.tv_description = (TextView) v
                    .findViewById(R.id.item_blogauthor_tv_desc);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        kjb.display(holder.img_head, data.getHead(), 150, 150);
        holder.tv_description.setText(data.getDescription());
        holder.tv_name.setText(data.getName());
        return v;
    }
}

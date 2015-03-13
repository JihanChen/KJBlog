package org.kymjs.blog.adapter;

import org.kymjs.blog.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 每日推送界面列表适配器
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class WeChatAdapter extends BaseAdapter {
    private final Context cxt;

    public WeChatAdapter(Context cxt) {
        this.cxt = cxt;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        ImageView img;
        TextView title;
        RelativeLayout layoutHead;
        TextView singleTitle;
        ImageView singleImg;
        TextView singleDescription;
        RelativeLayout singleLayout;
        LinearLayout root;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            v = View.inflate(cxt, R.layout.item_list_wechat, null);
            holder = new ViewHolder();
            holder.img = (ImageView) v.findViewById(R.id.item_wechat_img_head);
            holder.title = (TextView) v.findViewById(R.id.item_wechat_tv_head);
            holder.layoutHead = (RelativeLayout) v
                    .findViewById(R.id.item_wechat_layout_head);
            holder.singleTitle = (TextView) v
                    .findViewById(R.id.item_wechat_tv_single);
            holder.singleDescription = (TextView) v
                    .findViewById(R.id.item_wechat_tv_single_content);
            holder.singleImg = (ImageView) v
                    .findViewById(R.id.item_wechat_img_single);
            holder.singleLayout = (RelativeLayout) v
                    .findViewById(R.id.item_wechat_layout_single);
            holder.root = (LinearLayout) v;
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.singleLayout.setVisibility(View.GONE);
        holder.layoutHead.setVisibility(View.VISIBLE);
        holder.root.addView(View.inflate(cxt, R.layout.item_wechat_list, null));
        return v;
    }
}

package org.kymjs.blog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}

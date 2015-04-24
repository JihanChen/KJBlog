package org.kymjs.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.Active;
import org.kymjs.kjframe.KJBitmap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActiveAdapter extends BaseAdapter {

    private final Context cxt;
    private List<Active> datas;
    private final KJBitmap kjb = new KJBitmap();

    public ActiveAdapter(Context cxt, List<Active> datas) {
        if (datas == null) {
            datas = new ArrayList<Active>(1);
        }
        this.cxt = cxt;
        this.datas = datas;
    }

    public void refresh(List<Active> datas) {
        if (datas == null) {
            datas = new ArrayList<Active>(1);
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
        return position;
    }

    private static class ViewHolder {
        ImageView iv_event_img;
        TextView tv_event_title;
        TextView tv_event_date;
        TextView tv_event_time;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        Active data = datas.get(position);
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(cxt, R.layout.item_list_active, null);
            holder.iv_event_img = (ImageView) v.findViewById(R.id.iv_event_img);
            holder.tv_event_title = (TextView) v
                    .findViewById(R.id.tv_event_title);
            holder.tv_event_date = (TextView) v
                    .findViewById(R.id.tv_event_date);
            holder.tv_event_time = (TextView) v
                    .findViewById(R.id.tv_event_time);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        kjb.display(holder.iv_event_img, data.getCover());
        holder.tv_event_title.setText(data.getTitle());
        holder.tv_event_date.setText(data.getStartTime());
        holder.tv_event_time.setText(data.getSpot());
        return v;
    }
}

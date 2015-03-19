package org.kymjs.blog.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.Tweet;
import org.kymjs.blog.ui.widget.CollapsibleTextView;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.utils.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends BaseAdapter {

    private final Context cxt;

    private final KJBitmap kjb;

    private TreeSet<Tweet> datas;
    private List<Tweet> datasList;

    public TweetAdapter(Context cxt, TreeSet<Tweet> datas) {
        if (datas == null) {
            datas = new TreeSet<Tweet>();
        }
        kjb = KJBitmap.create();
        this.cxt = cxt;
        this.datas = datas;

        datasList = new ArrayList<Tweet>(datas);
    }

    public void refresh(TreeSet<Tweet> datas) {
        if (datas == null) {
            datas = new TreeSet<Tweet>();
        }
        this.datas = datas;
        datasList = new ArrayList<Tweet>(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datasList.size();
    }

    @Override
    public Object getItem(int position) {
        return datasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        ImageView img_head;
        TextView tv_name;
        CollapsibleTextView tv_content;
        ImageView img_pic;
        TextView tv_date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet data = datasList.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(cxt, R.layout.item_list_tweet, null);
            holder = new ViewHolder();
            holder.img_head = (ImageView) convertView
                    .findViewById(R.id.msg_item_img_head);
            holder.tv_name = (TextView) convertView
                    .findViewById(R.id.msg_item_text_uname);
            holder.tv_content = (CollapsibleTextView) convertView
                    .findViewById(R.id.msg_item_text_content);
            holder.tv_date = (TextView) convertView
                    .findViewById(R.id.msg_item_text_time);
            holder.img_pic = (ImageView) convertView
                    .findViewById(R.id.msg_item_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 由于头像地址默认加了一段参数需要去掉
        String headUrl = data.getPortrait();
        int end = headUrl.indexOf('?');
        if (end > 0) {
            headUrl = headUrl.substring(0, end);
        }
        kjb.display(holder.img_head, headUrl, R.drawable.head_def, 135, 135);
        holder.tv_name.setText(data.getAuthor());
        holder.tv_content.setText(data.getBody());
        holder.tv_date.setText(StringUtils.friendlyTime(data.getPubDate()));
        if (StringUtils.isEmpty(data.getImgBig())) {
            holder.img_pic.setVisibility(View.GONE);
        } else {
            holder.img_pic.setVisibility(View.VISIBLE);
            kjb.display(holder.img_pic, data.getImgBig());
        }
        return convertView;
    }
}

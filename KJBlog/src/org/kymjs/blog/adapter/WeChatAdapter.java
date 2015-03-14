package org.kymjs.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.EverydayMessage;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.utils.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
<<<<<<< HEAD

import org.kymjs.blog.R;
import org.kymjs.blog.domain.EverydayMessage;
import org.kymjs.kjframe.KJBitmap;

import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> origin/dev

/**
 * 每日推送界面列表适配器
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class WeChatAdapter extends BaseAdapter {
    private final Context cxt;
    private final List<EverydayMessage> datas;
    private final KJBitmap kjb = KJBitmap.create();

    public WeChatAdapter(Context cxt, List<EverydayMessage> datas) {
        this.cxt = cxt;
        if (datas == null) {
            datas = new ArrayList<EverydayMessage>(0);
        }
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
        TextView tiem;
    }

    /**
     * 变量命名：多图文消息中分title和item，title指代第一项那个大的列表项，而item表示小的列表项<br>
     * 单图文消息中只有标题，图片，摘要
     */
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        EverydayMessage data = datas.get(position);
        int itemCount = 0; // 标题下面的栏目共有多少个
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
            holder.tiem = (TextView) v.findViewById(R.id.item_wechat_time);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
            itemCount = holder.root.getChildCount() - 3;
        }

        if (data.isHasItem()) {
            // 多图文消息
            holder.singleLayout.setVisibility(View.GONE);
            holder.layoutHead.setVisibility(View.VISIBLE);
            initMsgItem(holder.root, itemCount, data);
        } else {
            // 单图文消息
            holder.singleLayout.setVisibility(View.VISIBLE);
            holder.layoutHead.setVisibility(View.GONE);
            holder.singleDescription.setText(data.getDescription());
            holder.singleTitle.setText(data.getTitle());
            kjb.display(holder.singleImg, data.getImgUrl());
        }
<<<<<<< HEAD
        //holder.tiem.setText(StringUtils.friendlyTime(data.getTime()));
=======
        holder.tiem.setText(StringUtils.friendlyTime(data.getTime()));
>>>>>>> origin/dev
        return v;
    }

    /**
     * 初始化多图文消息中下半部分
     */
    private void initMsgItem(LinearLayout root, int itemCount,
            EverydayMessage data) {
        // 已有的layout直接复用修改数据
        for (int i = 0; i < itemCount; i++) {
            RelativeLayout itemView = (RelativeLayout) root.getChildAt(i + 3);
            initItem(data, i, itemView);
        }
        if (data.getUrlList().size() == itemCount) { // 正好容纳
            return;
        } else if (data.getUrlList().size() > itemCount) { // 当需要额外添加item
            for (int i = itemCount; i < data.getUrlList().size(); i++) {
                RelativeLayout itemView = (RelativeLayout) View.inflate(cxt,
                        R.layout.item_wechat_list, null);
                initItem(data, i, itemView);
                root.addView(itemView);
            }
        } else { // 需要移除item
            for (int i = itemCount; i < data.getUrlList().size(); i++) {
                root.removeViewAt(i);
            }
        }
    }

    /**
     * 初始化多图文消息的item
     * 
     * @param data
     * @param i
     * @param itemView
     */
    private void initItem(EverydayMessage data, int i, RelativeLayout itemView) {
        ImageView itemImg = (ImageView) itemView.getChildAt(0);
        TextView itemText = (TextView) itemView.getChildAt(1);
        kjb.display(itemImg, data.getImageUrlList().get(i));
        itemText.setText(data.getTitleList().get(i));
        itemView.setOnClickListener(getItemMessageClickListener(data
                .getUrlList().get(i)));
    }

    /**
     * 当点击一个标题时，跳转到浏览器显示参数地址
     * 
     * @param url
     *            要显示的url
     * @return 点击事件监听器
     */
    private OnClickListener getItemMessageClickListener(final String url) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}

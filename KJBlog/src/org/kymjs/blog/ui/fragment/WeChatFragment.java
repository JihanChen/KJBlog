package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;
import org.kymjs.kjframe.ui.BindView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 仿照公众号推送界面创建的每日推送列表<br>
 * 有关本界面还有一段很有意思的故事：最初做这个界面的时候，出于偷懒，并没有打算自己写，在网上搜索到了一篇写这种布局的博客，博主叫秦元培。(http://
 * blog.csdn.net/qinyuanpei/article/details/17734755)。心想，这次又可以少写好多代码了。
 * 可是博客内并没有提供项目介绍的sample,于是根据博客中QQ信息加发送了好友请求:你好，刚看了你的博客，麻烦你能发一份Demo给我吗。结果被拒绝了。。。<br>
 * 诶，还是自己写吧,于是才有了这个界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class WeChatFragment extends TitleBarFragment {

    @BindView(id = R.id.wechat_listview)
    private ListView mList;

    private Activity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        aty = getActivity();
        View root = View.inflate(aty, R.layout.frag_wechat, null);
        return root;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }
}

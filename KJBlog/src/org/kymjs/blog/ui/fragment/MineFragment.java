package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;
import org.kymjs.kjframe.ui.BindView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 我爱看界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 * 
 */
public class MineFragment extends TitleBarFragment {

    @BindView(id = R.id.mine_tv_collect, click = true)
    private TextView mTvCollect;
    @BindView(id = R.id.mine_tv_join, click = true)
    private TextView mTvJoin;
    @BindView(id = R.id.mine_tv_other, click = true)
    private TextView mTvOther;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(outsideAty, R.layout.frag_mine, null);
        return view;
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.mine_tv_collect:
            break;
        case R.id.mine_tv_other:
            break;
        case R.id.mine_tv_join:
            doJoin();
            break;
        default:
            break;
        }
    }

    private void doJoin() {
        // intent.setData(Uri.parse("http://weixin.qq.com/r/Q0gBGfTEsqnNrbB69x1R"));
        // 怎样才能打开指定的Activity？通过反编译已经获取到Activity的参数key但是没有启动权限
        // ComponentName componetName = new ComponentName("com.tencent.mm",
        // "com.tencent.mm.ui.tools.ShowImageUI");
        //
        // Intent intent = new Intent();
        // intent.putExtra("key_title", "你好");
        // intent.putExtra("key_image_path", FileUtils.getSDCardPath()
        // + "/qrcode.jpg");
        // intent.setComponent(componetName);
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // try {
        // Context cxt = CJTool
        // .getCxtFromPkgName(outsideAty, "com.tencent.mm");
        // cxt.startActivity(intent);
        // } catch (NameNotFoundException e) {
        // }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://jq.qq.com/?_wv=1027&k=XblWhv"));
        startActivity(intent);
    }
}

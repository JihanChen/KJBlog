package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.AppConfig;
import org.kymjs.blog.R;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.utils.PreferenceHelper;
import org.kymjs.kjframe.utils.SystemTool;
import org.kymjs.push.core.KJPushManager;
import org.kymjs.push.core.PushReceiver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
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
    @BindView(id = R.id.mine_rl_feedback, click = true)
    private RelativeLayout mRlFeedback;
    @BindView(id = R.id.mine_rl_about, click = true)
    private RelativeLayout mRlAbout;
    @BindView(id = R.id.mine_rl_push, click = true)
    private RelativeLayout mRlPush;
    @BindView(id = R.id.mine_rl_clean, click = true)
    private RelativeLayout mRlClean;
    @BindView(id = R.id.mine_rl_exit, click = true)
    private RelativeLayout mRlExit;
    @BindView(id = R.id.mine_tv_currentversion)
    private TextView mTvVersion;
    @BindView(id = R.id.mine_cbox_push)
    private CheckBox mCbox;

    private boolean isChanged = false; // box是否有改变

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(outsideAty, R.layout.frag_mine, null);
        return view;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = getString(R.string.app_name);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mTvVersion.setText("当前版本" + SystemTool.getAppVersion(outsideAty));
        mCbox.setChecked(PreferenceHelper.readBoolean(outsideAty,
                AppConfig.PUSH_SWITCH_FILE, AppConfig.PUSH_SWITCH_KEY));
        mCbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                PreferenceHelper.write(outsideAty, AppConfig.PUSH_SWITCH_FILE,
                        AppConfig.PUSH_SWITCH_KEY, isChecked);
            }
        });
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
        case R.id.mine_rl_push:
            isChanged = true;
            mCbox.setChecked(!mCbox.isChecked());
            break;
        case R.id.mine_rl_about:
            break;
        case R.id.mine_rl_clean:
            break;
        case R.id.mine_rl_feedback:
            break;
        case R.id.mine_rl_exit:
            KJActivityStack.create().AppExit(outsideAty);
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

    @Override
    public void onDestroy() {
        if (isChanged) {
            boolean isOpen = PreferenceHelper.readBoolean(outsideAty,
                    AppConfig.PUSH_SWITCH_FILE, AppConfig.PUSH_SWITCH_KEY);
            if (isOpen) {
                KJPushManager.create()
                        .startWork(outsideAty, PushReceiver.class);
            } else {
                KJPushManager.create().stopWork();
            }
        }
        super.onDestroy();
    }
}

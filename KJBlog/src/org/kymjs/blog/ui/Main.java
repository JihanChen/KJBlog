package org.kymjs.blog.ui;

import org.kymjs.blog.R;
import org.kymjs.blog.ui.fragment.BlogFragment;
import org.kymjs.blog.ui.fragment.FindFragment;
import org.kymjs.blog.ui.fragment.MineFragment;
import org.kymjs.blog.ui.fragment.TitleBarFragment;
import org.kymjs.kjframe.ui.BindView;

import android.view.View;
import android.widget.RadioButton;

/**
 * 应用主界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class Main extends TitleBarActivity {

    @BindView(id = R.id.bottombar_content1, click = true)
    private RadioButton mRbtnContent1;
    @BindView(id = R.id.bottombar_content2, click = true)
    private RadioButton mRbtnContent2;
    @BindView(id = R.id.bottombar_content3, click = true)
    private RadioButton mRbtnContent3;

    private TitleBarFragment contentFragment1;
    private TitleBarFragment contentFragment2;
    private TitleBarFragment contentFragment3;
    private TitleBarFragment currentFragment;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_main);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment1 = new BlogFragment();
        contentFragment2 = new FindFragment();
        contentFragment3 = new MineFragment();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        changeFragment(contentFragment1);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.bottombar_content1:
            changeFragment(contentFragment1);
            break;
        case R.id.bottombar_content2:
            changeFragment(contentFragment2);
            break;
        case R.id.bottombar_content3:
            changeFragment(contentFragment3);
            break;
        default:
            break;
        }
    }

    @Override
    protected void onBackClick() {
        super.onBackClick();
        currentFragment.onBackClick();
    }

    @Override
    protected void onMenuClick() {
        super.onMenuClick();
        currentFragment.onMenuClick();
    }

    public void changeFragment(TitleBarFragment targetFragment) {
        currentFragment = targetFragment;
        super.changeFragment(R.id.main_content, targetFragment);
    }
}

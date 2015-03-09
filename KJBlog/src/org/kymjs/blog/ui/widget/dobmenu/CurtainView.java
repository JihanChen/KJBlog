package org.kymjs.blog.ui.widget.dobmenu;

import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.OnSwitchListener;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.SlidingType;
import org.kymjs.blog.ui.widget.dobmenu.CurtainViewController.SlidingStatus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 尊重原创感谢开源：本控件是由Startappz的DobSliding(https://github.com/Startappz/DobSliding)
 * 项目修改而来，由于最初代码只能使用在系统ActionBar上，不适合完全自定义ActionBar，而且写的比较杂乱也没有什么注释，
 * 我进行了几乎全部的重构并添加了必要注释，希望能帮到你<br>
 * 
 * @author Startappz (https://github.com/Startappz/DobSliding)
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 * 
 */
public class CurtainView {
    private final Activity activity;
    private final CurtainItem curtainItem;
    private final CurtainViewController curtainViewController;

    public CurtainView(Activity activity, int actionBarId) {
        super();
        this.activity = activity;
        curtainItem = new CurtainItem();
        curtainViewController = new CurtainViewController(activity,
                curtainItem, actionBarId);
    }

    /**
     * 窗帘拉下来
     */
    public void expand() {
        curtainViewController.expand();
    }

    /**
     * 窗帘推上去
     */
    public void collapse() {
        curtainViewController.collapse();
    }

    public void finish() {
        curtainViewController.finish();
    }

    /**
     * 是否启用Sliding
     * 
     * @return
     */
    public boolean isEnabled() {
        return curtainItem.isEnabled();
    }

    /**
     * 是否启用Sliding
     */
    public void setEnabled(boolean enabled) {
        curtainItem.setEnabled(enabled);
        curtainViewController.setEnabled(enabled);
    }

    /**
     * 当前状态:显示，滚动上去，滚动中
     * 
     * @return
     */
    public SlidingStatus getSlidingStatus() {
        return curtainViewController.getSlidingStatus();
    }

    /**
     * 获取到窗帘布局对象
     * 
     * @return
     */
    public View getContentView() {
        return curtainItem.getSlidingView();
    }

    public void setSlidingView(View slidingView) {
        curtainItem.setSlidingView(slidingView);
        curtainViewController.setCurtainView(slidingView);
    }

    public void setSlidingView(int slidingResId) {
        View slidingView = LayoutInflater.from(activity).inflate(slidingResId,
                null, false);
        setSlidingView(slidingView);
    }

    public SlidingType getSlidingType() {
        return curtainItem.getSlidingType();
    }

    public void setSlidingType(SlidingType slidingType) {
        curtainItem.setSlidingType(slidingType);
        curtainViewController.setSlidingType(slidingType);
    }

    public int getMaxDuration() {
        return curtainItem.getMaxDuration();
    }

    public void setMaxDuration(int maxDuration) {
        curtainItem.setMaxDuration(maxDuration);
    }

    public float getJumpLinePercentage() {
        return curtainItem.getJumpLinePercentage();
    }

    public void setJumpLinePercentage(float jumpLinePercentage) {
        curtainItem.setJumpLinePercentage(jumpLinePercentage);
    }

    public OnSwitchListener getOnSwitchListener() {
        return curtainItem.getOnSwitchListener();
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        curtainItem.setOnSwitchListener(onSwitchListener);
    }

    /**
     * 点击窗帘View时回调
     * 
     * @param l
     */
    public void setOnClickListener(OnClickListener l) {
        curtainViewController.getSlidingParent().setOnClickListener(l);
    }
}

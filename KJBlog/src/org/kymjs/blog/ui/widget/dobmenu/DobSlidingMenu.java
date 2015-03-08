package org.kymjs.blog.ui.widget.dobmenu;

import org.kymjs.blog.ui.widget.dobmenu.SlidingItem.SlidingType;
import org.kymjs.blog.ui.widget.dobmenu.VSlidingMenuController.SlidingStatus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

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
public class DobSlidingMenu {
    private final Activity activity;
    private SlidingItem slidingItem;
    private VSlidingMenuController vSlidingMenuController;

    public DobSlidingMenu(Activity activity) {
        super();
        this.activity = activity;
        init();
    }

    private void init() {
        slidingItem = new SlidingItem();
        vSlidingMenuController = new VSlidingMenuController(activity,
                slidingItem);
    }

    /**
     * 滚动上去
     */
    public void expand() {
        vSlidingMenuController.expand();
    }

    /**
     * 拉下来
     */
    public void collapse() {
        vSlidingMenuController.collapse();
    }

    public void finish() {
        vSlidingMenuController.finish();
    }

    /**
     * 是否启用SlidingMenu
     * 
     * @return
     */
    public boolean isEnabled() {
        return slidingItem.isEnabled();
    }

    /**
     * 是否启用SlidingMenu
     * 
     * @return
     */
    public void setEnabled(boolean enabled) {
        slidingItem.setEnabled(enabled);
        vSlidingMenuController.setEnabled(enabled);
    }

    /**
     * 当前状态:显示，滚动上去，滚动中
     * 
     * @return
     */
    public SlidingStatus getSlidingStatus() {
        return vSlidingMenuController.getSlidingStatus();
    }

    /**
     * 获取到SlidingMenu布局对象
     * 
     * @return
     */
    public View getSlidingView() {
        return slidingItem.getSlidingView();
    }

    public void setSlidingView(View slidingView) {
        slidingItem.setSlidingView(slidingView);
        vSlidingMenuController.setSlidingView(slidingView);
    }

    public void setSlidingView(int slidingResId) {
        View slidingView = LayoutInflater.from(activity).inflate(slidingResId,
                null, false);

        setSlidingView(slidingView);
    }

    public SlidingType getSlidingType() {
        return slidingItem.getSlidingType();
    }

    public void setSlidingType(SlidingType slidingType) {
        slidingItem.setSlidingType(slidingType);
        vSlidingMenuController.setSlidingType(slidingType);
    }

    public int getMaxDuration() {
        return slidingItem.getMaxDuration();
    }

    public void setMaxDuration(int maxDuration) {
        slidingItem.setMaxDuration(maxDuration);
    }

    public float getJumpLinePercentage() {
        return slidingItem.getJumpLinePercentage();
    }

    public void setJumpLinePercentage(float jumpLinePercentage) {
        slidingItem.setJumpLinePercentage(jumpLinePercentage);
    }

    public boolean isUseHandle() {
        return slidingItem.isUseHandle();
    }

    public void setUseHandle(boolean useHandle) {
        slidingItem.setUseHandle(useHandle);
        vSlidingMenuController.setUseHandle(useHandle);
    }

    public OnCollapsedListener getOnCollapsedListener() {
        return slidingItem.getOnCollapsedListener();
    }

    public void setOnCollapsedListener(OnCollapsedListener onCollapsedListener) {
        slidingItem.setOnCollapsedListener(onCollapsedListener);
    }

    public OnExpandedListener getOnExpandedListener() {
        return slidingItem.getOnExpandedListener();
    }

    public void setOnExpandedListener(OnExpandedListener onExpandedListener) {
        slidingItem.setOnExpandedListener(onExpandedListener);
    }
}

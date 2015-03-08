package org.kymjs.blog.ui.widget.dobmenu;

import android.view.View;

public class CurtainItem {

    public enum SlidingType {
        SIZE, MOVE
    }

    private boolean enabled = true;
    private View curtainContentView;
    private SlidingType slidingType = SlidingType.MOVE;
    private int maxDuration = CurtainViewController.DEFAULT_INT;
    private float jumpLinePercentage = CurtainViewController.DEFAULT_JUMP_LINE_PERCENTAGE;

    private OnSwitchListener onSwitchListener;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public View getSlidingView() {
        return curtainContentView;
    }

    public void setSlidingView(View slidingView) {
        this.curtainContentView = slidingView;
    }

    public SlidingType getSlidingType() {
        return slidingType;
    }

    public void setSlidingType(SlidingType slidingType) {
        this.slidingType = slidingType;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public float getJumpLinePercentage() {
        return jumpLinePercentage;
    }

    public void setJumpLinePercentage(float jumpLinePercentage) {
        this.jumpLinePercentage = jumpLinePercentage;
    }

    public OnSwitchListener getOnSwitchListener() {
        return onSwitchListener;
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        this.onSwitchListener = onSwitchListener;
    }

    /**
     * 窗帘开关监听器
     */
    public interface OnSwitchListener {
        /**
         * 掉下时回调
         */
        public void onCollapsed();

        /**
         * 卷起时回调
         */
        public void onExpanded();
    }
}

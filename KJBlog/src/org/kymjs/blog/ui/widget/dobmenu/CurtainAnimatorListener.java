package org.kymjs.blog.ui.widget.dobmenu;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

/**
 * 动画移动过程监听器
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class CurtainAnimatorListener implements AnimatorListener {

    private final CurtainViewController mCurtainViewController;
    private AnimationExecutor.MovingType movingType;

    public CurtainAnimatorListener(CurtainViewController vSlidingMenuController) {
        super();
        this.mCurtainViewController = vSlidingMenuController;
    }

    public AnimationExecutor.MovingType getMovingType() {
        return movingType;
    }

    public void setMovingType(AnimationExecutor.MovingType movingType) {
        this.movingType = movingType;
    }

    @Override
    public void onAnimationStart(Animator animation) {}

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mCurtainViewController != null) {
            if (movingType == AnimationExecutor.MovingType.BOTTOM_TO_TOP) {
                if (mCurtainViewController.getSlidingItem()
                        .getOnSwitchListener() != null) {
                    mCurtainViewController.getSlidingItem()
                            .getOnSwitchListener().onCollapsed();
                }

            } else if (movingType == AnimationExecutor.MovingType.TOP_TO_BOTTOM) {
                if (mCurtainViewController.getSlidingItem()
                        .getOnSwitchListener() != null) {
                    mCurtainViewController.getSlidingItem()
                            .getOnSwitchListener().onExpanded();
                }
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {}

    @Override
    public void onAnimationRepeat(Animator animation) {}

}

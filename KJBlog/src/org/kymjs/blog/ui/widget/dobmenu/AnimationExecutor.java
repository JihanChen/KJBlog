package org.kymjs.blog.ui.widget.dobmenu;

import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.SlidingType;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

/**
 * 执行开关窗帘的动画
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 * 
 */
public class AnimationExecutor {

    private final CurtainViewController mCurtainViewController;

    public AnimationExecutor(CurtainViewController vSlidingMenuController) {
        super();
        this.mCurtainViewController = vSlidingMenuController;
    }

    /**
     * 两种动画类型
     */
    public enum MovingType {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }

    public void animateView(int fromY, int toY) {
        int duration = Math.abs(toY - fromY);
        if (mCurtainViewController.getSlidingItem().getMaxDuration() > CurtainViewController.DEFAULT_INT) {
            duration = Math.min(duration, mCurtainViewController
                    .getSlidingItem().getMaxDuration());
        }

        // 移动方式
        final MovingType movingType = toY == 0 ? MovingType.BOTTOM_TO_TOP
                : MovingType.TOP_TO_BOTTOM;

        String propertyName = "";
        if (mCurtainViewController.getSlidingItem().getSlidingType() == SlidingType.SIZE) {
            propertyName = "viewHeight";

        } else if (mCurtainViewController.getSlidingItem().getSlidingType() == SlidingType.MOVE) {
            propertyName = "viewTop";
        }

        ValueAnimator sizeAnim = ObjectAnimator.ofInt(mCurtainViewController,
                propertyName, fromY, toY);
        sizeAnim.setDuration(duration);
        sizeAnim.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
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

            @Override
            public void onAnimationCancel(Animator animation) {}
        });
        sizeAnim.start();
    }
}

package org.kymjs.blog.ui.widget.dobmenu;

import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.SlidingType;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

/**
 * 执行动画方法类
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 * 
 */
public class AnimationExecutor {

    private final CurtainViewController vSlidingMenuController;

    public AnimationExecutor(CurtainViewController vSlidingMenuController) {
        super();
        this.vSlidingMenuController = vSlidingMenuController;
    }

    /**
     * 两种动画类型
     */
    public enum MovingType {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }

    public void animateView(int fromY, int toY) {
        int duration = Math.abs(toY - fromY);
        if (vSlidingMenuController.getSlidingItem().getMaxDuration() > CurtainViewController.DEFAULT_INT) {
            duration = Math.min(duration, vSlidingMenuController
                    .getSlidingItem().getMaxDuration());
        }

        CurtainAnimatorListener animatorListener = new CurtainAnimatorListener(
                vSlidingMenuController);

        MovingType movingType = toY == 0 ? MovingType.BOTTOM_TO_TOP
                : MovingType.TOP_TO_BOTTOM;
        animatorListener.setMovingType(movingType);

        String propertyName = "";
        if (vSlidingMenuController.getSlidingItem().getSlidingType() == SlidingType.SIZE) {
            propertyName = "viewHeight";

        } else if (vSlidingMenuController.getSlidingItem().getSlidingType() == SlidingType.MOVE) {
            propertyName = "viewTop";
        }
        ValueAnimator sizeAnim = ObjectAnimator.ofInt(vSlidingMenuController,
                propertyName, fromY, toY);
        sizeAnim.setDuration(duration);
        sizeAnim.addListener(animatorListener);
        sizeAnim.start();
    }
}

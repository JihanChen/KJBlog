package org.kymjs.blog.ui.widget.dobmenu;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

public class VSlidingAnimatorListener implements AnimatorListener {

    private final VSlidingMenuController vSlidingMenuController;
    private AnimationExecutor.MovingType movingType;

    public VSlidingAnimatorListener(
            VSlidingMenuController vSlidingMenuController) {
        super();
        this.vSlidingMenuController = vSlidingMenuController;
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
        if (vSlidingMenuController != null) {
            if (movingType == AnimationExecutor.MovingType.BOTTOM_TO_TOP) {

                if (vSlidingMenuController.getSlidingItem()
                        .getOnCollapsedListener() != null) {

                    vSlidingMenuController.getSlidingItem()
                            .getOnCollapsedListener().onCollapsed();
                }

            } else if (movingType == AnimationExecutor.MovingType.TOP_TO_BOTTOM) {

                if (vSlidingMenuController.getSlidingItem()
                        .getOnExpandedListener() != null) {

                    vSlidingMenuController.getSlidingItem()
                            .getOnExpandedListener().onExpanded();
                }
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {}

    @Override
    public void onAnimationRepeat(Animator animation) {}

}

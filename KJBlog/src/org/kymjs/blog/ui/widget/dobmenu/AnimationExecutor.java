package org.kymjs.blog.ui.widget.dobmenu;

import org.kymjs.blog.ui.widget.dobmenu.SlidingItem.SlidingType;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

public class AnimationExecutor {

    private final VSlidingMenuController vSlidingMenuController;

    public AnimationExecutor(VSlidingMenuController vSlidingMenuController) {
        super();
        this.vSlidingMenuController = vSlidingMenuController;
    }

    public enum MovingType {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }

    public void animateView(int fromY, int toY) {
        int duration = Math.abs(toY - fromY);

        if (vSlidingMenuController.getSlidingItem().getMaxDuration() > VSlidingMenuController.DEFAULT_INT) {
            duration = Math.min(duration, vSlidingMenuController
                    .getSlidingItem().getMaxDuration());
        }

        VSlidingAnimatorListener animatorListener = new VSlidingAnimatorListener(
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

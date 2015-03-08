package org.kymjs.blog.ui.widget.dobmenu;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class SlidingParentKeyListener implements OnKeyListener {

    private final VSlidingMenuController vSlidingMenuController;

    public SlidingParentKeyListener(
            VSlidingMenuController vSlidingMenuController) {
        super();
        this.vSlidingMenuController = vSlidingMenuController;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && vSlidingMenuController.getSlidingStatus() == VSlidingMenuController.SlidingStatus.EXPANDED) {
            vSlidingMenuController.collapse();
            return true;
        } else {
            return false;
        }
    }

}

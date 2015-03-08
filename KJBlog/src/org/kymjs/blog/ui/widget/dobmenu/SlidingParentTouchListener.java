package org.kymjs.blog.ui.widget.dobmenu;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SlidingParentTouchListener implements OnTouchListener {

    private VSlidingMenuController vSlidingMenuController;

    public SlidingParentTouchListener() {
        super();
    }

    public void register(VSlidingMenuController vSlidingMenuController) {
        this.vSlidingMenuController = vSlidingMenuController;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        vSlidingMenuController.finish();

        return true;
    }

}

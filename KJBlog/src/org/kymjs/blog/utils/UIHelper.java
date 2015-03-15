package org.kymjs.blog.utils;

import android.content.Context;
import android.content.Intent;

public class UIHelper {
    public static void toHome(Context cxt) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
        intent.addCategory(Intent.CATEGORY_HOME);
        cxt.startActivity(intent);
    }
}

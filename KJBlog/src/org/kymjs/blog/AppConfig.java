package org.kymjs.blog;

/**
 * 配置文件常量
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class AppConfig {
    public static final String saveFolder = "KJBlog";
    public static final String httpCachePath = saveFolder + "/httpCache";
    public static final String imgCachePath = saveFolder + "/imageCache";
    public static final String audioPath = saveFolder + "/audio";

    public static final String CACHE_TIME_KEY = "cache_time_key";

    public static final String SPLASH_HEAD_IMG_KEY = "headimage_key";
    public static final String SPLASH_BACKGROUND_KEY = "main_background_key";
    public static final String SPLASH_BOX_KEY = "main_box_key";
    public static final String SPLASH_CONTENT_KEY = "main_content_key";

    public static final String PUSH_SWITCH_FILE = "push_switch_file";
    public static final String PUSH_SWITCH_KEY = "push_switch_key";

    public static final String PUSH_BROADCAST_ACTION = "org.kymjs.blog.kjpush_has_new_message";
}

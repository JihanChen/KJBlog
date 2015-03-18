package org.kymjs.blog.utils;

import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 解析工具类
 * 
 * @author kymjs
 * 
 */
public class Parser {

    public static <T> T xmlToBean(Class<T> type, String xml) {
        T data = null;
        try {
            XStream xStream = new XStream(new DomDriver("UTF-8"));
            xStream.processAnnotations(type);
            data = (T) xStream.fromXML(xml);
        } catch (Exception e) {
            Log.e(Parser.class.getSimpleName(), "xmlToBean()中解析异常");
        }
        return data;
    }
}

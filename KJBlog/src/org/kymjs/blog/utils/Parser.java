package org.kymjs.blog.utils;

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
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.processAnnotations(type);
        return (T) xStream.fromXML(xml);
    }
}

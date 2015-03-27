package org.kymjs.blog.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.blog.domain.BlogAuthor;
import org.kymjs.kjframe.utils.KJLoger;

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
            try {
                data = type.newInstance();
            } catch (Exception ee) {
            }
        }
        return data;
    }

    public static List<BlogAuthor> getBlogAuthor(String json) {
        List<BlogAuthor> datas = new ArrayList<BlogAuthor>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                BlogAuthor data = new BlogAuthor();
                JSONObject obj = jsonArray.getJSONObject(i);
                data.setHead(obj.optString("image", ""));
                data.setId(obj.optInt("id", 863548));
                data.setName(obj.optString("name", "张涛"));
                data.setDescription(obj.optString("description", "暂无简介"));
                datas.add(data);
            }
        } catch (JSONException e) {
            KJLoger.debug("getBlogAuthor()解析异常");
        }

        return datas;
    }
}

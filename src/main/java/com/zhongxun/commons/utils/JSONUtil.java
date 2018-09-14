package com.zhongxun.commons.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    private static ObjectMapper mapper = null;

    static {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
    }

    /**
     * 将list转换成ExtStore
     *
     * @param rs List
     * @return String
     */
    public static String ListToExtStore(List rs) {
        StringBuffer jsonBuff = new StringBuffer();
        jsonBuff.append("{\"total\": ").append(rs.size())
                .append(", \"rows\": ").append(serialize(rs)).append("}");
        return jsonBuff.toString();
    }


    /**
     * 将list转换成ExtStore
     *
     * @param rs List
     * @return String
     */
    public static String ListToExtStore(Collection rs) {
        StringBuffer jsonBuff = new StringBuffer();
        jsonBuff.append("{\"total\": ").append(rs.size())
                .append(", \"rows\": ").append(serialize(rs)).append("}");
        return jsonBuff.toString();
    }

    /**
     * 将对象序列化成JSON对象
     *
     * @param object Object
     * @return String
     */
    public static String serialize(Object object) {
        if (object == null || object.toString().equals(""))
            return "";
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 将json字符串写到response中
     *
     * @param response   HttpServletResponse
     * @param encoding   String
     * @param jsonString String
     * @throws IOException
     */
    public static void writeJSONToResponse(HttpServletResponse response,
                                           String encoding,
                                           String jsonString) throws IOException {
        writeJSONToResponse(response, encoding, false, jsonString, false);
    }

    /**
     * 将json字符串写到response中
     *
     * @param response         HttpServletResponse
     * @param encoding         String
     * @param wrapWithComments boolean//是否用注释封装
     * @param jsonString       String
     * @param smd              boolean//rpc调用
     * @throws IOException
     */
    public static void writeJSONToResponse(HttpServletResponse response,
                                           String encoding,
                                           boolean wrapWithComments,
                                           String jsonString,
                                           boolean smd) throws IOException {
        String json = jsonString != null ? jsonString : "";
        if (wrapWithComments) {
            StringBuffer sb = new StringBuffer("/* ");
            sb.append(json);
            sb.append(" */");
            json = sb.toString();
        }
        response.setContentLength(json.getBytes(encoding).length);
        response.setContentType((new StringBuffer()).append(smd ? "application/json-rpc;charset=" : "application/json;charset=")
                .append(encoding).toString());
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public static String errorGridStore(String msg) {
        StringBuffer jsonBuff = new StringBuffer();
        Map data = new HashMap();
        data.put("msg", msg);

        // {\"total\":"+queryInputActualTreeData.size()+",\"rows\":"+PayCommon.serialize(queryInputActualTreeData)+"}
        jsonBuff.append("{\"total\": ").append(1).append(", \"success\": ").append(false).append(", \"rows\": ").append(serialize(data))
                .append("}");
        return jsonBuff.toString();
    }

    /**
     * 将字符串写到response中
     *
     * @param response HttpServletResponse
     * @param encoding String
     * @param str
     * @throws IOException
     */
    public static void writeToResponse(HttpServletResponse response, String encoding, String str) throws IOException {
        response.setContentType((new StringBuffer()).append("text/html;charset=").append(encoding).toString());
        response.getWriter().print(str);
    }

    /**
     * 将JSON转换成Bean
     *
     * @param jsonString String
     * @param beanClass  Class
     * @return Object
     */
    public static <T> T toBean(String jsonString, Class beanClass) {
        try {
            return (T) mapper.readValue(jsonString, beanClass);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
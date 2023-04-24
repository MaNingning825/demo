package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

@Slf4j
@Data
@AllArgsConstructor
public class ResponseUtils {
    private Integer code;
    private String msg;
    private Object data;
    public static void responseJson(ServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
            out.flush();
        } catch (Exception e) {
            log.error("输出异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    public static ResponseUtils response(Integer code, String msg, Object data) {
        return new ResponseUtils(code, msg, data);
    }
    public static ResponseUtils success(Object data) {
        return ResponseUtils.response(200, "成功", data);
    }
    public static ResponseUtils fail(Object data) {
        return ResponseUtils.response(500, "失败", data);
    }
}

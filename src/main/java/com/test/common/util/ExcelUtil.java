package com.test.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ExcelUtil {
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response)
            throws Exception{
        try{
            fileName = URLEncoder.encode(fileName,"utf-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //此处指定了文件类型为xlsx，如果是xls的，请自行替换修改
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e){
            throw new Exception("导出文件失败！");
        }
    }
}

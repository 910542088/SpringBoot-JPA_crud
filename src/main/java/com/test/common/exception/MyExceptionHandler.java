package com.test.common.exception;

import com.alibaba.excel.exception.ExcelDataConvertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.text.ParseException;


@RestControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = ExcelDataConvertException.class)
    public String dateConverterException(ExcelDataConvertException e){
        int row; int column;
        row = e.getRowIndex();
        column = e.getColumnIndex();
        LOGGER.info("第{}行第{}列数据异常，请查看格式是否符合要求！！！！",row+1,column+1);
        return "第" + (row+1) + "行第"+(column+1)+"列出生日期异常，请查看格式是否正确！";
    }

    @ExceptionHandler(value = BindException.class)
    public String handler(BindException e){
        LOGGER.info("========实体校验异常=========");
        LOGGER.error(e.getMessage());
        LOGGER.error(e.getAllErrors().get(0).getDefaultMessage());
        return String.valueOf(e.getAllErrors().get(0).getDefaultMessage());
        //只返回了第一个异常
    }

    @ExceptionHandler(value = IOException.class)
    public String handler(IOException e){
        LOGGER.error(e.getMessage());
        return "文件下载失败，请稍后再试！";
    }
}

package com.test.common.exception;

import com.alibaba.excel.exception.ExcelDataConvertException;
import com.test.common.easyexcel.UserListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = ExcelDataConvertException.class)
    public String dateConverterException(ExcelDataConvertException e){
        int row = 0;
        int column = 0;
        row = e.getRowIndex();
        column = e.getColumnIndex();
        LOGGER.info("第{}行第{}列数据异常，请查看格式是否符合要求！！！！",row+1,column+1);
        return "第" + (row+1) + "行第" + (column+1) + "列数据异常，请查看格式是否正确！";
    }


}

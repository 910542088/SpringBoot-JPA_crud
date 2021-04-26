package com.test.common.easyexcel;

import com.alibaba.excel.write.handler.AbstractRowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class WriteHandler extends AbstractRowWriteHandler {

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder,
                                WriteTableHolder writeTableHolder,
                                Row row, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            Sheet sheet = writeSheetHolder.getSheet();
            Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();
            Comment comment = drawingPatriarch.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short)1, 0, (short)4, 1));
            // 输入批注信息
            comment.setString(new XSSFRichTextString("日期格式为:2008/5/12"));
            // 将批注添加到单元格(第一行第六列)中
            sheet.getRow(0).getCell(5).setCellComment(comment);
        }
    }
}

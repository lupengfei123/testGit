package com.example.demo.exportOrImport;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;


/**
 * @Description: 导出
 * @Author: lpf
 * @Date: 2020/10/11
**/
@Component
public class ExportExcel {

    /**
     * @Description: 导出报表
     * @Author: lpf
     * @Param: columnWidth 列宽度例如 {10,50,25}
     * @Param: columnNumber 列数量 如 3
     * @Param: columnName 每列名字如 {"序号","阶段","数量"};
     * @Param: sheetName 表名
     * @Date: 2020/3/19
     **/
    public  void getFile( String sheetName,
                        HSSFWorkbook wb,
                        HttpServletResponse response,
                        HttpServletRequest request){

        StringBuffer sb = new StringBuffer();
        sb.append(sheetName).append("_").append(System.currentTimeMillis()).append(".xls");
        String filename = sb.toString();
        response.setContentType("application/ms-excel;charset=UTF-8");
        OutputStream out = null;
        try {

            if (request.getHeader("User-Agent").toLowerCase()
                    .indexOf("firefox") > 0) {
                filename = new String(filename.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
            } else if (request.getHeader("User-Agent").toUpperCase()
                    .indexOf("CHROME") > 0) {
                filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");// 谷歌
            }else if (request.getHeader("User-Agent").toUpperCase()
                    .indexOf("MSIE") > 0) {
                filename = URLEncoder.encode(filename, "UTF-8");// IE浏览器
            }
            response.setHeader("Content-Disposition", "attachment;filename=" +filename);
            out = response.getOutputStream();
            wb.write(out);// 将数据写出去
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != out){
                    out.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    /**
     * 导出列表
     */
    public  void createExcel(int columnNumber,
                               int[] columnWidth,
                               String[] columnName,
                               HSSFWorkbook wb,
                               HSSFSheet sheet)throws Exception{
        if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {

            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++){
                for (int j = 0; j <= i; j++){
                    if (i == j) {
                        sheet.setColumnWidth(i, columnWidth[j] * 200); // 单独设置每列的宽
                    }
                }
            }
            HSSFRow row = sheet.createRow( 0);
            HSSFCellStyle style = wb.createCellStyle();
            style(style,"s");
            HSSFFont headerFont =  wb.createFont(); // 创建字体样式
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont.setFontName("黑体"); // 设置字体类型
            headerFont.setFontHeightInPoints((short) 15); // 设置字体大小
            style.setFont(headerFont); // 为标题样式设置字体样式
            for (int i = 0; i < columnNumber; i++)
            {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnName[i]);
                cell.setCellStyle(style);
            }
            HSSFCellStyle style1 = wb.createCellStyle();
            this.style(style1,"s1");

            HSSFCellStyle style2 = wb.createCellStyle();
            this.style(style2,"s2");

            HSSFFont contentFont =  wb.createFont(); // 创建字体样式
            contentFont.setFontName("宋体");
            contentFont.setFontHeightInPoints((short) 15); // 设置字体大小

            style1.setFont(contentFont);
            style2.setFont(contentFont);
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");

        }else{
            System.out.println("列数目长度名称和数组长度要一致");
        }
    }

    public  void style(HSSFCellStyle style, String type){
        style.setWrapText(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        if("s".equals(type) || "s2".equals(type) ){
            style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillBackgroundColor(HSSFColor.AQUA.index);
        }
    }


    public  void main(String[] args) {

    }
}

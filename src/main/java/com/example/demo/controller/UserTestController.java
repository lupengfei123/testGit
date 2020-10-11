package com.example.demo.controller;

import com.example.demo.entity.UserTest;
import com.example.demo.exportOrImport.ExportExcel;
import com.example.demo.service.impl.UserTestServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/userTest")

public class  UserTestController {
    @Resource
    private UserTestServiceImpl userTestServiceImpl;
    @Resource
    private ExportExcel exportExcel;

    @GetMapping("/detail")
    public List<UserTest> detail() throws Exception {
    return  userTestServiceImpl.detail();
    }

    /**
     * @Description: 导出列表
     * @Author: lpf
     * @Date: 2020/3/19
     **/
    @GetMapping(value = "/export")
    public void getFile(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<UserTest> list=userTestServiceImpl.detail();
        int[] columnWidth= {10,50,25};
        int columnNumber =3;
        String sheetName ="用户表";
        String [] columnName ={"序号","阶段","数量"};
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet  = wb.createSheet(sheetName);
        exportExcel.createExcel( columnNumber, columnWidth, columnName, wb,sheet);
        HSSFCellStyle style2 = wb.createCellStyle();
        exportExcel.style(style2,"s2");
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            //创建单元格设值
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(style2);
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellValue(list.get(i).getUserName());
            cell1.setCellStyle(style2);
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellValue(list.get(i).getNickName());
            cell2.setCellStyle(style2);

        }

        exportExcel.getFile(sheetName,wb,response,request);
    }

}

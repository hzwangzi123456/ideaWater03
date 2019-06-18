package com.xs.service;

import com.common.util.FileUtils;
import com.xs.bean.ExcelRow;
import com.xs.bean.Point;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzi
 * @date 19/5/29 下午3:43.
 */
@Slf4j
public class ExcleImpl {

    private final static String newExcelFile = "/Users/ziwang/Desktop/研究生文档/实验/0528/Test12.xls";

    public static void main(String[] args) throws Exception {
        String[] titles = { "序号", "真实值", "克里金", "IDW","TR" };
        List<ExcelRow> excelRows = genRow();
        ExcleImpl.export(titles,excelRows);
    }

    /**
     * 组装实体类List
     * @return
     */
    private static List<ExcelRow> genRow() {
        List<ExcelRow> excelRows = new ArrayList<>();
        List<Point> confirmPoints = WzIdwTest.confirmPoints;
        System.out.println("---------");
        System.out.println(confirmPoints);
        List<String> strings = FileUtils.readText("/Users/ziwang/Desktop/研究生文档/实验/0528/Export_Output.txt");

        //添加上克里金和IDW预测值
        for (String str:strings) {
            ExcelRow row = new ExcelRow();
            if (str.startsWith("#")) {
                continue;
            }
            String[] split = str.split(",");
            row.setIndex(Integer.valueOf(split[0]));
            row.setKriValue(Double.valueOf(split[1]));
            row.setIdwValue(Double.valueOf(split[2]));
            excelRows.add(row);
        }

        //添加上TR预测值
        for (int i = 0;i < confirmPoints.size();i ++) {
            Point point = confirmPoints.get(i);
            ExcelRow excelRow = excelRows.get(i);
            excelRow.setTRvalue(point.getValue());
        }

        //todo 添加真实值

        return excelRows;
    }



    /**
     * 输出excel
     * @param titles
     * @param , ServletOutputStream out
     * @throws Exception
     */
    public static void export(String[] titles,List<ExcelRow> excelRows) throws Exception {
        try {
            File writeName = new File(newExcelFile); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            FileWriter writer = new FileWriter(writeName);
            OutputStream out = new FileOutputStream(writeName);

            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            for (int i = 0; i < excelRows.size(); i++) {
                row = hssfSheet.createRow(i + 1);
                ExcelRow excelRow = excelRows.get(i);

                // 第六步，创建单元格，并设置值
                row.createCell(0).setCellValue(excelRow.getIndex());
                row.createCell(1).setCellValue(excelRow.getTrueValue());
                row.createCell(2).setCellValue(excelRow.getKriValue());
                row.createCell(3).setCellValue(excelRow.getIdwValue());
                row.createCell(4).setCellValue(excelRow.getTRvalue());
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
                log.error("excel写入错误:{}",e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("内部错误:{}",e.getMessage());
        }
    }
}

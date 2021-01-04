package com.yunqing.demohutool;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 合并单元格，主要用于同一列上不用行的元素相同，合并相同元素的行
 * @author kangqing
 * @since 2021/1/4 20:13
 */
public class MergedRegionUtil {

    /**
     * @param sheet sheet页
     * @param col 第几列
     * @param slow 慢指针，合并的开始行数
     * @param fast 快指针，合并的结束行数
     */
    public static void mergedRegion(Sheet sheet, int col, int slow, int fast) {
        //合并分子公司相同项的单元格
        while(sheet.getRow(fast) != null) {
            String topVal = sheet.getRow(slow).getCell(col).getStringCellValue();
            String butVal = sheet.getRow(fast).getCell(col).getStringCellValue();
            if (!topVal.equals(butVal) && slow < fast - 1) {
                sheet.addMergedRegion(new CellRangeAddress(slow, fast - 1, col, col));
                slow = fast;
            }
            fast++;
        }
        if (slow < fast - 1) {
            sheet.addMergedRegion(new CellRangeAddress(slow, fast - 1, col, col));
        }
    }
}

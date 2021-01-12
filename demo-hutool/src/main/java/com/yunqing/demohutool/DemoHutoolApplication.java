package com.yunqing.demohutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RestController
@SpringBootApplication
@Slf4j
public class DemoHutoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHutoolApplication.class, args);
    }

    /**
     * 导出操作日志
     * @param response 响应
     */
    @GetMapping("/export")
    public void exportLogList(HttpServletResponse response) throws IOException {
        // 输出xlsx格式, 默认xls格式
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 重命名当前sheet
        writer.renameSheet("操作日志列表");

        // 设置标题样式
        StyleSet style = writer.getStyleSet();
        CellStyle headCellStyle = style.getHeadCellStyle();
        //水平居中
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置内容字体
        Font font = writer.createFont();
        //加粗
        font.setBold(true);
        //设置标题字体大小
        font.setFontHeightInPoints((short)12);
        headCellStyle.setFont(font);


        SysLogExportDTO bean1 = SysLogExportDTO.builder()
                .id(1L)
                .operation("4")
                .createTime(new Date())
                .logDesc("自定义1")
                .username("kanqging")
                .build();
        SysLogExportDTO bean2 = SysLogExportDTO.builder()
                .id(2L)
                .operation("4")
                .createTime(new Date())
                .logDesc("自定义2")
                .username("kanqging")
                .build();
        SysLogExportDTO bean3 = SysLogExportDTO.builder()
                .id(1L)
                .operation("4")
                .createTime(new Date())
                .logDesc("自定义1")
                .username("aa")
                .build();
        SysLogExportDTO bean4 = SysLogExportDTO.builder()
                .id(2L)
                .operation("4")
                .createTime(new Date())
                .logDesc("自定义2")
                .username("aa")
                .build();
        // 获取操作日志列表
        List<SysLogExportDTO> rows = CollUtil.newArrayList(bean1, bean2, bean3, bean4);

        //自定义标题别名, 需要自定义一个与导出excel标题属性相同的DTO对象SysLogExportDTO，否则会行错乱
        String[] title = new String[]{"id", "operation", "username", "logDesc", "createTime"};
        String[] alias = new String[]{"ID", "操作类型", "操作人", "日志描述", "创建时间"};
        int[] width = {20, 15, 15, 30, 20};
        for (int i = 0; i < title.length; i++) {
            writer.addHeaderAlias(title[i], alias[i]);
        }

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(title.length - 1, "操作日志列表导出");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);

        //合并单元格
        //writer.merge(2, 3, 2, 2, "kangqing", false);
        // 获取当前行
        Sheet sheet = writer.getSheet();
        // 合并同一列上相同值的行
        MergedRegionUtil.mergedRegion(sheet, 2, 2, 3);

        // 切换sheet页
        // writer.setSheet("sheet2");

        // 设置列宽度 (单位为一个字符的宽度，例如传入width为10，表示10个字符的宽度）
        for (int i = 0; i < width.length; i++) {
            writer.setColumnWidth(i, width[i]);
        }

        //导出数据
        try {

            response.setContentType("application/octet-stream;charset=iso-8859-1");
            //中文编码
            String fileName = URLEncoder.encode("操作日志", StandardCharsets.UTF_8);
            //设置Http响应头告诉浏览器下载这个附件
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
            writer.flush(response.getOutputStream(), true);
            writer.close();

        } catch (Exception ex) {
            log.info("导出Excel异常，异常信息");
            throw new IOException("导出Excel异常，异常信息：" + ex.getMessage());
        } finally {
            //清理资源
            try {
                IoUtil.close(response.getOutputStream());
            } catch (IOException e) {
                log.info("清理资源错误");
            }
        }

    }


}

package com.yupi.springbootinit;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;

import java.io.FileOutputStream;
import java.math.BigInteger;

public class WordGenerator {

    public static void main(String[] args) {
        try {
            XWPFDocument document = new XWPFDocument();

            // 标题
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText("_____________建设项目");
            titleRun.setFontSize(18);

            // 设置标题样式，例如加粗
            titleRun.setBold(true);

            // 添加新段落
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.BOTH); // 两端对齐
            XWPFRun run = paragraph.createRun();
            run.setText("这里是段落内容");
            run.setFontSize(12);

            // 设置段落间距
            paragraph.setSpacingBetween(1.5); // 行间距1.5倍

            // 设置段落缩进
            paragraph.setIndentationFirstLine(567); // 首行缩进，单位：twips，1/20点

            // 其他文档内容和格式设置

            // 保存文档
            FileOutputStream out = new FileOutputStream("测试文件.docx");
            document.write(out);
            out.close();
            document.close();

            System.out.println("Word文档生成成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

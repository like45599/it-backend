package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("file_info")
public class FileInfo {
    @TableId(type = IdType.AUTO)
    private Long id; // 文件信息的主键ID

    private String sessionId;   // 会话ID，用于关联会话

    private String formDataId; // 关联的表单数据ID，用于关联到特定的表单提交

    private String originalFileName; // 原始文件名

    private String filePath; // 文件存储在服务器上的路径或URL

    private String fileType; // 文件类型，如'image/png', 'application/pdf'等

    private LocalDateTime createTime; // 记录文件信息创建时间
}
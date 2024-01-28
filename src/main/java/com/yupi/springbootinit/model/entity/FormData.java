package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
@TableName("form_data")
public class FormData {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id; // 主键ID

    private String sessionId; // 会话ID

    private String data; // 存储序列化的表单数据（键值对），包含300多个字段

    private String fileReferences; // 存储序列化的文件引用信息
}
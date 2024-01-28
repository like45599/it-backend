package com.yupi.springbootinit.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("Scenario")
@ApiModel(description = "场景实体")
public class Scenario {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "场景ID")
    private Integer scenarioId;

    @ApiModelProperty(value = "场景名称")
    private String scenarioName;
}
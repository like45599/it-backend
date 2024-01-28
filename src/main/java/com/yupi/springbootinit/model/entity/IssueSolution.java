package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("Issues_Solutions")
@ApiModel(description = "问题及解决方案实体")
public class IssueSolution {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "场景ID")
    @TableField("scenario_id")
    private Integer scenarioId;

    @ApiModelProperty(value = "根本原因")
    @TableField("root_cause")
    private String rootCause;

    @ApiModelProperty(value = "问题描述")
    @TableField("issue_description")
    private String issueDescription;

    @ApiModelProperty(value = "解决方案描述")
    @TableField("solution_description")
    private String solutionDescription;
}
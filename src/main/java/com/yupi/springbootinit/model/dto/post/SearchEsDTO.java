package com.yupi.springbootinit.model.dto.post;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.springbootinit.model.entity.IssueSolution;
import com.yupi.springbootinit.model.entity.Post;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子 ES 包装类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 **/
// todo 取消注释开启 ES（须先配置 ES）
@Document(indexName = "post_v3")
@Data
public class SearchEsDTO {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * id
     */
    @Id
    private Long id;

    private String root_cause;

    private Integer scenario_id;  // 添加场景 ID

    private String issue_description;

    private String solution_description;


    /**
     * 对象转包装类
     *
     * @param
     * @return
     */
    public static SearchEsDTO objToDto(IssueSolution issueSolution) {
        if (issueSolution == null) {
            return null;
        }
        SearchEsDTO searchEsDTO = new SearchEsDTO();
        BeanUtils.copyProperties(issueSolution, searchEsDTO);
        return searchEsDTO;
    }


    /**
     * 包装类转对象
     *
     * @param searchEsDTO
     * @return
     */
    public static IssueSolution dtoToObj(SearchEsDTO searchEsDTO) {
        if (searchEsDTO == null) {
            return null;
        }
        IssueSolution issueSolution = new IssueSolution();
        BeanUtils.copyProperties(searchEsDTO, issueSolution);

        // 如果有从特殊格式（如JSON字符串）转换回实体属性的需求，可以在这里添加相应的逻辑

        return issueSolution;
    }
}

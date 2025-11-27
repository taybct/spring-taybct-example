package io.github.taybct.lab.elk.dto;

import io.github.taybct.tool.core.es.dto.ESQuerySort;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * ES 语句查询数据传输对象，这个不是针对查询地址，其实是查询什么都可以
 *
 * @author xijieyin <br> 2023/1/11 13:54
 */
@Data
@Schema(description = "ES 语句查询数据传输对象")
public class StringQueryDTO implements Serializable {
    /**
     * 查询语句
     */
    @Schema(description = "查询语句", example = "{\"match_all\": {}}")
    private String source;
    /**
     * 如果你引用不到这个类型，就可以考虑写这个类型的名称，后面做根据类型名来获取这个类型
     */
    @Schema(description = "类型", example = "io.github.taybct.module.es.domain.ESApiLog")
    private String clazzName;
    /**
     * 第几页
     */
    @Schema(description = "第几页，从 0 开始", example = "0")
    private Integer number = 0;
    /**
     * 页面大小
     */
    @Schema(description = "页面大小", example = "10")
    private Integer size = 10;
    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private LinkedHashSet<ESQuerySort> fieldSort;
}

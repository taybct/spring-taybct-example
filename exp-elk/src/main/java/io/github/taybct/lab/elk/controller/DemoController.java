package io.github.taybct.lab.elk.controller;

import io.github.taybct.lab.elk.dto.StringQueryDTO;
import io.github.taybct.tool.core.annotation.ApiVersion;
import io.github.taybct.tool.core.es.util.ESQueryUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ddd
 *
 * @author xijieyin <br> 2023/2/28 下午10:14
 */
@RestController
@Tag(name = "demo")
@AutoConfiguration
@RequestMapping("{version}/es")
@RequiredArgsConstructor
@ApiVersion
public class DemoController {

    final ElasticsearchOperations elasticsearchOperations;

    @SneakyThrows
    @Operation(summary = "使用匹配语句查询地址库，这个是直接指定要操作的实体类类型去查询")
    @PostMapping("query")
    public Page<?> search(@RequestBody StringQueryDTO stringQueryDTO) {
        Class<?> clazz = Class.forName(stringQueryDTO.getClazzName());
        return ESQueryUtil.stringQueryPage(
                elasticsearchOperations
                , stringQueryDTO.getSource()
                , stringQueryDTO.getNumber()
                , stringQueryDTO.getSize()
                , stringQueryDTO.getFieldSort()
                , clazz);
    }
}

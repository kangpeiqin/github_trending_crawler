package com.example.crawler.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kpq
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "仓库贡献者信息")
public class Contributor {
    @ApiModelProperty("贡献者头像")
    private String avatar;
    @ApiModelProperty("账户链接")
    private String accountLink;
}

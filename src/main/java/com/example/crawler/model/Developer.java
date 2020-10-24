package com.example.crawler.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kpq
 * @since 1.0.0
 */
@Data
@ApiModel(value = "Developer", description = "最火开发者")
public class Developer {
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("账户链接")
    private String accountLink;
    @ApiModelProperty("最火仓库名称")
    private String popularRepoName;
    @ApiModelProperty("最火仓库链接")
    private String popularRepoUrl;
    @ApiModelProperty("最火仓库简介")
    private String popularRepoDescription;
}

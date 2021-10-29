package com.example.crawler.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * github trending repository model
 *
 * @author kpq
 * @since 1.0.0
 */
@Data
@ApiModel(value = "GithubRepository", description = "github仓库信息")
public class GithubRepository {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("仓库链接")
    private String url;
    @ApiModelProperty("编程语言")
    private String programmingLanguage;
    @ApiModelProperty("仓库简介")
    private String description;
    @ApiModelProperty("获得star个数")
    private String stars;
    @ApiModelProperty("被forks的次数")
    private String forks;
    @ApiModelProperty("贡献者列表")
    private List<Contributor> contributors;
}

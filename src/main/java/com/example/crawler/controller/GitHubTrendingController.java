package com.example.crawler.controller;

import com.example.crawler.service.GitHubTrendingService;
import com.example.crawler.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
@Api(tags = "获取github trending page api")
public class GitHubTrendingController {
    @Resource
    GitHubTrendingService gitHubTrendingService;

    @GetMapping(value = {"/trending/{language}", "/trending"})
    @ApiOperation(value = "获取github trending page api", notes = "获取github trending page api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "查询语言", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "since", value = "查询日期", dataType = "String", paramType = "query")
    })
    public ResultVo getGitHubTrending(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) {
        return ResultVo.success(gitHubTrendingService.getGitHubTrending(language, since));
    }
}

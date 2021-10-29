package com.example.crawler.controller;

import com.example.crawler.model.Developer;
import com.example.crawler.service.GitHubTrendingService;
import com.example.crawler.service.HotDeveloperService;
import com.example.crawler.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
@Api(tags = "获取github trending page api")
@Slf4j
public class GitHubTrendingController {

    @Resource
    GitHubTrendingService gitHubTrendingService;

    @Resource
    private HotDeveloperService hotDeveloperService;

    @GetMapping(value = {"/trending/{language}", "/trending"})
    @ApiOperation(value = "获取github trending page api", notes = "获取github trending page api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "查询语言", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "since", value = "查询日期", dataType = "String", paramType = "query")
    })
    @CrossOrigin("*")
    public ResultVO getGitHubTrending(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) {
        return ResultVO.success(gitHubTrendingService.getGitHubTrending(language, since));
    }

    @GetMapping({"/hot/develops/{language}", "/hot/develops"})
    @CrossOrigin({"*"})
    public ResultVO getHotDevelops(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) {
        List<Developer> developers = hotDeveloperService.getDevelopers(language, since);
        return ResultVO.success(developers);
    }
}

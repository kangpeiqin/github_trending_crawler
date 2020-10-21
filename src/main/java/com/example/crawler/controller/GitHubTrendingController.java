package com.example.crawler.controller;

import com.example.crawler.service.GitHubTrendingService;
import com.example.crawler.vo.ResultVo;
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
public class GitHubTrendingController {
    @Resource
    GitHubTrendingService gitHubTrendingService;

    @GetMapping(value = {"/trending/{language}", "/trending"})
    public ResultVo getGitHubTrending(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) throws Exception {
        if (language == null) {
            language = "";
        }
        return ResultVo.success(gitHubTrendingService.getGitHubTrending());
    }
}

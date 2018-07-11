package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
    }

    /**
     * 根据标签查询最新回答问题列表
     *
     * @param label
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/all/{label}/{page}/{size}", method = RequestMethod.POST)
    public Result findNewProListByLabelId(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pageList = problemService.findNewProListByLabelId(label, page, size);
        PageResult<Problem> pageResult = new PageResult(pageList.getTotalElements(), pageList.getContent());
        return new Result(true, StatusCode.OK, "查询成功!", pageResult);
    }

    /**
     * 根据标签查询热门问题
     *
     * @param label
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/newlist/{label}/{page}/{size}", method = RequestMethod.GET)
    public Result hotlist(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        Page<Problem> hotList = problemService.findHotListByLabelId(label, page, size);
        PageResult<Problem> pageResult = new PageResult<>(hotList.getTotalElements(), hotList.getContent());
        return new Result(true, StatusCode.OK, "查询成功!", pageResult);
    }

    /**
     * 根据标签查询等待回答的问题
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/waitlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result waitlist(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemList = problemService.findWaitListByLabelId(labelId, page, size);
        PageResult<Problem> pageResult = new PageResult<>(problemList.getTotalElements(), problemList.getContent());
        return new Result(true, StatusCode.OK, "查询成功!", pageResult);
    }

    /**
     * 增加
     *
     * @param problem
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}

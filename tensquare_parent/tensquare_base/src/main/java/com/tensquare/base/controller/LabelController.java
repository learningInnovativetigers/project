package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 标签控制器类
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询所有成功!",labelService.findAll());
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String id){
        return new Result(true,StatusCode.OK,"查询一个成功!",labelService.findById(id));
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        List<Label> labelList = labelService.findSearch(searchMap);
        return new Result(true,StatusCode.OK,"查询成功!",labelList);
    }

    /**
     * 分页条件查询
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Label> pageList = labelService.findSearch(searchMap, page, size);
        return new Result(true,StatusCode.OK,"查询成功!",new PageResult<Label>(pageList.getTotalElements(),pageList.getContent()));
    }
    /**
     * 添加
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功!");
    }

    /**
     * 修改
     * @param id
     * @param label
     * @return
     */
    @RequestMapping(value = "/{lableId}",method = RequestMethod.PUT)
    public Result updateById(@PathVariable("lableId") String id,@RequestBody Label label){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功!");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("labelId") String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功!");
    }
}

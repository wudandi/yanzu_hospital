package com.yanzu.yygh.cmn.controller;

import com.yanzu.yygh.cmn.service.DictService;
import com.yanzu.yygh.common.result.Result;
import com.yanzu.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther 吴彦祖
 * @date 2021/6/6
 */
@Api(tags = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation("根据上级id查询子节点列表")
    @GetMapping("/fiandChidrenData/{id}")
    public Result fiandChidrenData(@PathVariable Long id){
        List<Dict> dictList = dictService.findChidrenDataById(id);
        return Result.ok(dictList);
    }

    @ApiOperation("导出信息")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response){
        dictService.export(response);
    }

    @ApiOperation("导入信息")
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        dictService.importData(file);
        return Result.ok();
    }

    //根据dictcode和value查询，获取数据字典名称
    @GetMapping("/getName/{dictCode}/{value}")
    public Result getName(@PathVariable String dictCode,@PathVariable String value){
        return Result.ok(dictService.getDictName(dictCode,value));
    }
    //根据value查询
    @GetMapping("/getName/{value}")
    public Result getName(@PathVariable String value){
        return Result.ok(dictService.getDictName("",value));
    }
}

package com.yanzu.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanzu.yygh.common.result.Result;
import com.yanzu.yygh.common.util.MD5;
import com.yanzu.yygh.hosp.service.HospitalSetService;
import com.yanzu.yygh.model.hosp.HospitalSet;
import com.yanzu.yygh.vo.HospSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@Api(tags = "医院管理设置")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    //查询医院设置的所有数据
    @ApiOperation("获取医院所有设置信息")
    @GetMapping("/findAll")
    public Result findAllHospital(){
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }
    //逻辑删除医院设置（不是真的删除，只是查不到了，实体类有逻辑删除注解）
    @ApiOperation("删除医院设置")
    @DeleteMapping("/delete/{id}")
    public Result deleteHospitalSet(@PathVariable("id") Integer id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag) {
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //使用vo类封装条件值
    @ApiOperation("条件查询带分页")
    @PostMapping("/findPage/{current}/{limit}")
    public Result findPageHospitalSet(@PathVariable("current") long current, @PathVariable("limit") long limit, @RequestBody(required = false) HospSetQueryVo hospSetQueryVo){
        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(hospSetQueryVo.getHoscode())){
            wrapper.like("hosname",hospSetQueryVo.getHospname());
        }
        if (!StringUtils.isEmpty(hospSetQueryVo.getHoscode())){
            wrapper.eq("hoscode",hospSetQueryVo.getHoscode());
        }
        Page<HospitalSet> pageHospSet = hospitalSetService.page(page, wrapper);
        return Result.ok(pageHospSet);
    }

    @ApiOperation("添加医院设置信息")
    @PostMapping("/save")
    public Result saveHospSet(@RequestBody HospitalSet hospitalSet){
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        return hospitalSetService.save(hospitalSet) ? Result.ok() : Result.fail();
    }

    @ApiOperation("根据id获取医院设置")
    @GetMapping("/getHospSet/{id}")
    public Result saveHospSetById(@PathVariable("id") Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    @ApiOperation("修改医院设置")
    @PutMapping("/updateHospSet")
    public Result updateHospSet(@RequestBody HospitalSet hospitalSet){
        boolean updateById = hospitalSetService.updateById(hospitalSet);
        return updateById ? Result.ok() : Result.fail();
    }

    @ApiOperation("批量删除医院设置")
    @DeleteMapping("/batchRemove")
    public Result deleteHospSet(@RequestBody List<Long> idList){
        boolean removeByIds = hospitalSetService.removeByIds(idList);
        return removeByIds ? Result.ok() : Result.fail();
    }

    @ApiOperation("医院设置锁定和解锁" +
            "" +
            "")
    @PutMapping("/lockHospSet/{id}/{status}")
    public Result lockHospSet(@PathVariable("id") Long id,@PathVariable("status") Integer status){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        boolean updateById = hospitalSetService.updateById(hospitalSet);
        return updateById ? Result.ok() : Result.fail();
    }

    @ApiOperation("发送签名密钥")
    @PutMapping("/sendKey/{id}")
    public Result sendKey(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //发送短信TODO
        return Result.ok();
    }
}

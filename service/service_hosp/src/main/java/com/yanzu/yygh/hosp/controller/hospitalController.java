package com.yanzu.yygh.hosp.controller;

import com.yanzu.yygh.common.result.Result;
import com.yanzu.yygh.hosp.service.HospitalService;
import com.yanzu.yygh.model.hosp.Hospital;
import com.yanzu.yygh.vo.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther 吴彦祖
 * @date 2021/7/27
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
public class hospitalController {
    @Autowired
    private HospitalService hospitalService;
    //多条件分页查询
    @GetMapping("/list/{page}/{limit}")
    public Result listHosp(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, HospitalQueryVo hospitalQueryVo){
        Page<Hospital> pageModel = hospitalService.selectHosPage(page,limit,hospitalQueryVo);
        return Result.ok(pageModel);
    }
}

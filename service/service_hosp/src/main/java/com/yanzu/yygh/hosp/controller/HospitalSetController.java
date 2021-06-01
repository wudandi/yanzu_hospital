package com.yanzu.yygh.hosp.controller;

import com.yanzu.yygh.hosp.service.HospitalSetService;
import com.yanzu.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    //查询医院设置的所有数据
    @GetMapping("/fiandAll")
    public List<HospitalSet> findAllHospital(){
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }
}

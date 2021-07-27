package com.yanzu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yanzu.yygh.hosp.repository.HospitalRepository;
import com.yanzu.yygh.hosp.service.HospitalService;
import com.yanzu.yygh.model.hosp.Hospital;
import com.yanzu.yygh.vo.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/19
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> objectMap) {
        //把Map集合参数转换成Hospital对象
        String jsonString = JSONObject.toJSONString(objectMap);
        Hospital hospital = JSONObject.parseObject(jsonString, Hospital.class);
        //判断是否MongoDB数据库中是否存在数据，存在就修改，不存在就保存
        String hoscode = hospital.getHoscode();
        Hospital byHospital = hospitalRepository.getHospitalByHoscode(hoscode);
        if (byHospital != null){
            hospital.setStatus(byHospital.getStatus());
            hospital.setUpdateTime(new Date());
            hospital.setCreateTime(byHospital.getCreateTime());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else{
            hospital.setStatus(0);
            hospital.setUpdateTime(new Date());
            hospital.setCreateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }

    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    @Override
    public Page<Hospital> selectHosPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        Pageable pageable = PageRequest.of(page,limit);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo,hospital);
        Example<Hospital> example = Example.of(hospital, exampleMatcher);
        Page<Hospital> all = hospitalRepository.findAll(example, pageable);
        return all;
    }

}

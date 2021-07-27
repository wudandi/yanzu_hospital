package com.yanzu.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanzu.yygh.hosp.dao.HospitalSetMapper;
import com.yanzu.yygh.hosp.service.HospitalSetService;
import com.yanzu.yygh.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {


    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
}

package com.yanzu.yygh.hosp.service;

import com.yanzu.yygh.model.hosp.Hospital;
import com.yanzu.yygh.vo.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/19
 */
public interface HospitalService {
    void save(Map<String, Object> objectMap);

    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectHosPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);
}

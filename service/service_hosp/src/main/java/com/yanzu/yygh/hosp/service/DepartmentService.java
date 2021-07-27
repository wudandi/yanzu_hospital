package com.yanzu.yygh.hosp.service;

import com.yanzu.yygh.model.hosp.Department;
import com.yanzu.yygh.vo.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/23
 */
public interface DepartmentService {
    void save(Map<String, Object> objectMap);

    Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);
}

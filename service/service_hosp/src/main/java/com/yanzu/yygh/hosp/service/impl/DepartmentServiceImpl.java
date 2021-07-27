package com.yanzu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yanzu.yygh.hosp.repository.DepartmentRepository;
import com.yanzu.yygh.hosp.service.DepartmentService;
import com.yanzu.yygh.model.hosp.Department;
import com.yanzu.yygh.vo.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/23
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> objectMap) {
        String jsonString = JSONObject.toJSONString(objectMap);
        Department department = JSONObject.parseObject(jsonString,Department.class);
        Department ByDepartment = departmentRepository.findDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());
        if (ByDepartment != null){
            department.setUpdateTime(new Date());
            department.setIsDeleted(ByDepartment.getIsDeleted());
            departmentRepository.save(department);
        }else{
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);
        //创建Pageable对象，设置当前页和每页记录数(第0页开始)
        Pageable pageable= PageRequest.of(page-1, limit);
        //创建example对象
        ExampleMatcher matching = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Example<Department> example = Example.of(department,matching);
        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.findDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department != null){
            departmentRepository.deleteById(department.getId());
        }
    }
}

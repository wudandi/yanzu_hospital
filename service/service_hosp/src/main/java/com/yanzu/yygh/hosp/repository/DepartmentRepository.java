package com.yanzu.yygh.hosp.repository;

import com.yanzu.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther 吴彦祖
 * @date 2021/6/23
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department findDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}

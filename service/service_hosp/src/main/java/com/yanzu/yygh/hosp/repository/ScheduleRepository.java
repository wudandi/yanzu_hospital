package com.yanzu.yygh.hosp.repository;

import com.yanzu.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther 吴彦祖
 * @date 2021/6/24
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    Schedule findScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}

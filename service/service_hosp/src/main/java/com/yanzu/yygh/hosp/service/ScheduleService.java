package com.yanzu.yygh.hosp.service;

import com.yanzu.yygh.model.hosp.Schedule;
import com.yanzu.yygh.vo.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/24
 */
public interface ScheduleService {
    void save(Map<String, Object> objectMap);

    Page<Schedule> findPageSchedule(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);
}

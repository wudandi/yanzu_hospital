package com.yanzu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yanzu.yygh.hosp.repository.ScheduleRepository;
import com.yanzu.yygh.hosp.service.ScheduleService;
import com.yanzu.yygh.model.hosp.Schedule;
import com.yanzu.yygh.vo.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/24
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(Map<String, Object> objectMap) {
        String jsonString = JSONObject.toJSONString(objectMap);
        Schedule schedule = JSONObject.parseObject(jsonString, Schedule.class);
        Schedule Byschedule = scheduleRepository.findScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(),schedule.getHosScheduleId());
        if (Byschedule != null){
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(Byschedule.getIsDeleted());
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        }else{
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        }
    }

    @Override
    public Page<Schedule> findPageSchedule(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo,schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);
        //创建Pageable对象，设置当前页和每页记录数(第0页开始)
        Pageable pageable= PageRequest.of(page-1, limit);
        //创建example对象
        ExampleMatcher matching = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Example<Schedule> example = Example.of(schedule,matching);
        Page<Schedule> all = scheduleRepository.findAll(example, pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Schedule schedule = scheduleRepository.findScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (schedule != null){
            scheduleRepository.deleteById(schedule.getId());
        }
    }
}

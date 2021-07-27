package com.yanzu.yygh.hosp.controller.api;

import com.yanzu.yygh.common.exception.HospitalException;
import com.yanzu.yygh.common.helper.HttpRequestHelper;
import com.yanzu.yygh.common.result.Result;
import com.yanzu.yygh.common.result.ResultCodeEnum;
import com.yanzu.yygh.common.util.MD5;
import com.yanzu.yygh.hosp.service.DepartmentService;
import com.yanzu.yygh.hosp.service.HospitalSetService;
import com.yanzu.yygh.hosp.service.ScheduleService;
import com.yanzu.yygh.hosp.service.impl.HospitalServiceImpl;
import com.yanzu.yygh.model.hosp.Department;
import com.yanzu.yygh.model.hosp.Hospital;
import com.yanzu.yygh.model.hosp.Schedule;
import com.yanzu.yygh.vo.DepartmentQueryVo;
import com.yanzu.yygh.vo.ScheduleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther 吴彦祖
 * @date 2021/6/19
 */
@RestController
@RequestMapping("/api/hosp")
@CrossOrigin
public class ApiController {
    @Autowired
    private HospitalServiceImpl hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/saveHospital")
    public Result saveHosp(HttpServletRequest request){
        //获取传递过来的信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        //根据医院系统传递过来的签名
        String hospSign = (String)objectMap.get("sign");
        //根据传递过来的医院编码处查询数据库签名并MD5加密
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        String logoData = (String)objectMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        objectMap.put("logoData",logoData);
        hospitalService.save(objectMap);
        return Result.ok();
    }
    @PostMapping("/hospital/show")
    public Result getHospital(HttpServletRequest request){
        //获取传递过来的信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hoscode = (String)objectMap.get("hoscode");
        String hospSign = (String)objectMap.get("sign");
        //根据传递过来的医院编码处查询数据库签名并MD5加密
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }
    @PostMapping("/saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hospSign = (String)objectMap.get("sign");
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        departmentService.save(objectMap);
        return Result.ok();
    }
    @PostMapping("/department/list")
    public Result getDepartment(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hospSign = (String)objectMap.get("sign");
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        Integer page = StringUtils.isEmpty(objectMap.get("page")) ? 1 : Integer.parseInt((String)objectMap.get("page"));
        Integer limit = StringUtils.isEmpty(objectMap.get("limit")) ? 1 : Integer.parseInt((String)objectMap.get("limit"));
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> pageModel = departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.ok(pageModel);
    }
    @PostMapping("/department/remove")
    public Result removeDepartment(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hospSign = (String)objectMap.get("sign");
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        String depcode = (String)objectMap.get("depcode");
        departmentService.remove(hoscode,depcode);
        return Result.ok();
    }
    @PostMapping("/saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hospSign = (String)objectMap.get("sign");
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        scheduleService.save(objectMap);
        return Result.ok();
    }
    @PostMapping("/schedule/list")
    public Result findSchedule(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hospSign = (String)objectMap.get("sign");
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        Integer page =StringUtils.isEmpty(objectMap.get("page"))?1:Integer.parseInt((String)objectMap.get("page"));
        Integer limit =StringUtils.isEmpty(objectMap.get("limit"))?1:Integer.parseInt((String)objectMap.get("limit"));
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode((String) objectMap.get("depcode"));
        Page<Schedule> schedulePage = scheduleService.findPageSchedule(page,limit,scheduleQueryVo);
        return Result.ok(schedulePage);
    }
    @PostMapping("/schedule/remove")
    public Result removeSchedule(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = HttpRequestHelper.switchMap(parameterMap);
        String hospSign = (String)objectMap.get("sign");
        String hoscode = (String)objectMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyByMD5 = MD5.encrypt(signKey);
        if (!signKeyByMD5.equals(hospSign)){
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
        String hosScheduleId = (String)objectMap.get("hosScheduleId");
        scheduleService.remove(hoscode,hosScheduleId);
        return Result.ok();
    }
}

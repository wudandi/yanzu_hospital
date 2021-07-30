package com.yanzu.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanzu.yygh.common.result.Result;
import com.yanzu.yygh.model.cmn.Dict;
import com.yanzu.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
public interface DictService extends IService<Dict> {
    List<Dict> findChidrenDataById(Long id);

    void export(HttpServletResponse response);

    void importData(MultipartFile file);

    String getDictName(String dictCode, String value);

    List<Dict> findByDictCode(String dictCode);
}



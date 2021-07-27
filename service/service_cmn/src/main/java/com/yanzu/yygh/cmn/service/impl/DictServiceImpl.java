package com.yanzu.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanzu.yygh.cmn.listeners.DictListener;
import com.yanzu.yygh.cmn.mapper.DictMapper;
import com.yanzu.yygh.cmn.service.DictService;
import com.yanzu.yygh.common.result.Result;
import com.yanzu.yygh.model.cmn.Dict;
import com.yanzu.yygh.vo.DictVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 吴彦祖
 * @date 2021/6/6
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")//从数据库缓存数据
    public List<Dict> findChidrenDataById(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        for (Dict dict:dictList) {
            boolean isChidren = this.hasChidren(dict.getId());
            dict.setHasChildren(isChidren);
        }
        return dictList;
    }

    @Override
    public void export(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        String fileName = "dictData";
        response.setHeader("Content-disposition","attachment;fileName="+ fileName +".xlsx");
        response.setCharacterEncoding("utf-8");

        List<Dict> dicts = baseMapper.selectList(null);
        List<DictVo> dictList = new ArrayList<>();
        for (Dict dict:dicts) {
            DictVo dictVo = new DictVo();
//            dictVo.setId(dict.getId());
//            dictVo.setDictCode(dict.getDictCode());
//            dictVo.setName(dict.getName());
//            dictVo.setParentId(dict.getParentId());
//            dictVo.setValue(dict.getValue());
            BeanUtils.copyProperties(dict,dictVo);
            dictList.add(dictVo);
        }

        try {
            EasyExcel.write(response.getOutputStream(), DictVo.class).sheet("dict").doWrite(dictList);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    @CacheEvict(value = "dict",allEntries = true)//清空缓存
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDictName(String dictCode, String value) {
        if (StringUtils.isEmpty(dictCode)){
            QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
            dictQueryWrapper.eq("value",value);
            Dict dict = baseMapper.selectOne(dictQueryWrapper);
            return dict.getName();
        }else {
            QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
            dictQueryWrapper.eq("dict_code",dictCode);
            Dict dict = baseMapper.selectOne(dictQueryWrapper);
            Long id = dict.getId();
            Dict dictFind = baseMapper.selectOne(new QueryWrapper<Dict>().eq("parent_id", id).eq("value", value));
            return dictFind.getName();
        }

    }

    //判断有无子树
    private boolean hasChidren(Long id){
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(dictQueryWrapper);
        return count>0;
    }
}


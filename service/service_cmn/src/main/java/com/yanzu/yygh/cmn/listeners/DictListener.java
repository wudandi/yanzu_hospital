package com.yanzu.yygh.cmn.listeners;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yanzu.yygh.cmn.mapper.DictMapper;
import com.yanzu.yygh.model.cmn.Dict;
import com.yanzu.yygh.vo.DictVo;
import org.springframework.beans.BeanUtils;

/**
 * @auther 吴彦祖
 * @date 2021/6/7
 */
public class DictListener extends AnalysisEventListener<DictVo> {
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    //读取
    @Override
    public void invoke(DictVo dictVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictVo,dict);
        dict.setIsDeleted(0);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

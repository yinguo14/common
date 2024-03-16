package com.military.cloud.common.database.util;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.military.cloud.common.database.dto.PageDTO;
import com.military.cloud.common.database.vo.PageVO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author FrozenWatermelon
 * @date 2020/9/8
 */
public class PageUtil {

    /**
     * 使用pagehelper进行分页，该分页只能一对一
     */
    public static <T> PageVO<T> doPage(PageDTO pageDTO, ISelect select) {

        PageSerializable<T> simplePageInfo = PageHelper.startPage(pageDTO).doSelectPageSerializable(select);

        PageVO<T> pageVO = new PageVO<>();
        pageVO.setList(simplePageInfo.getList());
        pageVO.setTotal(simplePageInfo.getTotal());
        pageVO.setPages(getPages(simplePageInfo.getTotal(), pageDTO.getPageSize()));
        return pageVO;
    }

    /**
     * 分页，带map类映射函数
     * @param pageDTO
     * @param select
     * @param mapper
     * @param <E>
     * @return
     */
    public static <E, R> PageVO<R> doPage(PageDTO pageDTO, ISelect select, Function<? super E, ? extends R> mapper) {

        PageSerializable<E> simplePageInfo = PageHelper.startPage(pageDTO).doSelectPageSerializable(select);

        PageVO<R> pageVO = new PageVO<>();
        pageVO.setList(Optional.ofNullable(simplePageInfo.getList())
                .orElse(new ArrayList<>())
                .stream()
                .map(mapper)
                .collect(Collectors.toList()));
        pageVO.setTotal(simplePageInfo.getTotal());
        pageVO.setPages(getPages(simplePageInfo.getTotal(), pageDTO.getPageSize()));
        return pageVO;
    }

    public static <T> PageVO<T> emptyTransfer(PageVO source, Class<T> clazz) {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setList(null);
        pageVO.setTotal(source.getTotal());
        pageVO.setPages(source.getPages());
        return pageVO;
    }



    public static Integer getPages(long total, Integer pageSize) {

        if (total == -1) {
            return 1;
        }
        if (pageSize > 0) {
            return  (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        }
        return  0;
    }
}

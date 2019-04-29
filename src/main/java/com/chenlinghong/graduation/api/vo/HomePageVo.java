package com.chenlinghong.graduation.api.vo;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 主页VO
 * @Author chenlinghong
 * @Date 2019/4/29 9:15
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePageVo implements Serializable {

    private static final long serialVersionUID = 47834696641007060L;

    /**
     * 所有目录数据,包括一级二级
     */
    PageDto<GoodsCatalogOne> catalogPageDto;

    /**
     * 商品
     */
    PageDto<Goods> goodsPageDto;


}

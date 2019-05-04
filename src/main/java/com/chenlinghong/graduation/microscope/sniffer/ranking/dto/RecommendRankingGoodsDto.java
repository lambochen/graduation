package com.chenlinghong.graduation.microscope.sniffer.ranking.dto;

import com.chenlinghong.graduation.repository.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description goods ranking dto
 * @Author chenlinghong
 * @Date 2019/5/3 11:21
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRankingGoodsDto implements Serializable {

    private static final long serialVersionUID = -1858156084446375285L;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品基本信息
     */
    private Goods goods;

    /**
     * ranking
     */
    private Integer ranking;
}

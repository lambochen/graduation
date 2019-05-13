package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户-标签
 * @Author chenlinghong
 * @Date 2019/4/23 22:31
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTag extends BaseDomain {

    private static final long serialVersionUID = 4470837128304734592L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品一级目录ID
     */
    private Long goodsCatalogOneId;

    /**
     * 商品二级目录ID
     */
    private Long goodsCatalogTwoId;

    public UserTag(long userId, long goodsCatalogOneId, long goodsCatalogTwoId){
        this.userId = userId;
        this.goodsCatalogOneId = goodsCatalogOneId;
        this.goodsCatalogTwoId = goodsCatalogTwoId;
    }

}

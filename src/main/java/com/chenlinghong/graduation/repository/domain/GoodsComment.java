package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品评论表
 * @Author chenlinghong
 * @Date 2019/4/2 17:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsComment extends BaseDomain {

    private static final long serialVersionUID = 3844742750378239686L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 第一张图片,每个评论只支持最多三张图片
     */
    private String imgOne;

    /**
     * 第二
     */
    private String imgTwo;

    /**
     * 第三
     */
    private String imgThree;

    public GoodsComment(long goodsId, long userId, String content) {
        this.goodsId = goodsId;
        this.userId = userId;
        this.content = content;
    }
}

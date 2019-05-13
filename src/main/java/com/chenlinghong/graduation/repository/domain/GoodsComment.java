package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "商品ID不能为空")
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

    /**
     * 评分，1-5对应好中差
     */
    @NotNull(message = "分数不能为空")
    @Range(min = 1, max = 5, message = "分数必须为1-5之间")
    private Integer score;

    public GoodsComment(long goodsId, long userId, String content, int score) {
        this.goodsId = goodsId;
        this.userId = userId;
        this.content = content;
        this.score = score;
    }
}

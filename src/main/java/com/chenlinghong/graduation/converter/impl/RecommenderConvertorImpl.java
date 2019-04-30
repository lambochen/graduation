package com.chenlinghong.graduation.converter.impl;

import com.chenlinghong.graduation.converter.RecommenderConvertor;
import com.chenlinghong.graduation.repository.domain.Goods;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 推荐系统转换类实现类
 * @Author chenlinghong
 * @Date 2019/4/30 23:10
 * @Version V1.0
 */
@Slf4j
@Service
public class RecommenderConvertorImpl implements RecommenderConvertor {

    @Override
    public List<Goods> convert(List<RecommendedItem> itemList) {
        return null;
    }
}

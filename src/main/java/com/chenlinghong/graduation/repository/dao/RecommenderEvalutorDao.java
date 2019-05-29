package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.RecommenderEvalutor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 推荐评估DAO
 * @Author chenlinghong
 * @Date 2019/5/30 2:47
 * @Version V1.0
 */
public interface RecommenderEvalutorDao extends IBaseDao<RecommenderEvalutor> {

    /**
     * 根据推荐类型分页获取
     *
     * @param type
     * @param offset
     * @param rows
     * @return
     */
    List<RecommenderEvalutor> listByType(@Param("type") int type, @Param("offset") long offset, @Param("rows") long rows);

    long countByType(int type);

}

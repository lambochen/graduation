package com.chenlinghong.graduation.util;

import com.alibaba.fastjson.JSON;
import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.common.redis.RedisUtil;
import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.constant.RedisConstant;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.repository.domain.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description 该项目专用redis 工具类
 * @Author chenlinghong
 * @Date 2019/4/6 17:35
 * @Version V1.0
 */
@Slf4j
@Service
public class MyRedisUtil extends RedisUtil {

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    /**
     * 用户视图对象写入redis。
     * userVo对象采用hash存储，其属性对象采用string存储
     * <p>
     * userVo : {
     * user : {},
     * // TODO 待完善
     * }
     *
     * @param userVo 用户视图对象
     * @return 该对象在redis存储中的key，若为null则写入失败
     */
    @Async(value = AsyncNameConstant.REDIS)
    public String put(UserVo userVo) {
        log.info("MyRedisUtil#put(UserVo): beginning. userVo={}", userVo);
        if (userVo == null || userVo.getUserInfo() == null) {
            log.error("MyRedisUtil#put(userVo): param is null. userVo={}", userVo);
            return null;
        }
        // 获取redis key
        String redisKey = redisKeyUtil.generateKey(userVo);
        /**
         * 构造field:value map
         */
        Map<String, String> hashData = Maps.newLinkedHashMap();
        // 用户基本信息
        String user = JSON.toJSONString(userVo.getUserInfo());
        hashData.put(RedisConstant.USER, user);
        /**
         * TODO 其它属性
         */
        // 写入redis
        hPutAll(redisKey, hashData);
        // 设置ttl
        expire(redisKey, RedisConstant.DATA_TTL, TimeUnit.MILLISECONDS);
        log.info("MyRedisUtil#put(UserVo): ended. userVo={}", userVo);
        return redisKey;
    }


    /**
     * 短信验证码写入redis
     *
     * @param telephone 电话号码
     * @param smsCode   短信验证码
     * @return redis key
     */
    @Async(value = AsyncNameConstant.REDIS)
    public String putSmsCode(String telephone, String smsCode) {
        log.info("MyRedisUtil#putSmsCode: beginning. telephone={}, smsCode={}", telephone, smsCode);
        // 生成redis key
        String redisKey = redisKeyUtil.generateKeyForSms(telephone);
        // 写入redis
        set(redisKey, smsCode);
        // 设置key 存活时间   10 * 60s
        expire(redisKey, RedisConstant.SMS_TTL, TimeUnit.MILLISECONDS);
        log.info("MyRedisUtil#putSmsCode: ended. telephone={}, smsCode={}", telephone, smsCode);
        return redisKey;
    }


    /**
     * 商品推荐排名 写入Redis
     *
     * @param rankingGoodsList 推荐商品排名列表
     * @return redis key
     */
    @Async(value = AsyncNameConstant.REDIS)
    public String putRecommendRankingGoods(List<RecommendRankingGoods> rankingGoodsList) {
        String key = redisKeyUtil.generateKeyForRecommendRankingGoods();
        /**
         * 构造set
         */
        Set<ZSetOperations.TypedTuple<String>> goodsSet = Sets.newHashSet();
        for (RecommendRankingGoods item : rankingGoodsList) {
            if (item == null || item.getGoodsId() == null) {
                // value is not null
                continue;
            }
            double ranking = 0;
            if (item.getRanking() != null && item.getRanking() > 0) {
                ranking = item.getRanking();
            }
            ZSetOperations.TypedTuple<String> tuple =
                    new DefaultTypedTuple<>("" + item.getGoodsId(), ranking);
            goodsSet.add(tuple);
        }
        /**
         * 批量写入redis
         */
        long putResult = zAdd(key, goodsSet);
        /**
         * TODO 校验返回结果
         */
        return key;
    }

    /**
     * 写入ranking
     *
     * @param key
     * @param value
     * @param score
     */
    @Async(value = AsyncNameConstant.REDIS)
    public String putRecommendRankingGoods(String key, String value, double score) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return null;
        }
        boolean putResult = zAdd(key, value, score);
        /**
         * TODO 处理结果
         */
        return key;
    }

    /**
     * 写入ranking
     *
     * @param value 商品值
     * @param score 分数
     * @return redis key
     */
    public String putRecommendRankingGoods(String value, double score) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        String key = redisKeyUtil.generateKeyForRecommendRankingGoods();
        return putRecommendRankingGoods(key, value, score);
    }

    /**
     * 写入ranking
     *
     * @param goodsId 商品ID
     * @param score   分数
     * @return redis key
     */
    public String putRecommendRankingGoods(long goodsId, double score) {
        if (goodsId <= 0) {
            return null;
        }
        return putRecommendRankingGoods("" + goodsId, score);
    }

    /**
     * 移出ranking
     *
     * @param key    redis key
     * @param values 具体value列表
     * @return 移出个数
     */
    @Async(value = AsyncNameConstant.REDIS)
    public long removeRecommendRankingGoods(String key, String... values) {
        if (StringUtils.isBlank(key)) {
            return 0;
        }
        return zRemove(key, values);
    }

    /**
     * 移出ranking
     *
     * @param goodsId 商品ID
     * @return
     */
    public long removeRecommendRankingGoods(long goodsId) {
        if (goodsId <= 0) {
            return 0;
        }
        String key = redisKeyUtil.generateKeyForRecommendRankingGoods();
        String value = String.valueOf(goodsId);
        return removeRecommendRankingGoods(key, value);
    }

    /**
     * 增加分数
     *
     * @param goodsId
     * @param score
     * @return
     */
    public double incrementScoreToRankginGoods(long goodsId, double score) {
        String key = redisKeyUtil.generateKeyForRecommendRankingGoods();
        String value = String.valueOf(goodsId);
        double result = zIncrementScore(key, value, score);
        expire(key, RedisConstant.RANKING_GOODS_TTL, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * 增加分数，默认1分
     *
     * @param goodsId
     * @return
     */
    public double incrementScoreToRankginGoods(long goodsId) {
        return incrementScoreToRankginGoods(goodsId, NumericConstant.ONE);
    }

    /**
     * range data and score
     *
     * @param key   redis key
     * @param start 开始下标，0为第一个
     * @param end   结束下标
     * @return
     */
    public List<RecommendRankingGoods> rangeWithScoresToRankingGoods(String key, long start, long end) {
        Set<ZSetOperations.TypedTuple<String>> redisData = zRangeWithScores(key, start, end);
        List<RecommendRankingGoods> result = Lists.newArrayList();
        for (ZSetOperations.TypedTuple<String> item : redisData) {
            RecommendRankingGoods tmp = new RecommendRankingGoods();
            tmp.setGoodsId(Long.parseLong(item.getValue()));
            // tmp.setRanking(item.getScore().intValue());
            int tmpRank = zRank(key,
                    item.getValue()) == null ? NumericConstant.MAX_VALUE : zRank(key, item.getValue()).intValue();
            tmp.setRanking(tmpRank);
            result.add(tmp);
        }
        return result;
    }

    /**
     * range data and score
     *
     * @param start
     * @param end
     * @return
     */
    public List<RecommendRankingGoods> rangeWithScoresToRankingGoods(long start, long end) {
        String key = redisKeyUtil.generateKeyForRecommendRankingGoods();
        return rangeWithScoresToRankingGoods(key, start, end);
    }

    /**
     * top n to ranking
     *
     * @param n
     * @return
     */
    public List<RecommendRankingGoods> topNForRangeWithScoresToRankingGoods(int n) {
        return rangeWithScoresToRankingGoods(0, n);
    }

    /**
     * 返回排名
     *
     * @param goodsId
     * @return
     */
    public Long rankToRankingGoods(long goodsId) {
        String key = redisKeyUtil.generateKeyForRecommendRankingGoods();
        return zRank(key, String.valueOf(goodsId));
    }

    /**
     * 获取userVo
     *
     * @param telephone
     * @return
     */
    public UserVo getUserVo(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            return null;
        }
        UserVo userVo = new UserVo();
        // 用户基本信息
        User user = getUserByTelephone(telephone);
        userVo.setUserInfo(user);
        /**
         * TODO 其它属性
         */
        return userVo;
    }

    /**
     * 获取User
     *
     * @param key
     * @return
     */
    public User getUser(String key) {
        String jsonUser = (String) hGet(key, RedisConstant.USER);
        return JSON.parseObject(jsonUser, User.class);
    }

    /**
     * 根据电话号码获取User
     *
     * @param telephone
     * @return
     */
    public User getUserByTelephone(String telephone) {
        String redisKey = redisKeyUtil.generateKeyForUserVo(telephone);
        return getUser(redisKey);
    }

    /**
     * 是否存活用户对象
     *
     * @param telephone
     * @return
     */
    public boolean isAliveUser(String telephone) {
        String redisKey = redisKeyUtil.generateKeyForUserVo(telephone);
        return hasKey(redisKey);
    }

    /**
     * 获取短信验证码
     *
     * @param telephone
     * @return
     */
    public String getSmsCode(String telephone) {
        // 生成redis key
        String redisKey = redisKeyUtil.generateKeyForSms(telephone);
        // 获取短信验证码
        return get(redisKey);
    }

}

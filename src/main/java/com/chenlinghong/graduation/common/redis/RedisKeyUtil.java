package com.chenlinghong.graduation.common.redis;

import com.chenlinghong.graduation.repository.domain.BaseDomain;
import org.springframework.stereotype.Service;


/**
 * @Description redis key设计
 * @Author chenlinghong
 * @Date 2019/4/5 13:35
 * @Version V1.0
 */
@Service
public class RedisKeyUtil<T> {

    public String generateKey(T data){
        // data 是 domain领域对象
        if (data instanceof BaseDomain){
            /**
             * 通过反射，获取到data的类名，实际key的设计为：className:id
             */
            Class clazz = data.getClass();
            String className = clazz.getSimpleName();
            StringBuffer result = new StringBuffer(className);
            result.append(":");
            // 将data强转为BaseDomain
            BaseDomain dataDomain = (BaseDomain) data;
            result.append(dataDomain.getId());
            return result.toString();
        }
        /**
         * 暂不提供其它类的redis key设计
         */
        return null;
    }
}

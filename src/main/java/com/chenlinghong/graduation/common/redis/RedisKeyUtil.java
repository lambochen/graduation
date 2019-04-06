package com.chenlinghong.graduation.common.redis;

import com.chenlinghong.graduation.repository.domain.User;
import org.junit.Test;


/**
 * @Description redis key设计
 * @Author chenlinghong
 * @Date 2019/4/5 13:35
 * @Version V1.0
 */
public class RedisKeyUtil {



    public String generateKey(User user){
        Class clazz = user.getClass();
        String typeName = clazz.getTypeName();      // 全限定名
        String name = clazz.getName();
        String simpleName = clazz.getSimpleName();      // user TODO 将采用
        String canonicalName = clazz.getCanonicalName();
        return null;
    }

    @Test
    public void test(){
        generateKey(new User());
    }


}

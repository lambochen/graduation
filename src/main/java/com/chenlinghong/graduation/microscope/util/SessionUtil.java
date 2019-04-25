package com.chenlinghong.graduation.microscope.util;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.constant.SessionConstant;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserService;
import com.chenlinghong.graduation.util.MyRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description Session 相关处理
 * @Author chenlinghong
 * @Date 2019/4/25 11:49
 * @Version V1.0
 */
@Slf4j(topic = "me")
@Service
public class SessionUtil {

    @Autowired
    private MyRedisUtil redisUtil;

    @Autowired
    private UserService userService;

    /**
     * 获取用户ID
     *
     * @param request
     * @return
     */
    public long getUserId(HttpServletRequest request) {
        User user = getUser(request);
        return user == null ? -1 : user.getId();
    }

    /**
     * 获取电话号码
     *
     * @param request
     * @return
     */
    public String getTelephone(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew() == false) {
            return (String) session.getAttribute(SessionConstant.TELEPHONE);
        }
        return null;
    }

    /**
     * 获取UserVO对象
     * @param request
     * @return
     */
    public UserVo getUserVo(HttpServletRequest request) {
        String telephone = getTelephone(request);
        UserVo result = redisUtil.getUserVo(telephone);
        if (result == null){
            // 从DB获取数据
            result = userService.getUserVoByTelephoneNotPushCache(telephone);
        }
        return result;
    }

    /**
     * 获取user对象
     * @param request
     * @return
     */
    public User getUser(HttpServletRequest request){
        UserVo userVo = getUserVo(request);
        return userVo == null ? null : userVo.getUserInfo();
    }


}

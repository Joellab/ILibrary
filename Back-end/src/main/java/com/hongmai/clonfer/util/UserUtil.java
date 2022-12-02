package com.hongmai.clonfer.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongmai.clonfer.enums.ConstantEnum;
import com.hongmai.clonfer.enums.ResultCodeEnum;
import com.hongmai.clonfer.exception.ApiException;
import com.hongmai.clonfer.model.domain.CpBaseUser;
import com.hongmai.clonfer.model.vo.AclVO;
import com.hongmai.clonfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Component
public class UserUtil {

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    public static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    public static String getCurrentUserIp() {
        try {
            return ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRemoteAddress();
        } catch (Exception e) {
            return "0.0.0.0";
        }
    }

    public static String getCurrentUserToken() {
        return ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getSessionId();
    }

    public CpBaseUser getCurrentUserDetails() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", getCurrentUserName());
        return userService.getOne(queryWrapper);
    }

    public CpBaseUser getSpecificUserDetailsByUuid(String uuid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uuid", uuid);
        return userService.getOne(queryWrapper);
    }


    public static List<AclVO> generateUserAcl(String action, String subject) {
        List<AclVO> aclVOS = new ArrayList<>();
        AclVO aclVO = new AclVO();
        aclVOS.add(aclVO.setAction(action).setSubject(subject));
        return aclVOS;
    }

    public String invalidCurrentUserLoginStatus() {
        String ramdomCode = getRandomString(6);
        redisUtil.set("STATUSCHECK|" + UserUtil.getCurrentUserName(), ConstantEnum.INVALID_USER.getValue(), 86400);
        return ramdomCode;
    }

    public String invalidSpecificUserLoginStatus(String username) {
        String ramdomCode = getRandomString(6);
        redisUtil.set("STATUSCHECK|" + username, ConstantEnum.INVALID_USER.getValue(), 86400);
        return ramdomCode;
    }

    public void validSpecificUserLoginStatus(String username) {
        /*
        redisUtil.del("STATUSCHECK|" + username);

         */
    }

    public void checkSpecificUserLoginStatus(String username) {
        /*
        if(redisUtil.get("STATUSCHECK|" + UserUtil.getCurrentUserName()) != null) {
            if (redisUtil.get("STATUSCHECK|" + UserUtil.getCurrentUserName()).equals(ConstantEnum.INVALID_USER.getValue())) {
                throw new AccessDeniedException("登陆凭据已失效");
            }
        }

         */
    }

    public String generateVerifyUuidCode(String email) {
        String ramdomCode = getRandomString(6);
        redisUtil.set("verifyCode" + email, ramdomCode, 600);
        return ramdomCode;
    }

    public String verifyVerifyUuidCode(String email) {
        return (String)redisUtil.get("verifyCode" + email);
    }

    public String generateVerifyResetPasswordCode(String email) {
        String ramdomCode = getRandomString(20);
        redisUtil.set(ramdomCode, email, 600);
        return ramdomCode;
    }

    public String verifyResetPasswordCode(String code) {
        if(redisUtil.get(code) != null) {
            String resetCode = (String)redisUtil.get(code);
            redisUtil.del(code);
            return resetCode;
        }
        throw new ApiException(ResultCodeEnum.CODE_INVALID);
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}

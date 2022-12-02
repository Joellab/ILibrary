package com.hongmai.clonfer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongmai.clonfer.model.domain.CpBaseUser;
import com.hongmai.clonfer.model.param.*;
import com.hongmai.clonfer.model.vo.UserPageVO;
import com.hongmai.clonfer.model.vo.UserVO;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
public interface UserService extends IService<CpBaseUser> {
    /**
     *
     * @param user
     * @return
     */
    UserVO login(LoginParam user);

    /**
     *
     * @param param
     */
    void register(RegisterParam param);

    /**
     *
     * @param param
     */
    void sendAuthEmail(AuthEmailParam param);

    /**
     *
     * @param param
     */
    void sendResetPasswordEmail(ResetPasswordRequestParam param);

    /**
     *
     * @param param
     */
    void resetPassword(ResetPasswordParam param);

    IPage<UserPageVO> searchUserList(Page<CpBaseUser> page, String searchParam);

    //IPage<UserPageVO> selectPage(Page<CpBaseUser> page);

    //void update(UserParam param);

    //void createUser(UserParam param);
}

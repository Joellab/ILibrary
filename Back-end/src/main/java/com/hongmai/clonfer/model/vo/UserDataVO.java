package com.hongmai.clonfer.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
@Accessors(chain = true)
public class UserDataVO {

    private String uuid;

    private String fullName;

    private String userName;

    private String avatar;

    private String email;

    private String organization;

    private int role;

    private String roleNickname;

    private List<AclVO> ability;

}

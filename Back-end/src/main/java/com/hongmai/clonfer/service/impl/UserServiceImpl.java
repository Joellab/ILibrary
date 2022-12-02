package com.hongmai.clonfer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.enums.ConstantEnum;
import com.hongmai.clonfer.enums.ResultCodeEnum;
import com.hongmai.clonfer.enums.UserStatusEnum;
import com.hongmai.clonfer.exception.ApiException;
import com.hongmai.clonfer.mapper.CpBaseUserMapper;
import com.hongmai.clonfer.model.domain.CpBaseOrginazation;
import com.hongmai.clonfer.model.domain.CpBaseRole;
import com.hongmai.clonfer.model.domain.CpBaseUser;
import com.hongmai.clonfer.model.param.*;
import com.hongmai.clonfer.model.template.ResetPasswordTemplate;
import com.hongmai.clonfer.model.template.VerifyEmailTemplate;
import com.hongmai.clonfer.model.vo.*;
import com.hongmai.clonfer.service.*;
import com.hongmai.clonfer.security.JwtManager;
import com.hongmai.clonfer.security.UserDetail;
import com.hongmai.clonfer.util.SendEmailUtil;
import com.hongmai.clonfer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DateUtil.now;
import static com.hongmai.clonfer.util.UserUtil.getCurrentUserIp;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<CpBaseUserMapper, CpBaseUser> implements UserService, UserDetailsService {
    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserVO login(LoginParam param) {

        CpBaseUser cpBaseUser;
        //判断用户是否存在及状态是否有效
        try {
            QueryWrapper<CpBaseUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", param.getUsername());
            CpBaseUser cpBaseUserTemp = baseMapper.selectOne(queryWrapper);
            if(cpBaseUserTemp == null) {
                QueryWrapper<CpBaseUser> newQueryWrapper = new QueryWrapper<>();
                newQueryWrapper.eq("email", param.getUsername());
                cpBaseUserTemp = baseMapper.selectOne(newQueryWrapper);
            }
            cpBaseUser = cpBaseUserTemp;
        } catch (Exception e) {
            throw new ApiException(ResultCodeEnum.DATABASE_EXCEPTION);
        }

        //判断用户密码是否正确
        if (cpBaseUser == null || !passwordEncoder.matches(param.getPassword(), cpBaseUser.getPassword())) {
            throw new ApiException(ResultCodeEnum.LOGIN_FAILED);
        }

        //判断用户是否被禁用
        if(cpBaseUser.getStatus().equals(ConstantEnum.INVALID_USER.getValue())) {
            throw new ApiException(ResultCodeEnum.USER_INVALID);
        }

        //删除无效用户缓存
        userUtil.validSpecificUserLoginStatus(cpBaseUser.getUsername());
        //生成JWT并储存用户数据
        try {
            String userToken = jwtManager.generate(cpBaseUser.getUsername());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("code", cpBaseUser.getRole());
            String roleNickName = roleService.getOne(queryWrapper).getName();
            UserDataVO userDataVO = new UserDataVO();
            userDataVO.setUuid(cpBaseUser.getUuid())
                    .setFullName(cpBaseUser.getFullname())
                    .setUserName(cpBaseUser.getUsername())
                    .setAvatar(cpBaseUser.getAvatar())
                    .setEmail(cpBaseUser.getEmail())
                    .setRole(cpBaseUser.getRole())
                    .setRoleNickname(roleNickName)
                    .setAbility((List<AclVO>) JSON.parseObject(cpBaseUser.getAbility(), List.class))
                    .setOrganization(cpBaseUser.getOrginazationUuid());

            UserVO userVO = new UserVO();
            userVO.setAccessToken(userToken)
                    .setRefreshToken(userToken)
                    .setUserData(userDataVO);

            cpBaseUser.setIpLastLogin(getCurrentUserIp());
            cpBaseUser.setGmtLastLogin(now());
            updateById(cpBaseUser);

        return userVO;
        } catch (Exception e) {
            System.out.println(e);
            throw new ApiException(ResultCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public void register(RegisterParam param) {

        if (lambdaQuery().eq(CpBaseUser::getUsername, param.getUsername()).one() != null || lambdaQuery().eq(CpBaseUser::getEmail, param.getEmail()).one() != null || lambdaQuery().eq(CpBaseUser::getEmail, param.getUsername()).one() != null) {
            throw new ApiException(ResultCodeEnum.USER_EXIST);
        }
        /*
        if(userUtil.verifyVerifyUuidCode(param.getEmail()) == null) {
            throw new ApiException(ResultCodeEnum.CODE_INVALID);
        } else if (!userUtil.verifyVerifyUuidCode(param.getEmail()).equals(param.getCode().trim())) {
            throw new ApiException(ResultCodeEnum.CODE_INVALID);
        }
         */

        CpBaseOrginazation organization = organizationService.getById(param.getOrganization().toLowerCase());
        if(organization == null) {
            throw new ApiException(ResultCodeEnum.ORGANIZATION_NOT_EXIST);
        }

        try {
            CpBaseUser cpBaseUser = new CpBaseUser();
            cpBaseUser.setUsername(param.getUsername())
                    .setUuid(UUID.randomUUID().toString())
                    .setFullname(param.getFullname())
                    .setPassword(passwordEncoder.encode(param.getPassword()))
                    .setEmail(param.getEmail())
                    .setAvatar(ConstantEnum.DEFAULT_AVATAR_URL.getValue())
                    .setOrginazationUuid(organization.getUuid())
                    .setStatus(UserStatusEnum.NORMAL.getCode())
                    .setAbility(JSON.toJSONString(UserUtil.generateUserAcl(ConstantEnum.DEFAULT_USER_ACL_ACTION.getValue(), ConstantEnum.DEFAULT_USER_ACL_SUBJECT.getValue())))
                    .setRole(Integer.valueOf(ConstantEnum.DEFAULT_ROLE.getValue()))
                    .setIpReg(getCurrentUserIp())
                    .setGmtReg(now());

            save(cpBaseUser); 
        } catch (Exception e) {
            throw new ApiException(ResultCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public void sendAuthEmail(AuthEmailParam param) {
        sendEmailUtil.sendSimpleMail(param.getEmail(), "Clonf云展会 - 用户注册邮箱验证", VerifyEmailTemplate.generateVerifyEmailHtml(param.getFullname(), userUtil.generateVerifyUuidCode(param.getEmail())));
    }

    @Override
    public void sendResetPasswordEmail(ResetPasswordRequestParam param) {

        CpBaseUser cpBaseUser;

        QueryWrapper<CpBaseUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", param.getEmail());
        CpBaseUser cpBaseUserTemp = baseMapper.selectOne(queryWrapper);
        cpBaseUser = cpBaseUserTemp;
        System.out.println(cpBaseUser);
        if(cpBaseUser == null) {
            throw new ApiException(ResultCodeEnum.USER_NOT_EXIST);
        } else {
            String resetLink = ConstantEnum.WEB_BASE_LINK.getValue() + ConstantEnum.WEB_RESET_PASSWORD_ROUTE.getValue() + "/" + userUtil.generateVerifyResetPasswordCode(param.getEmail());
            sendEmailUtil.sendSimpleMail(param.getEmail(), "Clonf云展会 - 用户密码重置", ResetPasswordTemplate.resetPasswordTemplate(cpBaseUser.getFullname(), param.getEmail(), cpBaseUser.getUuid(), getCurrentUserIp(), resetLink));
        }
    }

    @Override
    public void resetPassword(ResetPasswordParam param) {

        CpBaseUser cpBaseUser;

        QueryWrapper<CpBaseUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userUtil.verifyResetPasswordCode(param.getCode()));
        CpBaseUser cpBaseUserTemp = baseMapper.selectOne(queryWrapper);
        cpBaseUser = cpBaseUserTemp;

        cpBaseUser.setPassword(passwordEncoder.encode(param.getPassword()));
        System.out.println(updateById(cpBaseUser));

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        QueryWrapper<CpBaseUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        CpBaseUser selectedUser = baseMapper.selectOne(queryWrapper);
        if (selectedUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        QueryWrapper<CpBaseRole> queryWrapperRole = new QueryWrapper<>();
        queryWrapperRole.eq("code", selectedUser.getRole());
        CpBaseRole cpBaseRole = roleService.getOne(queryWrapperRole);

        Set<Integer> tempAuthorities = JSON.parseObject(cpBaseRole.getResource(), Set.class);

        Set<SimpleGrantedAuthority> authorities = tempAuthorities
                                                    .stream()
                                                    .map(String::valueOf)
                                                    .map(SimpleGrantedAuthority::new)
                                                    .collect(Collectors.toSet());

        return new UserDetail(selectedUser, authorities);
    }

    @Override
    public IPage<UserPageVO> searchUserList(Page<CpBaseUser> page, String searchParam) {
        QueryWrapper<CpBaseUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("fullname", searchParam);
        IPage<CpBaseUser> pages = baseMapper.selectPage(page, queryWrapper);
        IPage<UserPageVO> userPages = pages.convert(CpBaseUser -> BeanUtil.copyProperties(CpBaseUser, UserPageVO.class));
        return userPages;
    }

    /*

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        // 删除用户下所属的角色
        for (Serializable userId : idList) {
            roleService.removeByUserId(userId);
        }
        // 删除用户
        baseMapper.deleteBatchIds(idList);
        return true;
    }

    @Override
    public IPage<UserPageVO> selectPage(Page<CpBaseUser> page) {
        QueryWrapper<CpBaseUser> queryWrapper = new QueryWrapper<>();
        // 获取分页列表
        IPage<UserPageVO> pages = baseMapper.selectPage(page, queryWrapper);
        // 再查询各用户的角色和公司id
        for (UserPageVO vo : pages.getRecords()) {
            //vo.setRoleIds(roleService.getIdsByUserId(vo.getId()));
            //vo.setCompanyIds(companyService.getIdsByUserId(vo.getId()));
        }
        return pages;
    }

    @Override
    public void update(UserParam param) {
        updateRoles(param);
        updateCompany(param);
    }

    private void updateRoles(UserParam param) {
        // 先删除原有数据
        roleService.removeByUserId(param.getId());
        // 如果角色为空就代表删除所有角色，不用后面新增流程了
        if (CollectionUtils.isEmpty(param.getRoleIds())) {
            return;
        }
        // 再新增数据
        roleService.insertRolesByUserId(param.getId(), param.getRoleIds());
    }

    private void updateCompany(UserParam param) {
        companyService.removeByUserId(param.getId());
        // 如果公司为空就代表删除所有公司，不用后面新增流程了
        if (CollectionUtils.isEmpty(param.getCompanyIds())) {
            return;
        }
        // 再新增数据
        companyService.insertCompanyByUserId(param.getId(), param.getCompanyIds());
    }
 */
}

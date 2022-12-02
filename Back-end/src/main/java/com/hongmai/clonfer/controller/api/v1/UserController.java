package com.hongmai.clonfer.controller.api.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongmai.clonfer.annotation.WebLog;
import com.hongmai.clonfer.model.domain.CpBaseUser;
import com.hongmai.clonfer.model.vo.UserPageVO;
import com.hongmai.clonfer.service.UserService;
import com.hongmai.clonfer.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@Auth(id = 100, name = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    @Auth(id = 1, name = "搜索用户列表")
    @WebLog(description = "搜索用户列表接口")
    public IPage<UserPageVO> searchUserList(@RequestParam("q") String searchParam) {
        Page<CpBaseUser> userPage = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(false);
        userPage.setCurrent(1).addOrder(orderItem);
        return userService.searchUserList(userPage, searchParam);
    }
    /*
    @PostMapping
    @Auth(id = 1, name = "新增用户")
    public void createUser(@RequestBody @Validated(UserParam.CreateUser.class) UserParam param) {
        userService.createUser(param);
    }

    @DeleteMapping
    @Auth(id = 2, name = "删除用户")
    public void deleteUser(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            throw new ApiException(ResultCodeEnum.VALIDATE_FAILED);
        }
        userService.removeByIds(Arrays.asList(ids));
    }

    @PutMapping
    @Auth(id = 3, name = "编辑用户")
    public String updateRoles(@RequestBody @Validated(UserParam.Update.class) UserParam param) {
        userService.update(param);
        return "操作成功";
    }
     */

    /*
    @GetMapping("/page/{current}")
    public IPage<UserPageVO> getPage(@PathVariable("current") int current) {
        // 设置分页参数
        Page<UserPageVO> page = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("id");
        page.setCurrent(current).addOrder(orderItem);
        return userService.selectPage(page);
    }

    @GetMapping("/resources/{userId}")
    public Set<Long> getResourcesByUserId(@PathVariable("userId") Long userId) {
        return resourceService.getIdsByUserId(userId);
    }

    */
}

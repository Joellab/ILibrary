package com.hongmai.clonfer.controller.api.v1.management;

import com.hongmai.clonfer.annotation.Auth;
import com.hongmai.clonfer.model.param.RoleParam;
import com.hongmai.clonfer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/API/role")
@Auth(id = 2000, name = "角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    @Auth(id = 1, name = "新增角色")
    public String createUser(@RequestBody @Validated(RoleParam.CreateRole.class) RoleParam param) {
        //roleService.createRole(param);
        return "OK";
    }

    @DeleteMapping
    @Auth(id = 2, name = "删除角色")
    public String deleteUser(Long[] ids) {
        roleService.removeByIds(Arrays.asList(ids));
        return "OK";
    }

    @PutMapping
    @Auth(id = 3, name = "编辑角色")
    public String updateMenus(@RequestBody @Validated(RoleParam.UpdateResources.class) RoleParam param) {
        //roleService.updateResources(param);
        return "OK";
    }
 /*
    @GetMapping("/list")
    public List<Role> getRoleList() {
        return roleService.list();
    }

    @GetMapping("/page/{current}")
    public IPage<RolePageVO> getPage(@PathVariable("current") int current) {
        // 设置分页参数
        Page<RolePageVO> page = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("id");
        page.setCurrent(current).addOrder(orderItem);
        return roleService.selectPage(page);
    }
    */
}

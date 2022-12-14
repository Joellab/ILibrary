package com.hongmai.clonfer.security;

import com.hongmai.clonfer.enums.ResultCodeEnum;
import com.hongmai.clonfer.model.vo.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权错误处理器
 *
 * @author JiaweiWang
 */
public class MyDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultVO<String> resultVO = new ResultVO<>(ResultCodeEnum.FORBIDDEN, "没有相关权限");
        out.write(resultVO.toString());
        out.flush();
        out.close();
    }
}

package com.cg.module03casestudy.filter;

import com.cg.module03casestudy.model.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        String loginPath = request.getContextPath() + "/login";
        String forbiddenPath = request.getContextPath() + "/error/403";
        UserDto userDto =  (UserDto) request.getSession().getAttribute("userLogged");
        boolean isStartWithLoginUrls = servletPath.startsWith("/login");
        boolean isStartWithRoleUrls = servletPath.startsWith("/role");
        boolean isStartWithUserUrls = servletPath.startsWith("/user");

        if (isStartWithLoginUrls) {
            filterChain.doFilter(request, response);
        }

        if (!isStartWithLoginUrls && userDto == null) {
            response.sendRedirect(loginPath);
            return;
        }

        if (!isStartWithLoginUrls && userDto != null) {
            String roleDesc = userDto.getRoleDesc();

            if ((isStartWithRoleUrls || isStartWithUserUrls)
                    && roleDesc.equals("Nhân viên")) {
                response.sendRedirect(forbiddenPath);
                return;
            }

            filterChain.doFilter(request, response);
        }
    }
}

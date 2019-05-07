package com.conan.mvcdemo.intercepter;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需要在注册配置打SpringMVC容器
 * <p>
 * 1 : 可以用来判断权限
 * <p>
 * 2 : 可以用来修改编码
 */
public class Intercepter2 implements HandlerInterceptor {

    private String encoding;

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 最先执行 NO.1
     * <p>
     * 这个方法中判断是否拦截  返回true 说明不拦截  返回false 拦截,请求终止
     *
     * @param request
     * @param response
     * @param handler  handler 可以获取到 Controller的实例
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        return false;
        return true;
    }

    /**
     * NO.2  执行到这一步 请求已经到达Controller 并要返回给请求端
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView Controller 返回的modelAndView ,通过操作modelAndView 可以修改数据和视图;
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * NO.3 用来最终释放资源;
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

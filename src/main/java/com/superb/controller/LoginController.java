package com.superb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Controller
public class LoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String user(@RequestParam("username")String username,
                       @RequestParam("password")String password,
                       Model model,
                       HttpServletRequest request){
        if ("admin".equals(username)&&"1".equals(password)){
            //存session证明已登录
            request.getSession().setAttribute("username",username);
            //重定向到user（避免请求重复提交）
            return "redirect:/home";
        }
        model.addAttribute("msg","用户名或密码错误");
        return "login";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @GetMapping("/layout")
    public String layout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "login";
    }

    /**
     * 去首页
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        //登录拦截
        if (request.getSession().getAttribute("username")==null){
            model.addAttribute("msg","未登录");
            return "login";
        }
        return "book/index";
    }



}

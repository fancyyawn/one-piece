package top.zhacker.blog.web.controller;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.zhacker.blog.web.model.User;
import top.zhacker.blog.web.service.UserServiceClient;

/**
 * DATE: 17/1/6 下午3:39 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 用户视图
 */
@Controller
@Slf4j
public class Users {

    private final UserServiceClient userService;

    @Autowired
    public Users(UserServiceClient userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupForm(){
        return "user/signup";
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(User user, BindingResult result,
                         RedirectAttributes attributes){
        if (result.hasErrors()) {
            return "user/signup";
        }
        try{
            userService.createUser(user);
            attributes.addFlashAttribute("success", "注册成功");
            return "redirect:/login";

        }catch(Exception e){
            log.error("signup fail, cause={}", Throwables.getStackTraceAsString(e));
            attributes.addFlashAttribute("error", "signup.fail");
            return "user/signup";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(){
        return "user/login";
    }

}
